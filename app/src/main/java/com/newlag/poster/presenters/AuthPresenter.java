package com.newlag.poster.presenters;

import com.newlag.poster.R;
import com.newlag.poster.interfaces.RegisterInterface;
import com.newlag.poster.model.User;
import com.newlag.poster.network.ApiClient;
import com.newlag.poster.network.ApiInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AuthPresenter {

    private RegisterInterface callback;
    private ApiInterface apiService;

    public AuthPresenter(RegisterInterface callback) {
        this.callback = callback;
        apiService = ApiClient.getInstance().create(ApiInterface.class);
    }

    public void onLogin(String username, String password) {
        User user = new User(username, password);
        apiService.login(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                s -> {
                    if (s.equals("server_error")) {
                        callback.showErrorMessage(R.string.server_error);
                    } else if(s.equals("incorr_user")) {
                        callback.showErrorMessage(R.string.auth_error);
                    } else {
                        callback.onUserRegistered(s);
                    }
                },
                e -> callback.showErrorMessage(R.string.server_error)
            );
    }
}
