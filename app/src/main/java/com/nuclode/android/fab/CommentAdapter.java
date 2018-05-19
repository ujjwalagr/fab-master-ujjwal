package com.nuclode.android.fab;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ImageViewHolder> {

    private int[] images;

    private Context context;

    public CommentAdapter(int[] images, Context context) {
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_row, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);
        return imageViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        int image_id = images[position];
        holder.userImage.setImageResource(image_id);
        holder.username.setText("Shivam Shukla");
        holder.timeOfComment.setText("12 May at 12:15 pm");
        holder.comment.setText("helllllloooo");


    }

    @Override
    public int getItemCount() {
        return images.length;
    }


    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView userImage;
        TextView username, timeOfComment, comment;
        ImageButton commentMenuButton;

        public ImageViewHolder(final View itemView) {
            super(itemView);

            commentMenuButton = itemView.findViewById(R.id.menuComment);
            userImage = itemView.findViewById(R.id.userImage);
            username = itemView.findViewById(R.id.nameView);
            timeOfComment = itemView.findViewById(R.id.timeView);
            comment = itemView.findViewById(R.id.commentView);
            commentMenuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(itemView.getContext(), commentMenuButton);
                    popupMenu.getMenuInflater().inflate(R.menu.comment_menu, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            Toast.makeText(itemView.getContext(), "You clicked edit Button", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    });
                    popupMenu.show();
                }
            });

        }
    }
}