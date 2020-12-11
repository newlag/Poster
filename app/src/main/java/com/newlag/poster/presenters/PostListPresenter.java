package com.newlag.poster.presenters;

import com.newlag.poster.R;
import com.newlag.poster.interfaces.PostInterface;
import com.newlag.poster.network.ApiClient;
import com.newlag.poster.network.ApiInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PostListPresenter {

    private PostInterface callback;
    private ApiInterface apiService;

    public PostListPresenter(PostInterface callback) {
        this.callback = callback;
        apiService = ApiClient.getInstance().create(ApiInterface.class);
    }

    public void loadPosts() {
        apiService.loadPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                posts -> callback.onPostsLoaded(posts),
                e -> callback.showErrorMessage(R.string.server_error)
            );
    }
}
