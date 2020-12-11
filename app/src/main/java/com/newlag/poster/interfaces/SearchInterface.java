package com.newlag.poster.interfaces;

import com.newlag.poster.model.User;

import java.util.ArrayList;

public interface SearchInterface {
    void onUsersSearched(ArrayList<User> users);
    void showErrorMessage(int message);
}
