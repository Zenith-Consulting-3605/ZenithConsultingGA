package com.example.infs3605groupassignment.Profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3605groupassignment.R;

import java.util.List;

public class ExperienceDetailAdapter extends RecyclerView.Adapter<ExperienceDetailAdapter.ExperienceDetailViewHolder> {
    private ExperienceDetailFragment parentActivity;
    private List<Experience> experiences;

    public ExperienceDetailAdapter (ExperienceDetailFragment parent, List<Experience> experiences) {
        this.parentActivity = parent;
        this.experiences = experiences;
    }

    public class ExperienceDetailViewHolder extends RecyclerView.ViewHolder {
        private TextView title, company, empType, location, date, description;

        public ExperienceDetailViewHolder(View v) {
            super(v);

            title = v.findViewById(R.id.txvTitle);
            company = v.findViewById(R.id.txvCompany);
            empType = v.findViewById(R.id.txvEmpType);
            location = v.findViewById(R.id.txvLocation);
            date = v.findViewById(R.id.txvDate);
            description = v.findViewById(R.id.txvDescription);
        }
    }

    @Override
    public ExperienceDetailAdapter.ExperienceDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_list_row_detailed, parent, false);
        return new ExperienceDetailViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExperienceDetailViewHolder holder, int position) {
        Experience experience = experiences.get(position);
        holder.title.setText(experience.getTitle());
        holder.empType.setText(experience.getEmploymentType() + " . ");
        holder.company.setText("@ " + experience.getCompany());
        holder.location.setText(experience.getLocation());
        holder.date.setText(experience.getStartDate() + "  -  " + experience.getEndDate());
        holder.description.setText(experience.getDescription());
    }

    @Override
    public int getItemCount() { return experiences.size(); }

}
