package com.newlag.poster.interfaces;

import com.newlag.poster.model.Post;

import java.util.ArrayList;

public interface MainInterface {

    void onUserLogged(boolean success);
    void showErrorMessage(int resource);
}
