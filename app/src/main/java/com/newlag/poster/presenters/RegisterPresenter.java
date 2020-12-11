package com.newlag.poster.presenters;

import com.newlag.poster.R;
import com.newlag.poster.interfaces.RegisterInterface;
import com.newlag.poster.model.User;
import com.newlag.poster.network.ApiClient;
import com.newlag.poster.network.ApiInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter {

    private RegisterInterface callback;
    private ApiInterface apiService;

    public RegisterPresenter(RegisterInterface callback) {
        this.callback = callback;
        apiService = ApiClient.getInstance().create(ApiInterface.class);
    }

    public void onRegister(String fullname, String username, String password, String image) {
        if (username.length() < 6 || username.length() > 24) {
            callback.showErrorMessage(R.string.username_error);
        } else if (password.length() < 6 || password.length() > 24) {
            callback.showErrorMessage(R.string.password_error);
        } else {
            User user = new User(fullname, username, password, image);
            apiService.regUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    s -> {
                        if (s.equals("server_error")) {
                            callback.showErrorMessage(R.string.server_error);
                        } else if(s.equals("name_used")) {
                            callback.showErrorMessage(R.string.user_already_exist);
                        } else {
                            callback.onUserRegistered(s);
                        }
                    },
                    e -> callback.showErrorMessage(R.string.server_error)
                );
        }
    }
}
