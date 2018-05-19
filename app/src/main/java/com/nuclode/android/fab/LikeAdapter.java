package com.nuclode.android.fab;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.LikeViewHolder> {

    private int[] images;


    public LikeAdapter(int[] images) {
        this.images = images;
    }

    @NonNull
    @Override
    public LikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.like_presenter, parent, false);
        LikeViewHolder likeViewHolder = new LikeViewHolder(view);
        return likeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LikeViewHolder holder, int position) {
        int image_id = images[position];
        holder.userProfileImage.setImageResource(image_id);
        holder.userName.setText("Shivam Shukla");
        holder.timeOfLikingPost.setText("12 May at 12:15 pm");
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class LikeViewHolder extends RecyclerView.ViewHolder {

        ImageView userProfileImage;
        TextView userName, timeOfLikingPost;

        public LikeViewHolder(final View itemView) {
            super(itemView);
            userProfileImage = itemView.findViewById(R.id.userImageLikeActivity);
            timeOfLikingPost = itemView.findViewById(R.id.timeViewLikeActivity);
            userName = itemView.findViewById(R.id.userNameLikeActivity);

        }
    }
}
