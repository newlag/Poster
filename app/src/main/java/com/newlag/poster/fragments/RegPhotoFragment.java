package com.newlag.poster.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.newlag.poster.R;
import com.newlag.poster.adapter.PhotoAdapter;
import com.newlag.poster.interfaces.PhotoAdapterInterface;
import com.newlag.poster.network.ApiClient;
import com.newlag.poster.network.ApiInterface;
import com.newlag.poster.utils.CustomToast;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegPhotoFragment extends Fragment implements PhotoAdapterInterface, View.OnClickListener {

    private static final String FULLNAME_EXTRA = "fullname";
    private String fullname, image;
    private RecyclerView recyclerView;
    private Button nextButton;
    private ApiInterface apiService;

    public static RegPhotoFragment newInstance(Context context, String fullname) {
        RegPhotoFragment fragment = new RegPhotoFragment();
        Bundle args = new Bundle();
        args.putString(FULLNAME_EXTRA, fullname);
        fragment.setArguments(args);
        return fragment;
    }

    private RegPhotoFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reg_photo, container, false);
        fullname = getArguments().getString(FULLNAME_EXTRA);
        init(v);
        loadImages();
        return v;
    }

    private void init(View v) {
        recyclerView = v.findViewById(R.id.images_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        nextButton = v.findViewById(R.id.next_button);
        nextButton.setOnClickListener(this);
        apiService = ApiClient.getInstance().create(ApiInterface.class);
    }

    private void loadImages() {
        apiService.loadImages()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                images -> recyclerView.setAdapter(new PhotoAdapter(images, RegPhotoFragment.this)),
                e -> CustomToast.showToast(getActivity(), getString(R.string.server_error), CustomToast.TOAST_ERROR)
            );
    }

    @Override
    public void onSelected(String url) {
        image = url;
        nextButton.setEnabled(true);
        nextButton.setBackground(getResources().getDrawable(R.drawable.button_accent));
    }

    @Override
    public void unSelected() {
        nextButton.setEnabled(false);
        nextButton.setBackground(getResources().getDrawable(R.drawable.button_default));
    }

    @Override
    public void onClick(View v) {
        setFragment(RegPassFragment.newInstance(getContext(), fullname, image));
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (fm.findFragmentById(R.id.fragment_container) == null) {
            transaction.add(R.id.fragment_container, fragment);
        } else {
            transaction.replace(R.id.fragment_container, fragment);
        }
        transaction.commit();
    }
}
