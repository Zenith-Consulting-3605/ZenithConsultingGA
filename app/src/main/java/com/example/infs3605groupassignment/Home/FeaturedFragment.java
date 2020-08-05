package com.example.infs3605groupassignment.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.infs3605groupassignment.Database.DbHelper;
import com.example.infs3605groupassignment.Objects.Project;
import com.example.infs3605groupassignment.Profiles.ProfileActivity;
import com.example.infs3605groupassignment.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeaturedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeaturedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RequestQueue requestQueue;

    private String TAG = "FEATURED_FRAGMENT";
    private String webformatURL;
    private List<Project> featuredProjects = new ArrayList<>();
    private List<Project> updatedProjects = new ArrayList<>();
    private Context context;

    private int userID;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeaturedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeaturedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeaturedFragment newInstance(String param1, String param2) {
        FeaturedFragment fragment = new FeaturedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_featured, container, false);

        userID = getArguments().getInt("userID", 0);

        recyclerView = v.findViewById(R.id.rvListFeatured);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        final DbHelper dbHelper = new DbHelper(getContext());

        int numberProjects = dbHelper.getNumberProjects();
        int proj1 = (int) Math.floor(Math.random() * numberProjects) + 1;
        int proj2 = (int) Math.floor(Math.random() * numberProjects) + 1;
        int proj3 = (int) Math.floor(Math.random() * numberProjects) + 1;

        Log.d(TAG, "" + proj1);
        Log.d(TAG, "" + proj2);
        Log.d(TAG, "" + proj3);

        featuredProjects = dbHelper.getFeaturedProjects(proj1, proj2, proj3);

        requestQueue = Volley.newRequestQueue(getContext());

        parseJson();

        return v;
    }

    public void parseJson() {
        for(int i = 0; i < featuredProjects.size(); i++) {
            final Project project = featuredProjects.get(i);
            String fullName = project.getName();
            String[] nameArray = fullName.split("\\s+");
            String firstWord = nameArray[0];

            String url = "https://pixabay.com/api/?key=17762136-3d8484fce3723ad954e31049c&q=" + firstWord + "&image_type=photo&pretty=true";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("hits");

                                JSONObject hit = jsonArray.getJSONObject(0);
                                webformatURL = hit.getString("webformatURL");
                                Log.d(TAG, "CORRECT URL IS: " + webformatURL);

                                Project newProj = new Project(project.getID(), project.getName(), project.getCompany(), project.getCategory(), webformatURL);
                                updatedProjects.add(newProj);

                                adapter = new FeaturedAdapter(getContext(), FeaturedFragment.this, updatedProjects, new FeaturedAdapter.FeaturedClickListener() {
                                    @Override
                                    public void onClick(int ID) {
                                        Log.d(TAG, "Feature was clicked, ID was: " + ID);
                                        Intent intent = new Intent(getContext(), ProfileActivity.class); //NEED TO FIX TO GO TO CORRECT ACTIVITY
                                        intent.putExtra("userID", userID);
                                        startActivity(intent);
                                    }
                                });

                                recyclerView.setAdapter(adapter);
//                                adapter.notifyDataSetChanged();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            requestQueue.add(request);





        }



    }
}