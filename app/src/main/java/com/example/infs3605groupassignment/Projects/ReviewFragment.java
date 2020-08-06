package com.example.infs3605groupassignment.Projects;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.infs3605groupassignment.Database.DbHelper;
import com.example.infs3605groupassignment.Objects.Profile;
import com.example.infs3605groupassignment.Objects.User;
import com.example.infs3605groupassignment.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<User> userList = new ArrayList<>();
    private int[] collaborate;
    private List<Integer> reviewed = new ArrayList<>();
    private String TAG = "REVIEW_FRAGMENT";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProjectReviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewFragment newInstance(String param1, String param2) {
        ReviewFragment fragment = new ReviewFragment();
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
        View v =  inflater.inflate(R.layout.fragment_project_review, container, false);
        collaborate = getArguments().getIntArray("collaborators");
        Log.d(TAG, Arrays.toString(collaborate));

        recyclerView = v.findViewById(R.id.rvListReview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        final DbHelper dbHelper = new DbHelper(getContext());

        for (int i=0;i<collaborate.length;i++){
            int personalID = collaborate[i];
//            Log.d(TAG, "USERID IS: " + person);
            User user = dbHelper.getReviewInvites(personalID);
            userList.add(user);
        }

//        reviewed = new int[collaborate.length - 1];

        adapter = new ReviewAdapter(this,userList, new ReviewAdapter.ReviewClickListener() {
            @Override
            public void onReject(int id){
                Log.d(TAG, "REMOVE PERSON WAS PRESSED");
//                for (int i = 0; i < collaborate.length; i++) {
//                    int j = collaborate[i];
//                    if (j != id) {
//                        reviewed.add(collaborate[i]);
//                    }
//
//                }
                Log.d(TAG, "" + reviewed);
            }

        });

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return v;
    }
}