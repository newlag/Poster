package com.newlag.poster.presenters;

import com.newlag.poster.R;
import com.newlag.poster.interfaces.SearchInterface;
import com.newlag.poster.network.ApiClient;
import com.newlag.poster.network.ApiInterface;
import java.util.HashMap;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter {

    private SearchInterface callback;
    private ApiInterface apiService;

    public SearchPresenter(SearchInterface callback) {
        this.callback = callback;
        apiService = ApiClient.getInstance().create(ApiInterface.class);
    }

    public void search(String name) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", name);
        apiService.search(hashMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                s -> callback.onUsersSearched(s),
                e -> callback.showErrorMessage(R.string.server_error)
            );
    }
}
