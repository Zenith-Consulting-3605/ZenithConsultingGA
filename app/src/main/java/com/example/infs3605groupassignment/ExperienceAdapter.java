package com.example.infs3605groupassignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.ExperienceViewHolder> {
    private ExperienceFragment parentActivity;
    private List<Experience> experiences;

    public ExperienceAdapter (ExperienceFragment parent, List<Experience> experiences) {
        this.parentActivity = parent;
        this.experiences = experiences;
    }

    public static class ExperienceViewHolder extends RecyclerView.ViewHolder {
        public TextView title, company, startDate;
        public ImageView image;

        public ExperienceViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.txvTitle);
            company = v.findViewById(R.id.txvCompany);
            startDate = v.findViewById(R.id.txvDate);
            image = v.findViewById(R.id.imvArt);
        }
    }

    @Override
    public ExperienceAdapter.ExperienceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_list_row, parent, false);
        return new ExperienceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExperienceViewHolder holder, int position) {
        Experience experience = experiences.get(position);
        holder.title.setText(experience.getTitle());
        holder.company.setText(experience.getCompany());
        holder.startDate.setText(experience.getStartDate() + "  -  " + experience.getEndDate());
    }

    @Override
    public int getItemCount() { return experiences.size(); }
}
