package com.example.infs3605groupassignment.Profiles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3605groupassignment.Objects.Project;
import com.example.infs3605groupassignment.R;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {
    private ProjectFragment parentActivity;
    private List<Project> projects;
    private ProjectClickListener listener;

    private String TAG = "PROJECT_ADAPTER";

    public ProjectAdapter (ProjectFragment parentActivity, List<Project> projects, ProjectClickListener listener) {
        this.parentActivity = parentActivity;
        this.projects = projects;
        this.listener = listener;
    }

    public interface ProjectClickListener {
        void onClick (String title);
    }

    public class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name, company, category;
        private ImageView cover;
        private ProjectClickListener listener;

        public ProjectViewHolder(View v, ProjectClickListener listener) {
            super(v);
            this.listener = listener;
            v.setOnClickListener(this);

            name = v.findViewById(R.id.txvProjName);
            company = v.findViewById(R.id.txvProjCompany);
            category = v.findViewById(R.id.txvProjCategory);
            cover = v.findViewById(R.id.imvCover);
        }

        @Override
        public void onClick (View view) { listener.onClick(getSpecTitle(getAdapterPosition())); }

    }

    @Override
    public ProjectAdapter.ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_row, parent, false);
        return new ProjectViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        Project project = projects.get(position);
        holder.name.setText(project.getName());
        holder.company.setText(project.getCompany());
        holder.category.setText(project.getCategory());

        String prog = project.getProgress();
        if (prog.equals("A")) {
            holder.cover.setImageResource(R.drawable.proj_ideation);
        } else if (prog.equals("B")) {
            holder.cover.setImageResource(R.drawable.proj_development);
        } else {
            holder.cover.setImageResource(R.drawable.proj_complete);
        }
    }

    @Override
    public int getItemCount() { return projects.size(); }

    public String getSpecTitle(int position) { return projects.get(position).getName(); }
}
