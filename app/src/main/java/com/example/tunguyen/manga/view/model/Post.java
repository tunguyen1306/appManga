package com.example.tunguyen.manga.view.model;

/**
 * Created by TuNguyen on 02/19/2017.
 */

public class Post {
    private String postTitle;

    private String postSubTitle;

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostSubTitle() {
        return postSubTitle;
    }

    public void setPostSubTitle(String postSubTitle) {
        this.postSubTitle = postSubTitle;
    }

    public Post(String postTitle, String postSubTitle) {
        this.postTitle = postTitle;
        this.postSubTitle = postSubTitle;
    }
}