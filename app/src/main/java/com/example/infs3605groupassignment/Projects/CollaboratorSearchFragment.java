package com.example.infs3605groupassignment.Projects;

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

import com.example.infs3605groupassignment.Database.DbHelper;
import com.example.infs3605groupassignment.Objects.User;
import com.example.infs3605groupassignment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CollaboratorSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollaboratorSearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private CollaboratorSearchAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private String TAG = "COLLABORATOR_SEARCH_FRAGMENT";
    private EditText search;

    public List<User> invitees = new ArrayList<>();
    public onNextPass passer;
    private int userID;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CollaboratorSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CollaboratorSearch.
     */
    // TODO: Rename and change types and number of parameters
    public static CollaboratorSearchFragment newInstance(String param1, String param2) {
        CollaboratorSearchFragment fragment = new CollaboratorSearchFragment();
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
        View v =  inflater.inflate(R.layout.fragment_collaborator_search, container, false);

        search = v.findViewById(R.id.edtSearchUser);
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
            }
        });

        recyclerView = v.findViewById(R.id.searchList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        final DbHelper dbHelper = new DbHelper(getContext());

        userID = getArguments().getInt("userID");
        List<User> userList = dbHelper.getUserList(userID);

        adapter = new CollaboratorSearchAdapter(this, userList, new CollaboratorSearchAdapter.CollaboratorSearchClickListener() {
            @Override
            public void onClick(int id) {
                Log.d(TAG, "Individual is " + id);
                User user = new User(id);
                invitees.add(user);
                passer.onNextPass(invitees);
            }

            @Override
            public void onButtonClick (int id) {
                Log.d(TAG, "Adding Individual " + id);
            }
        });

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return v;
    }

    private void filter(String text) {
        List<User> filteredList = new ArrayList<>();

        final DbHelper dbHelper = new DbHelper(getContext());

        List<User> userList = dbHelper.getUserList(userID);
        for (User user : userList) {
            if (user.getEmail().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(user);
            }
        }
        adapter.filterList(filteredList);
    }

    public interface onNextPass {
        public void onNextPass(List<User> inviteList);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        passer = (onNextPass) context;
    }
}
//STRING WAS: d-----
//STRING WAS: -----