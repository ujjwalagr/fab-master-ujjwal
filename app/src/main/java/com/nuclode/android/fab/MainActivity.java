package com.nuclode.android.fab;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.vincent.filepicker.Constant;
import com.vincent.filepicker.activity.ImagePickActivity;
import com.vincent.filepicker.filter.entity.ImageFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.vincent.filepicker.activity.ImagePickActivity.IS_NEED_CAMERA;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;

    int image[] = {R.drawable.img1, -1, R.drawable.img2, -1, R.drawable.img3, R.drawable.img4, -1, R.drawable.img5};
    String desc[] = {"Post1", "Post2", "Post3", "Post4", "Post5", "Post6", "Post7", "Post8"};
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private List<Post> postList = new ArrayList<>();

    RecyclerView postRecyclerView;
    PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.upload_layout);
                dialog.setTitle("Add a Post");
                dialog.show();

                dialog.findViewById(R.id.action_camera).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (hasCamera()) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                        } else {
                            Toast.makeText(MainActivity.this, "Device doesn't support camera", Toast.LENGTH_SHORT).show();
                        }
                        dialog.hide();
                    }
                });

                dialog.findViewById(R.id.action_gallery).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(MainActivity.this, ImagePickActivity.class);
                        intent1.putExtra(IS_NEED_CAMERA, true);
                        intent1.putExtra(Constant.MAX_NUMBER, 1);
                        startActivityForResult(intent1, Constant.REQUEST_CODE_PICK_IMAGE);
                        dialog.hide();
                    }
                });

                dialog.findViewById(R.id.action_text).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Text clicked", Toast.LENGTH_SHORT).show();
                        dialog.hide();
                    }
                });
            }
            // });

            //popupMenu.show();
            //setForceShowIcon(popupMenu);
            // }
        });

        postRecyclerView = findViewById(R.id.post_recycler_view);
        postAdapter = new PostAdapter(postList, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        postRecyclerView.setLayoutManager(layoutManager);
        postRecyclerView.setAdapter(postAdapter);

        preparePostData();
    }

    private void preparePostData() {
        for (int i = 0; i < 8; i++) {
            Post post = new Post(image[i], desc[i]);
            postList.add(post);
        }
        postAdapter.notifyDataSetChanged();
    }

    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_IMAGE_CAPTURE:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent(MainActivity.this, PostPreview.class);
                    Bundle extras = data.getExtras();
                    Bitmap photo = (Bitmap) extras.get("data");
                    intent.putExtra("data", photo);
                    startActivity(intent);
                }
                break;
            case Constant.REQUEST_CODE_PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    ArrayList<ImageFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_IMAGE);
                    StringBuilder builder = new StringBuilder();
                    for (ImageFile file : list) {
                        String path = file.getPath();
                        Toast.makeText(this, path, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, PostPreview.class);
                        intent.putExtra("data",path);
                        startActivity(intent);
                    }
                }
                break;
            default:
                Toast.makeText(this, "Unable to perform the operation", Toast.LENGTH_SHORT).show();
        }
    }
}
