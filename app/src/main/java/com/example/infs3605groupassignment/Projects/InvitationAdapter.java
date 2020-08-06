package com.example.infs3605groupassignment.Projects;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3605groupassignment.Database.DbHelper;
import com.example.infs3605groupassignment.Objects.Project;
import com.example.infs3605groupassignment.R;

import java.util.List;

public class InvitationAdapter extends RecyclerView.Adapter<InvitationAdapter.InvitationViewHolder> {
    private InvitationFragment parentActivity;
    private List<Project> invitations;
    private InvitationAdapter.InvitationClickListener listener;

    private String TAG = "INVITATION_ADAPTER";

    public InvitationAdapter (InvitationFragment parentActivity, List<Project> invitations, InvitationAdapter.InvitationClickListener listener) {
        this.parentActivity = parentActivity;
        this.invitations = invitations;
        this.listener = listener;
    }

    public interface InvitationClickListener {
        void onReject(int ID);
        void onAccept(int ID);
    }

    public class InvitationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView content, title;
        private ImageView reject, accept;
        private InvitationClickListener listener;

        public InvitationViewHolder(View v, InvitationClickListener listener) {
            super(v);
            this.listener = listener;

            content = v.findViewById(R.id.txvIC);
            title = v.findViewById(R.id.txvProjOwner);
            reject = v.findViewById(R.id.imvReject);
            accept = v.findViewById(R.id.imvAccept);

            reject.setOnClickListener(this);
            accept.setOnClickListener(this);
        }

        @Override
        public void onClick (View view) {
            switch (view.getId()) {
                case R.id.imvReject:
                    listener.onReject(getSpecID(getAdapterPosition()));
                    break;
                case R.id.imvAccept:
                    listener.onAccept(getSpecID(getAdapterPosition()));
                    break;
            }
        }
    }

    @Override
    public InvitationAdapter.InvitationViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.invitation_list_row, parent, false);
        InvitationViewHolder holder = new InvitationViewHolder(v, listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(InvitationViewHolder holder, int position) {
        Project invitation = invitations.get(position);

        holder.content.setText(invitation.getName());
    }

    @Override
    public int getItemCount() { return invitations.size(); }

    public int getSpecID(int position) { return invitations.get(position).getID(); }

    public void reload(List<Project> reloadedList) {
        invitations = reloadedList;
        notifyDataSetChanged();
    }
}
