package com.newlag.poster.activities;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.newlag.poster.R;
import com.newlag.poster.fragments.CreateFragment;
import com.newlag.poster.fragments.PostsListFragment;
import com.newlag.poster.fragments.SearchFragment;
import com.newlag.poster.fragments.SettingsFragment;

public class FeedActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigation;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        init();
    }

    private void init() {
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        toolbar = findViewById(R.id.toolbar);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (item.getItemId()) {
            case R.id.nav_create:
                toolbar.setTitle(R.string.new_publication);
                fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, new CreateFragment())
                    .commit();
            break;
            case R.id.nav_search:
                toolbar.setTitle(R.string.search_title);
                fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, new SearchFragment())
                    .commit();
            break;
            case R.id.nav_posts:
                toolbar.setTitle(R.string.publications_title);
                fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, new PostsListFragment())
                    .commit();
            break;
            case R.id.nav_settings:
                toolbar.setTitle(R.string.settings_title);
                fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, new SettingsFragment())
                    .commit();
            break;
        }
        return true;
    }
}
