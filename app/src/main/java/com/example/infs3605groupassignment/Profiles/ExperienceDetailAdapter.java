package com.example.infs3605groupassignment.Profiles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3605groupassignment.Objects.Experience;
import com.example.infs3605groupassignment.R;

import java.util.List;

public class ExperienceDetailAdapter extends RecyclerView.Adapter<ExperienceDetailAdapter.ExperienceDetailViewHolder> {
    private ExperienceDetailFragment parentActivity;
    private List<Experience> experiences;
    private ExperienceEditListener listener;

    public ExperienceDetailAdapter (ExperienceDetailFragment parent, List<Experience> experiences, ExperienceEditListener listener) {
        this.parentActivity = parent;
        this.experiences = experiences;
        this.listener = listener;
    }

    public interface ExperienceEditListener {
        void onClick (String title);
        void onButtonClick (String title);
    }

    public class ExperienceDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title, company, empType, location, date, description;
        private Button edit;
        private ExperienceEditListener listener;

        public ExperienceDetailViewHolder(View v, final ExperienceEditListener listener) {
            super(v);
            this.listener = listener;
            v.setOnClickListener(this);

            title = v.findViewById(R.id.txvTitle);
            company = v.findViewById(R.id.txvCompany);
            empType = v.findViewById(R.id.txvEmpType);
            location = v.findViewById(R.id.txvLocation);
            date = v.findViewById(R.id.txvDate);
            description = v.findViewById(R.id.txvDescription);
            edit = v.findViewById(R.id.btnEditExp);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onButtonClick(getSpecTitle(getAdapterPosition()));
                }
            });
        }

        @Override
        public void onClick (View view) { listener.onClick(getSpecTitle(getAdapterPosition())); }
    }

    @Override
    public ExperienceDetailAdapter.ExperienceDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_list_row_detailed, parent, false);
        return new ExperienceDetailViewHolder(v, listener);
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

    public String getSpecTitle(int position) { return experiences.get(position).getTitle(); }

}
