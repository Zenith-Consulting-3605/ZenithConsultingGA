package com.example.infs3605groupassignment.Profiles;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.infs3605groupassignment.Database.DbHelper;
import com.example.infs3605groupassignment.Objects.Experience;
import com.example.infs3605groupassignment.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExperienceDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExperienceDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public ExperienceDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExperienceDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExperienceDetailFragment newInstance(String param1, String param2) {
        ExperienceDetailFragment fragment = new ExperienceDetailFragment();
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
        View v = inflater.inflate(R.layout.fragment_experience_detail, container, false);

        Bundle arguments = getArguments();
        String title = arguments.getString("title");

        recyclerView = v.findViewById(R.id.rvList);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        final DbHelper dbHelper = new DbHelper(getContext());

        List<Experience> experienceList = dbHelper.getDetailExperiences();

        adapter = new ExperienceDetailAdapter(this, experienceList, new ExperienceDetailAdapter.ExperienceEditListener() {
            @Override
            public void onClick(String title) {

            }

            @Override
            public void onButtonClick(String title) {
                Intent intent = new Intent(getContext(), ExperienceEdit.class);
                intent.putExtra(ExperienceEdit.CODE_EXTRA, title);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return v;
    }
}
