package com.newlag.poster.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.newlag.poster.R;
import com.newlag.poster.fragments.StartFragment;
import com.newlag.poster.interfaces.MainInterface;
import com.newlag.poster.presenters.MainPresenter;
import com.newlag.poster.utils.CustomToast;
import com.newlag.poster.utils.LocalDB;

public class MainActivity extends AppCompatActivity implements MainInterface {

    private LocalDB localDB;
    private MainPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        presenter = new MainPresenter(this);
        localDB = new LocalDB(getApplicationContext());
        if (localDB.isAuth()) { // Если пользователь уже авторизован на устройстве, то пытаемся синхронизировать это с сервером
            presenter.auth(localDB.getToken());
        } else {
            setFragment(new StartFragment());
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (fm.findFragmentById(R.id.fragment_container) == null) {
            transaction.add(R.id.fragment_container, fragment);
        } else {
            transaction.replace(R.id.fragment_container, fragment);
        }
        transaction.commit();
    }

    @Override
    public void onUserLogged(boolean success) {
        if (success) {
            startActivity(new Intent(MainActivity.this, FeedActivity.class));
            finish();
        } else {
            setFragment(new StartFragment());
        }
    }

    @Override
    public void showErrorMessage(int resource) {
        setFragment(new StartFragment());
        CustomToast.showToast(this, getString(resource), CustomToast.TOAST_ERROR);
    }
}