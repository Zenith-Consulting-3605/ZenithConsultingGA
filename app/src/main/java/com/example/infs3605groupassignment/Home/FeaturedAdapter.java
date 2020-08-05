package com.example.infs3605groupassignment.Home;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.infs3605groupassignment.Objects.Project;
import com.example.infs3605groupassignment.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {
    private FeaturedFragment parentActivity;
    private List<Project> features;
    private FeaturedAdapter.FeaturedClickListener listener;

    private Context context;

    private String TAG = "FEATURED_ADAPTER";

    public FeaturedAdapter (Context context, FeaturedFragment parentActivity, List<Project> features, FeaturedAdapter.FeaturedClickListener listener) {
        this.parentActivity = parentActivity;
        this.features = features;
        this.listener = listener;
        this.context = context;
    }

    public interface FeaturedClickListener {
        void onClick(int ID);
    }

    public class FeaturedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title, company, category;
        private ImageView image;
        private FeaturedClickListener listener;

        public FeaturedViewHolder(View v, FeaturedClickListener listener) {
            super(v);
            this.listener = listener;

            image = v.findViewById(R.id.imvFeatureImage);
            title = v.findViewById(R.id.txvFeatureTitle);
            company = v.findViewById(R.id.txvFeatureCompany);
            category = v.findViewById(R.id.txvFeatureCategory);
        }

        @Override
        public void onClick (View view) { listener.onClick(getSpecID(getAdapterPosition()));}
    }

    @Override
    public FeaturedAdapter.FeaturedViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_list_row, parent, false);
        FeaturedViewHolder holder = new FeaturedViewHolder(v, listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(FeaturedViewHolder holder, int position) {
        Project feature = features.get(position);

        Picasso.with(context).load(feature.getImageURL()).fit().centerCrop().into(holder.image);
        holder.title.setText(feature.getName());
        holder.company.setText(feature.getCompany());
        holder.category.setText(feature.getCategory());
    }

    @Override
    public int getItemCount() { return features.size(); }

    public int getSpecID(int position) { return features.get(position).getID(); }

}
