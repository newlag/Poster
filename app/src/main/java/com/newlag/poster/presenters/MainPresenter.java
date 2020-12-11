package com.newlag.poster.presenters;

import com.newlag.poster.R;
import com.newlag.poster.interfaces.MainInterface;
import com.newlag.poster.network.ApiClient;
import com.newlag.poster.network.ApiInterface;
import java.util.HashMap;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    private MainInterface callback;
    private ApiInterface apiService;

    public MainPresenter(MainInterface callback) {
        this.callback = callback;
        apiService = ApiClient.getInstance().create(ApiInterface.class);
    }

    public void auth(String token) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("token", token);
        apiService.auth(hashMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                integer -> callback.onUserLogged((integer >= 0)),
                e -> callback.showErrorMessage(R.string.server_error)
            );
    }
}
