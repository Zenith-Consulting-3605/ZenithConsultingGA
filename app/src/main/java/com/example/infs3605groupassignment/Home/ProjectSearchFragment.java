package com.example.infs3605groupassignment.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.infs3605groupassignment.Database.DbHelper;
import com.example.infs3605groupassignment.Objects.Project;
import com.example.infs3605groupassignment.Objects.User;
import com.example.infs3605groupassignment.Profiles.ProfileActivity;
import com.example.infs3605groupassignment.Projects.CollaboratorSearchAdapter;
import com.example.infs3605groupassignment.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProjectSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectSearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private ProjectSearchAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RequestQueue requestQueue;

    private String TAG = "PROJECT_SEARCH_FRAGMENT";
    private EditText search;
    private int userID;
    private String webformatURL;
    private List<Project> projectList = new ArrayList<>();
    private List<Project> updatedList = new ArrayList<>();
//    private List<Project> lister = new ArrayList<>();
    private Context context;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProjectSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProjectSearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProjectSearchFragment newInstance(String param1, String param2) {
        ProjectSearchFragment fragment = new ProjectSearchFragment();
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
        View v = inflater.inflate(R.layout.fragment_project_search, container, false);

        userID = getArguments().getInt("userID");

        search = v.findViewById(R.id.edtSearchProject);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
                Log.d(TAG, "STRING WAS: " + s.toString() + "-----");
            }

        });

        recyclerView = v.findViewById(R.id.searchListResults);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        final DbHelper dbHelper = new DbHelper(getContext());

        projectList = dbHelper.getAllProjects();

        requestQueue = Volley.newRequestQueue(getContext());

        parseJson(projectList);

        return v;
    }

    public void parseJson(final List<Project> projList) {
        for (int i = 0; i < projList.size(); i++) {
            final Project pj = projList.get(i);
            String fullName = pj.getName();
            String[] nameArray = fullName.split("\\s+");
            String fWord = nameArray[0];
//            Log.d(TAG, "WORD: " + fWord);

            String url = "https://pixabay.com/api/?key=17762136-3d8484fce3723ad954e31049c&q=" + fWord + "&image_type=photo&pretty=true";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("hits");

                                if(jsonArray.length() < 1) {
                                    webformatURL = "NO_RESULTS";
                                } else {
                                    JSONObject hit = jsonArray.getJSONObject(0);
                                    webformatURL = hit.getString("webformatURL");
                                }
                                Project update = new Project(pj.getID(), pj.getName(), pj.getFunding(), pj.getCompany(), pj.getCountry(), pj.getDescription(), pj.getProgress(), pj.getCategory(), webformatURL);
                                updatedList.add(update);

                                adapter = new ProjectSearchAdapter(getContext(), ProjectSearchFragment.this, updatedList, new ProjectSearchAdapter.ProjectSearchClickListener() {
                                    @Override
                                    public void onClick(int id) {
//                                        Log.d(TAG, "Project " + id + " was clicked");
                                        Intent intent = new Intent(getContext(), ProfileActivity.class);
                                        intent.putExtra("userID", userID);
                                        startActivity(intent);
                                    }
                                });

                                recyclerView.setAdapter(adapter);
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

    public void filter(String text) {
//        lister.clear();
        final List<Project> lister = new ArrayList<>();
        List<Project> filteredList = new ArrayList<>();

        final DbHelper dbHelper = new DbHelper(getContext());

        List<Project> returnList = dbHelper.getAllProjects();

        if (text.trim().length() == 0) {
            filteredList = returnList;
        } else {
            for (Project project : returnList) {
                if(project.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(project);
                }
            }
        }

        for(int i = 0; i < filteredList.size(); i++) {
            final Project rp = filteredList.get(i);
            String name = rp.getName();
            String[] nameArray = name.split("\\s+");
            String fWord = nameArray[0];
            Log.d(TAG, "FWORD:" + name);

            String url = "https://pixabay.com/api/?key=17762136-3d8484fce3723ad954e31049c&q=" + fWord + "&image_type=photo&pretty=true";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("hits");

                                if(jsonArray.length() < 1) {
                                    webformatURL = "NO_RESULTS";
                                } else {
                                    JSONObject hit = jsonArray.getJSONObject(0);
                                    webformatURL = hit.getString("webformatURL");
                                }

                                Project update = new Project(rp.getID(), rp.getName(), rp.getFunding(), rp.getCompany(), rp.getCountry(), rp.getDescription(), rp.getProgress(), rp.getCategory(), webformatURL);
                                lister.add(update);
                                adapter.filterList(lister);
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
