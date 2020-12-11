package com.newlag.poster.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.newlag.poster.R;
import com.newlag.poster.adapter.PostAdapter;
import com.newlag.poster.interfaces.PostInterface;
import com.newlag.poster.model.Post;
import com.newlag.poster.presenters.PostListPresenter;
import com.newlag.poster.utils.CustomToast;
import java.util.ArrayList;

public class PostsListFragment extends Fragment implements PostInterface {

    private RecyclerView postList;
    private PostListPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_post_list, container, false);
        init(v);
        presenter.loadPosts();
        return v;
    }

    private void init(View v) {
        postList = v.findViewById(R.id.post_list);
        postList.setLayoutManager(new LinearLayoutManager(getActivity()));
        presenter = new PostListPresenter(this);
    }

    @Override
    public void onPostsLoaded(ArrayList<Post> posts) {
        postList.setAdapter(new PostAdapter(getActivity(), posts));
    }

    @Override
    public void showErrorMessage(int message) {
        CustomToast.showToast(getActivity(), getString(message), CustomToast.TOAST_ERROR);
    }
}
