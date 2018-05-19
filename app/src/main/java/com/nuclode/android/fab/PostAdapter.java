package com.nuclode.android.fab;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private List<Post> postsList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView postImage;
        public TextView postDesc;
        public ImageButton popUpButton;
        public TextView postLikeCount;
        public TextView commentLikeCount;

        public MyViewHolder(View view) {
            super(view);
            postImage = view.findViewById(R.id.post_image);
            postDesc = view.findViewById(R.id.post_description);
            popUpButton = view.findViewById(R.id.pop_up_button);
            postLikeCount = view.findViewById(R.id.post_like_cnt);
            commentLikeCount = view.findViewById(R.id.post_cmnt_cnt);

            popUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(context, popUpButton);
                    popupMenu.getMenuInflater().inflate(R.menu.post_menu, popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            Toast.makeText(context, "You Clicked: " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    });
                    popupMenu.show();
                }
            });

            postLikeCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PostLikes.class);
                    context.startActivity(intent);
                }
            });

            commentLikeCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, CommentActivity.class));
                }
            });
        }

    }

    public PostAdapter(List<Post> postsList, Context context) {
        this.postsList = postsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Post post = postsList.get(position);
        holder.postDesc.setText(post.getPost_desc());
        if (post.getPost_image() != -1) {
            holder.postImage.setImageResource(post.getPost_image());
        } else {
            holder.postImage.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }
}
