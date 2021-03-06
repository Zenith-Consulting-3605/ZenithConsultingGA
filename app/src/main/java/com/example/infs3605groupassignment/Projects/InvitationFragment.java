package com.example.infs3605groupassignment.Projects;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.infs3605groupassignment.Database.DbHelper;
import com.example.infs3605groupassignment.Objects.Project;
import com.example.infs3605groupassignment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InvitationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InvitationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private InvitationAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Project> invitationList = new ArrayList<>();

    private String TAG = "INVITATION_FRAGMENT";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InvitationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InvitationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InvitationFragment newInstance(String param1, String param2) {
        InvitationFragment fragment = new InvitationFragment();
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
        View v = inflater.inflate(R.layout.fragment_invitation, container, false);

        final int userID = getArguments().getInt("userID", 0);

        recyclerView = v.findViewById(R.id.rvListInvitation);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        final DbHelper dbHelper = new DbHelper(getContext());

        invitationList = dbHelper.getInvitations(userID);

        adapter = new InvitationAdapter(this, invitationList, new InvitationAdapter.InvitationClickListener() {
            @Override
            public void onReject(int ID) {
                Log.d(TAG, "REJECT WAS PRESSED ON INVITATION FOR PROJECT " + ID);
                Toast.makeText(getContext(), "Invitation was Rejected", Toast.LENGTH_SHORT).show();
                dbHelper.rejectInvitation(ID, userID);
                invitationList.clear();
                invitationList = dbHelper.getInvitations(userID);
                adapter.reload(invitationList);
            }

            @Override
            public void onAccept(int ID) {
                Log.d(TAG, "ACCEPT WAS PRESSED ON INVITATION FOR PROJECT " + ID);
                Toast.makeText(getContext(), "Invitation was Accepted", Toast.LENGTH_SHORT).show();
                dbHelper.acceptInvitation(ID, userID);
                invitationList.clear();
                invitationList = dbHelper.getInvitations(userID);
                adapter.reload(invitationList);
            }
        });

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return v;
    }
}