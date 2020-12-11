package com.newlag.poster.presenters;

import com.newlag.poster.R;
import com.newlag.poster.interfaces.CreateInterface;
import com.newlag.poster.network.ApiClient;
import com.newlag.poster.network.ApiInterface;
import java.util.HashMap;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CreatePresenter {

    private CreateInterface callback;
    private ApiInterface apiService;

    public CreatePresenter(CreateInterface callback) {
        this.callback = callback;
        apiService = ApiClient.getInstance().create(ApiInterface.class);
    }

    public void uploadPost(String token, String message) {
        if (message.length() < 1) {
            callback.showErrorMessage(R.string.empty_message);
            return;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("text", message);
        hashMap.put("token", token);

        apiService.post(hashMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                integer -> {
                    switch (integer) {
                        case 1:
                            callback.onPostUploaded();
                        break;
                        default:
                            callback.showErrorMessage(R.string.server_error);
                    }
                },
                e -> callback.showErrorMessage(R.string.server_error)
            );
    }
}
