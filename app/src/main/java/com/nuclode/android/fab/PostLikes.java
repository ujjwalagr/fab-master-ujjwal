package com.nuclode.android.fab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class PostLikes extends AppCompatActivity {

    private RecyclerView recyclerView;

    private int images[]={R.drawable.image,R.drawable.image,R.drawable.image,R.drawable.image,R.drawable.image,
            R.drawable.image,R.drawable.image,R.drawable.image,R.drawable.image,
            R.drawable.image,R.drawable.image,R.drawable.image,R.drawable.image,
            R.drawable.image,R.drawable.image,R.drawable.image};

    //    private int images[]={
//            R.drawable.ic_launcher_background};
    private RecyclerView.LayoutManager layoutManager;

    private LikeAdapter likeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_likes);
        recyclerView= findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        LikeAdapter likeAdapter = new LikeAdapter(images);
        recyclerView.setAdapter(likeAdapter);
    }
}