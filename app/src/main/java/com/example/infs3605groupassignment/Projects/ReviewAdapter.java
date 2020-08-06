package com.example.infs3605groupassignment.Projects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3605groupassignment.Objects.User;
import com.example.infs3605groupassignment.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private ReviewFragment parentActivity;
    private List<User> userList;
    private ReviewAdapter.ReviewClickListener listener;

    private String TAG = "REVIEW_ADAPTER";

    public ReviewAdapter (ReviewFragment parentActivity, List<User> userList, ReviewAdapter.ReviewClickListener listener) {
        this.parentActivity = parentActivity;
        this.userList = userList;
        this.listener = listener;
    }

    public interface ReviewClickListener {
        void onReject (int id);
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name, email;
        private CircleImageView dp;
        private ReviewClickListener listener;
        private ImageView reject;

        public ReviewViewHolder(View v, final ReviewClickListener listener) {
            super(v);
            this.listener = listener;
            v.setOnClickListener(this);

            dp = v.findViewById(R.id.userDp);
            name = v.findViewById(R.id.tvUsername);
            email = v.findViewById(R.id.tvUserEmail);
            reject = v.findViewById(R.id.imReject);
            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onReject(getSpecID(getAdapterPosition()));
                }
            });

        }

        @Override
        public void onClick (View view) {
            if(view.getId() == R.id.imReject) {
                listener.onReject(getSpecID(getAdapterPosition()));
            }
        }
    }

    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_list_row, parent, false);
        return new ReviewViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        User user = userList.get(position);
        holder.name.setText(user.getFirstName() + " " + user.getLastName());
        holder.email.setText(user.getEmail());
        holder.dp.setImageResource(R.drawable.avatar2);
    }

    @Override
    public int getItemCount() { return userList.size(); }

    public int getSpecID(int position) { return userList.get(position).getID(); }





}
