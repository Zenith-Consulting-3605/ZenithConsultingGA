package com.example.infs3605groupassignment.Profile;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3605groupassignment.R;

import java.util.List;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.SkillViewHolder> {
    private SkillFragment parentActivity;
    private List<Skill> skills;

    public SkillAdapter (SkillFragment parent, List<Skill> skills) {
        this.parentActivity = parent;
        this.skills = skills;
    }

    public static class SkillViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public SkillViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.txvName);
        }
    }

    @Override
    public SkillAdapter.SkillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_list_row, parent, false);
        return new SkillViewHolder(v);
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

}