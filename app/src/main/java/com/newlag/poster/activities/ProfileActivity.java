package com.newlag.poster.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.newlag.poster.R;
import com.newlag.poster.adapter.PostAdapter;
import com.newlag.poster.interfaces.ProfileInterface;
import com.newlag.poster.model.Post;
import com.newlag.poster.model.User;
import com.newlag.poster.presenters.ProfilePresenter;
import com.newlag.poster.utils.CustomToast;
import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, ProfileInterface {

    private static final String USERNAME_EXTRA = "username";

    private Toolbar toolbar;
    private TextView fullname, username;
    private ImageView avatar;
    private RecyclerView postsList;
    private ProfilePresenter presenter;

    public static Intent newInstance(Context context, String username) {
        Intent i = new Intent(context, ProfileActivity.class);
        i.putExtra(USERNAME_EXTRA, username);
        return i;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Bundle args = getIntent().getExtras();
        if (args == null) {
            Log.e("ProfileActivity", "Не передано имя пользователя!");
            finish();
        }
        init();
        presenter.loadProfile(args.getString(USERNAME_EXTRA));
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.profile_title);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(this);
        presenter = new ProfilePresenter(this);
        postsList = findViewById(R.id.recyclerView);
        postsList.setLayoutManager(new LinearLayoutManager(this));
        fullname = findViewById(R.id.fullname_title);
        username = findViewById(R.id.username_title);
        avatar = findViewById(R.id.user_avatar);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }

    @Override
    public void onProfileLoaded(User user) {
        fullname.setText(user.getFullname());
        username.setText("@" + user.getName());
        Glide.with(this)
            .load(user.getAvatar())
            .into(avatar);
        presenter.loadPosts(user.getUserId());
    }

    @Override
    public void onPostsLoaded(ArrayList<Post> posts) {
        postsList.setAdapter(new PostAdapter(this, posts));
    }

    @Override
    public void showErrorMessage(int message) {
        CustomToast.showToast(this, getString(message), CustomToast.TOAST_ERROR);
    }
}
