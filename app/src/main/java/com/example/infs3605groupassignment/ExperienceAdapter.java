package com.example.infs3605groupassignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.ExperienceViewHolder> {
    private ExperienceFragment parentActivity;
    private List<Experience> experiences;
    private ExperienceClickListener listener;

    public ExperienceAdapter (ExperienceFragment parent, List<Experience> experiences, ExperienceClickListener listener) {
        this.parentActivity = parent;
        this.experiences = experiences;
        this.listener = listener;
    }

    public interface ExperienceClickListener {
        void onClick (String title);
        void onButtonClick (String title);
    }

    public class ExperienceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title, company, startDate;
        private Button edit;
        private ImageView image;
        private ExperienceClickListener listener;


        public ExperienceViewHolder(View v, final ExperienceClickListener listener) {
            super(v);
            this.listener = listener;
            v.setOnClickListener(this);

            title = v.findViewById(R.id.txvTitle);
            company = v.findViewById(R.id.txvCompany);
            startDate = v.findViewById(R.id.txvDate);
            image = v.findViewById(R.id.imvArt);
            edit = v.findViewById(R.id.btnEditExp);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onButtonClick(getSpecTitle(getAdapterPosition()));
                }
            });
        }

        @Override
        public void onClick (View view) { listener.onClick(getSpecTitle(getAdapterPosition())); } // WRITE THE METHOD CORRECTLY


    }

    @Override
    public ExperienceAdapter.ExperienceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_list_row, parent, false);
        return new ExperienceViewHolder(v, listener);
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

    public String getSpecTitle(int position) {
        return experiences.get(position).getTitle();
    }
}
