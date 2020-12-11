package com.newlag.poster.presenters;

import com.newlag.poster.R;
import com.newlag.poster.interfaces.SettingsInterface;
import com.newlag.poster.network.ApiClient;
import com.newlag.poster.network.ApiInterface;
import java.util.HashMap;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SettingsPresenter {

    private SettingsInterface callback;
    private ApiInterface apiService;

    public SettingsPresenter(SettingsInterface callback) {
        this.callback = callback;
        apiService = ApiClient.getInstance().create(ApiInterface.class);
    }

    public void updateProfile(String login, String fullname, String token) {
        if (login.length() < 6 || login.length() > 24) {
            callback.showErrorMessage(R.string.username_error);
            return;
        }
        if (fullname.length() < 1 || fullname.length() > 36) {
            callback.showErrorMessage(R.string.nickname_error);
            return;
        }

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", login);
        hashMap.put("fullname", fullname);
        hashMap.put("token", token);

        apiService.updateProfile(hashMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                s -> {
                    if (s.equals("success")) {
                        callback.onProfileUpdated();
                    } else if(s.equals("name_used")) {
                        callback.showErrorMessage(R.string.user_already_exist);
                    } else {
                        callback.showErrorMessage(R.string.server_error);
                    }
                },
                e -> callback.showErrorMessage(R.string.server_error)
            );
    }

    public void loadProfile(String token) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", token);
        apiService.loadUser(hashMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                user -> callback.onProfileLoaded(user),
                e -> callback.showErrorMessage(R.string.server_error)
            );
    }
}
