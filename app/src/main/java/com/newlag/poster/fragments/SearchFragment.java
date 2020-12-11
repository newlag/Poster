package com.newlag.poster.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.newlag.poster.R;
import com.newlag.poster.adapter.SearchAdapter;
import com.newlag.poster.interfaces.SearchInterface;
import com.newlag.poster.model.User;
import com.newlag.poster.presenters.SearchPresenter;
import com.newlag.poster.utils.CustomToast;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SearchFragment extends Fragment implements SearchInterface {

    private EditText nameInput;
    private RecyclerView usersList;
    private SearchPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        presenter = new SearchPresenter(this);
        nameInput = v.findViewById(R.id.name_input);
        RxTextView.textChanges(nameInput)
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe(
                charSequence -> presenter.search(nameInput.getText().toString())
            );
        usersList = v.findViewById(R.id.recyclerView);
        usersList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onUsersSearched(ArrayList<User> users) {
        if (usersList.getAdapter() == null) {
            usersList.setAdapter(new SearchAdapter(users));
        } else {
            ((SearchAdapter) usersList.getAdapter()).updateUsersList(users);
        }
    }

    @Override
    public void showErrorMessage(int message) {
        CustomToast.showToast(getActivity(), getString(message), CustomToast.TOAST_ERROR);
    }
}
