package com.example.infs3605groupassignment.Projects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3605groupassignment.Objects.User;
import com.example.infs3605groupassignment.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CollaboratorSearchAdapter extends RecyclerView.Adapter<CollaboratorSearchAdapter.CollaboratorSearchViewHolder> {
    private CollaboratorSearchFragment parentActivity;
    private List<User> userList;
    private CollaboratorSearchAdapter.CollaboratorSearchClickListener listener;

    private String TAG = "COLLABORATOR_SEARCH_ADAPTER";

    public CollaboratorSearchAdapter (CollaboratorSearchFragment parentActivity, List<User> userList, CollaboratorSearchAdapter.CollaboratorSearchClickListener listener) {
        this.parentActivity = parentActivity;
        this.userList = userList;
        this.listener = listener;
    }

    public interface CollaboratorSearchClickListener {
        void onClick (int id);
        void onButtonClick (int id);
    }

    public class CollaboratorSearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name, email;
        private CircleImageView dp;
        private CollaboratorSearchClickListener listener;
        private ImageButton invite;

        public CollaboratorSearchViewHolder(View v, final CollaboratorSearchClickListener listener) {
            super(v);
            this.listener = listener;
            v.setOnClickListener(this);

            dp = v.findViewById(R.id.imvUserDP);
            name = v.findViewById(R.id.txvUserName);
            email = v.findViewById(R.id.txvUserEmail);
            invite = v.findViewById(R.id.ibtInvite);
            invite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(getSpecID(getAdapterPosition()));
                }
            });

        }

        @Override
        public void onClick (View view) { listener.onClick(getSpecID(getAdapterPosition())); }
    }

    @Override
    public CollaboratorSearchAdapter.CollaboratorSearchViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.collaborator_search_list_row, parent, false);
        return new CollaboratorSearchViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(CollaboratorSearchViewHolder holder, int position) {
        User user = userList.get(position);
        holder.name.setText(user.getFirstName() + " " + user.getLastName());
        holder.email.setText(user.getEmail());
    }

    @Override
    public int getItemCount() { return userList.size(); }

    public void filterList(List<User> filteredList) {
        userList = filteredList;
        notifyDataSetChanged();
    }

    public int getSpecID(int position) { return  userList.get(position).getID(); }



}
