package com.example.infs3605groupassignment.Profiles;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3605groupassignment.Objects.Skill;
import com.example.infs3605groupassignment.R;

import java.util.List;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.SkillViewHolder> {
    private SkillFragment parentActivity;
    private List<Skill> skills;
    private SkillAddListener listener;

    public SkillAdapter (SkillFragment parent, List<Skill> skills, SkillAddListener listener) {
        this.parentActivity = parent;
        this.skills = skills;
        this.listener = listener;
    }

    public interface SkillAddListener {
        void onClick (String dummy);
    }

    public class SkillViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        private SkillAddListener listener;

        public SkillViewHolder(View v, final SkillAddListener listener) {
            super(v);
            this.listener = listener;
            v.setOnClickListener(this);
            name = v.findViewById(R.id.txvName);
        }

        @Override
        public void onClick (View view) { listener.onClick(getSpecDummy(getAdapterPosition())); }
    }

    @Override
    public SkillAdapter.SkillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_list_row, parent, false);
        return new SkillViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(SkillViewHolder holder, int position) {
        Skill skill = skills.get(position);
        holder.name.setText(skill.getName());

        if (skill.getName().equals("Add Skill+")) {
            holder.name.setBackgroundResource(R.drawable.skill_add);
            holder.name.setTextColor(Color.parseColor("#5F4B8B"));
        }
    }

    @Override
    public int getItemCount() { return skills.size(); }

    public String getSpecDummy(int position) { return skills.get(position).getName(); }

}
