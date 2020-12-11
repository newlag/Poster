package com.newlag.poster.interfaces;

import com.newlag.poster.model.User;

public interface SettingsInterface {

    void onProfileLoaded(User user);
    void showErrorMessage(int message);
    void onProfileUpdated();
}
