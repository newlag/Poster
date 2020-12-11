package com.newlag.poster.interfaces;

import com.newlag.poster.model.Post;

import java.util.ArrayList;

public interface PostInterface {

    void onPostsLoaded(ArrayList<Post> posts);
    void showErrorMessage(int message);
}
