package com.nuclode.android.fab;

public class Post {

    int post_image;
    String post_desc;

    public Post() {
    }

    public Post(int post_image, String post_desc) {
        this.post_image = post_image;
        this.post_desc = post_desc;
    }

    public int getPost_image() {
        return post_image;
    }

    public void setPost_image(int post_image) {
        this.post_image = post_image;
    }

    public String getPost_desc() {
        return post_desc;
    }

    public void setPost_desc(String post_desc) {
        this.post_desc = post_desc;
    }
}
