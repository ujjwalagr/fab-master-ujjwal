package com.nuclode.android.fab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;


public class CommentActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private int images[] = {R.drawable.image, R.drawable.image, R.drawable.image, R.drawable.image, R.drawable.image,
            R.drawable.image, R.drawable.image, R.drawable.image, R.drawable.image,
            R.drawable.image, R.drawable.image, R.drawable.image, R.drawable.image,
            R.drawable.image, R.drawable.image, R.drawable.image};
    /*private String name[]={"ujjwal","ujjwal","ujjwal","ujjwal",
            "ujjwal","ujjwal","ujjwal","ujjwal",
            "ujjwal","ujjwal","ujjwal","ujjwal",
            "ujjwal","ujjwal","ujjwal","ujjwal"};*/

    private RecyclerView.LayoutManager layoutManager;
    ImageButton commentMenuButton;

    private CommentAdapter recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        recyclerView = findViewById(R.id.recyle);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //commentMenuButton=findViewById(R.id.menuComment);
        recyclerAdapter = new CommentAdapter(images, this);
        recyclerView.setAdapter(recyclerAdapter);
//        commentMenuButton.setOnClickListener

    }
}