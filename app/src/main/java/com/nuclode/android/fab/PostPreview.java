package com.nuclode.android.fab;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostPreview extends AppCompatActivity {

    ImageView postImageView;
    RecyclerView groupRecycler;

    int image[] = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5};
    String desc[] = {"Post1", "Post2", "Post3", "Post4", "Post5"};

    private List<GroupModalClass> groupList = new ArrayList<>();

    GroupAdapter groupAdapter;

    EditText postDescriptionText;

    Button postButton;

    Bitmap bitmapit;

    private String uploadurl = "http://13.126.75.165/testing/addpost";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_preview);

        postDescriptionText=findViewById(R.id.post_description_post_preview);
        postButton = findViewById(R.id.post_preview_post_button);
        postImageView = findViewById(R.id.post_image_view);
        Intent data1 = getIntent();
        String path = data1.getStringExtra("data");
        bitmapit= BitmapFactory.decodeFile(path);
        postImageView.setImageBitmap(bitmapit);
        groupRecycler = findViewById(R.id.post_preview_group_recyler);
        groupAdapter = new GroupAdapter(groupList, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        groupRecycler.setLayoutManager(layoutManager);
        groupRecycler.setAdapter(groupAdapter);
        prepareGroupData();

    }

    private void prepareGroupData() {
        for (int i = 0; i < 5; i++) {
            GroupModalClass groupModalClass = new GroupModalClass(image[i], desc[i]);
            groupList.add(groupModalClass);
        }
        groupAdapter.notifyDataSetChanged();

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            uploadPost();
            }
        });
    }

    private void uploadPost()
    {
        StringRequest stringRequest= new StringRequest(Request.Method.POST, uploadurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String Response = jsonObject.getString("success");
                    Toast.makeText(PostPreview.this, ""+Response, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Toast.makeText(PostPreview.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("base64",imageToString(bitmapit));
                params.put("userid","5aaa6439b547c8a0edce3557");
                params.put("postdescription",postDescriptionText.getText().toString().trim());
                params.put("groupid","5aaa67a7de1ea073389644d9");
                return params;
            }
        };
        UploadPost.getInstance(PostPreview.this).addToRequestQue(stringRequest);
    }


    public String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }
}
