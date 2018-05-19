package com.nuclode.android.fab;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.MyViewHolder> {

    private List<GroupModalClass> groupList;
    private Context context;

    public GroupAdapter(List<GroupModalClass> groupList, Context context) {
        this.groupList = groupList;
        this.context = context;
    }

    @NonNull
    @Override
    public GroupAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_group_view, parent, false);

        return new GroupAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.MyViewHolder holder, int position) {
        GroupModalClass groupModalClass = groupList.get(position);
        holder.groupIcon.setImageResource(groupModalClass.getGroupIcon());
        holder.groupText.setText(groupModalClass.getGroupName());
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView groupIcon;
        public TextView groupText;

        public MyViewHolder(View itemView) {
            super(itemView);
            groupIcon = itemView.findViewById(R.id.groupIcon);
            groupText = itemView.findViewById(R.id.groupText);
        }
    }
}
