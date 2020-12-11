package com.newlag.poster.interfaces;

import com.newlag.poster.model.Post;
import com.newlag.poster.model.User;

import java.util.ArrayList;

public interface ProfileInterface {

    void onProfileLoaded(User user);
    void onPostsLoaded(ArrayList<Post> posts);
    void showErrorMessage(int message);
}
