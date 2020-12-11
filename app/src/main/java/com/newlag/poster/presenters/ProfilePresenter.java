package com.newlag.poster.presenters;

import com.newlag.poster.R;
import com.newlag.poster.interfaces.ProfileInterface;
import com.newlag.poster.model.User;
import com.newlag.poster.network.ApiClient;
import com.newlag.poster.network.ApiInterface;
import java.util.HashMap;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfilePresenter {

    private ProfileInterface callback;
    private ApiInterface apiService;

    public ProfilePresenter(ProfileInterface callback) {
        this.callback = callback;
        apiService = ApiClient.getInstance().create(ApiInterface.class);
    }

    public void loadProfile(String username) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", username);

        apiService.loadProfile(hashMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new SingleObserver<User>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onSuccess(User user) {
                    callback.onProfileLoaded(user);
                }

                @Override
                public void onError(Throwable e) {
                    callback.showErrorMessage(R.string.server_error);
                }
            });
    }

    public void loadPosts(String userId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userId", userId);
        apiService.loadProfilePosts(hashMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                posts -> callback.onPostsLoaded(posts),
                error -> callback.showErrorMessage(R.string.server_error)
            );
    }
}
