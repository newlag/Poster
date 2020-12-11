package com.newlag.poster.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.newlag.poster.R;
import com.newlag.poster.activities.FeedActivity;
import com.newlag.poster.interfaces.RegisterInterface;
import com.newlag.poster.presenters.RegisterPresenter;
import com.newlag.poster.utils.CustomToast;
import com.newlag.poster.utils.LocalDB;

public class RegPassFragment extends Fragment implements View.OnClickListener, RegisterInterface {

    private static final String FULLNAME_EXTRA = "fullname";
    private static final String IMAGE_EXTRA = "image";

    private TextView title;
    private EditText loginInput, passInput;
    private Button nextButton;

    private RegisterPresenter presenter;

    private String fullname, image;

    public static RegPassFragment newInstance(Context context, String fullname, String image) {
        RegPassFragment fragment = new RegPassFragment();
        Bundle args = new Bundle();
        args.putString(FULLNAME_EXTRA, fullname);
        args.putString(IMAGE_EXTRA, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        init(v);
        Bundle args = getArguments();
        fullname = args.getString(FULLNAME_EXTRA);
        image = args.getString(IMAGE_EXTRA);
        return v;
    }

    private void init(View v) {
        title = v.findViewById(R.id.title);
        title.setText(R.string.register_title);
        loginInput = v.findViewById(R.id.login_input);
        passInput = v.findViewById(R.id.pass_input);
        nextButton = v.findViewById(R.id.register_button);
        nextButton.setOnClickListener(this);
        presenter = new RegisterPresenter(this);
    }

    @Override
    public void onClick(View v) {
        presenter.onRegister(fullname, loginInput.getText().toString(), passInput.getText().toString(), image);
    }

    @Override
    public void showErrorMessage(int message) {
        CustomToast.showToast(getActivity(), getString(message), CustomToast.TOAST_ERROR);
    }

    @Override
    public void onUserRegistered(String token) {
        LocalDB localDB = new LocalDB(getContext());
        localDB.update(token);
        startActivity(new Intent(getContext(), FeedActivity.class));
        getActivity().finish();
    }
}
