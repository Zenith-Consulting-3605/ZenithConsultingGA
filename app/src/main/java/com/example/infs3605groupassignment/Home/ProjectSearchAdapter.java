package com.example.infs3605groupassignment.Home;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3605groupassignment.Objects.Project;
import com.example.infs3605groupassignment.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProjectSearchAdapter extends RecyclerView.Adapter<ProjectSearchAdapter.ProjectSearchViewHolder> {
    private ProjectSearchFragment parentActivity;
    private List<Project> projectList;
    private ProjectSearchAdapter.ProjectSearchClickListener listener;

    private Context context;

    private String TAG = "PROJECT_SEARCH_ADAPTER";

    public ProjectSearchAdapter (Context context, ProjectSearchFragment parentActivity, List<Project> projectList, ProjectSearchAdapter.ProjectSearchClickListener listener) {
        this.parentActivity = parentActivity;
        this.projectList = projectList;
        this.listener = listener;
    }

    public interface ProjectSearchClickListener {
        void onClick (int id);
    }

    public class ProjectSearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name, company, category;
        private ImageView picture;
        private ProjectSearchClickListener listener;

        public ProjectSearchViewHolder(View v , final ProjectSearchClickListener listener) {
            super(v);
            this.listener = listener;
            v.setOnClickListener(this);

            picture = v.findViewById(R.id.imvPSPicture);
            name = v.findViewById(R.id.txvPSName);
            company = v.findViewById(R.id.txvPSCompany);
            category = v.findViewById(R.id.txvPSCategory);
        }

        @Override
        public void onClick (View view) { listener.onClick(getSpecID(getAdapterPosition())); }
    }

    @Override
    public ProjectSearchAdapter.ProjectSearchViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_search_list_row, parent, false);
        return new ProjectSearchViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ProjectSearchViewHolder holder, int position) {
        Project project = projectList.get(position);
        Log.d(TAG, "onBindViewHolder: " + project.getImageURL());


        if(project.getImageURL().equals("NO_RESULTS")) {
            holder.picture.setImageResource(R.drawable.no_image);
        } else {
            Picasso.with(context).load(project.getImageURL()).fit().centerCrop().into(holder.picture);
        }

        holder.name.setText(project.getName());
        holder.company.setText(project.getCategory());
        holder.category.setText(project.getCompany());
    }

    @Override
    public int getItemCount() { return projectList.size(); }

    public void filterList(List<Project> filteredList) {
        projectList = filteredList;
        notifyDataSetChanged();
    }

    public int getSpecID(int position) { return projectList.get(position).getID(); }
}
