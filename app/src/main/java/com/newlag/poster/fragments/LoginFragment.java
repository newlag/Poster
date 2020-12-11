package com.newlag.poster.fragments;

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
import com.newlag.poster.presenters.AuthPresenter;
import com.newlag.poster.utils.CustomToast;
import com.newlag.poster.utils.LocalDB;

public class LoginFragment extends Fragment implements View.OnClickListener, RegisterInterface {

    private TextView title;
    private EditText loginInput, passInput;
    private Button nextButton;

    private AuthPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        title = v.findViewById(R.id.title);
        title.setText(R.string.auth_title);
        loginInput = v.findViewById(R.id.login_input);
        passInput = v.findViewById(R.id.pass_input);
        nextButton = v.findViewById(R.id.register_button);
        nextButton.setOnClickListener(this);
        presenter = new AuthPresenter(this);
    }

    @Override
    public void onClick(View v) {
        presenter.onLogin(loginInput.getText().toString(), passInput.getText().toString());
    }

    @Override
    public void showErrorMessage(int message) {
        CustomToast.showToast(getActivity(), getString(message), CustomToast.TOAST_ERROR);
    }

    @Override
    public void onUserRegistered(String token) {
        startActivity(new Intent(getContext(), FeedActivity.class));
        LocalDB localDB = new LocalDB(getContext());
        localDB.update(token);
        getActivity().finish();
    }
}
