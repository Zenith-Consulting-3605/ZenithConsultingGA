package com.example.infs3605groupassignment.Projects;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3605groupassignment.Objects.Project;
import com.example.infs3605groupassignment.Profiles.ProjectAdapter;
import com.example.infs3605groupassignment.Profiles.ProjectFragment;
import com.example.infs3605groupassignment.R;

import java.util.List;

public class ProjectManageAdapter extends RecyclerView.Adapter<ProjectManageAdapter.ProjectManageViewHolder> {
    private ProjectManageFragment parentActivity;
    private List<Project> projects;
    private ProjectManageAdapter.ProjectManageClickListener listener;

    private String TAG = "PROJECT_MANAGE_ADAPTER";

    public ProjectManageAdapter (ProjectManageFragment parentActivity, List<Project> projects, ProjectManageAdapter.ProjectManageClickListener listener) {
        this.parentActivity = parentActivity;
        this.projects = projects;
        this.listener = listener;
    }

    public interface ProjectManageClickListener {
        void onEdit (int ID);
    }

    public class ProjectManageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name, funding, company, country, description;
        private ImageView cover;
        private Button edit;
        private ProjectManageClickListener listener;

        public ProjectManageViewHolder(View v, ProjectManageClickListener listener) {
            super(v);
            this.listener = listener;
            v.setOnClickListener(this);

            cover = v.findViewById(R.id.imvCover1);
            name = v.findViewById(R.id.txvProjManageName);
            funding = v.findViewById(R.id.txvProjManageFunding);
            company = v.findViewById(R.id.txvProjManageCompany);
            country = v.findViewById(R.id.txvProjManageCountry);
            description = v.findViewById(R.id.txvProjManageDescription);

            edit = v.findViewById(R.id.btnMPEdit);
            edit.setOnClickListener(this);
        }

        @Override
        public void onClick (View view) {
            if (view.getId() == R.id.btnMPEdit) {
                listener.onEdit(getSpecID(getAdapterPosition()));
            }

        }

    }

    @Override
    public ProjectManageAdapter.ProjectManageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_manage_list_row, parent, false);
        return new ProjectManageViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ProjectManageViewHolder holder, int position) {
        Project project = projects.get(position);
        holder.name.setText(project.getName());
        holder.funding.setText(project.getFunding());
        holder.company.setText(project.getCompany());
        holder.country.setText(project.getCountry());
        holder.description.setText(project.getDescription());

        String prog = project.getProgress();
        if (prog.equals("A")) {
            holder.cover.setBackgroundColor(Color.parseColor("#a798c7"));
        } else if (prog.equals("B")) {
            holder.cover.setBackgroundColor(Color.parseColor("#917eba"));
        } else {
            holder.cover.setBackgroundColor(Color.parseColor("#7b65ac"));
        }
    }

    @Override
    public int getItemCount() { return projects.size(); }

    public int getSpecID(int position) { return projects.get(position).getID(); }
}
