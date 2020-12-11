package com.newlag.poster.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.newlag.poster.R;
import com.newlag.poster.activities.MainActivity;
import com.newlag.poster.interfaces.SettingsInterface;
import com.newlag.poster.model.User;
import com.newlag.poster.presenters.SettingsPresenter;
import com.newlag.poster.utils.CustomToast;
import com.newlag.poster.utils.LocalDB;

public class SettingsFragment extends Fragment implements View.OnClickListener, SettingsInterface, TextWatcher {

    private ImageView avatar;
    private EditText loginInput, fullnameInput;
    private Button saveButton, exitButton;

    private SettingsPresenter presenter;
    private boolean buttonStatus = false;

    private String token;
    private LocalDB localDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        avatar = v.findViewById(R.id.avatar);
        loginInput = v.findViewById(R.id.login_input);
        fullnameInput = v.findViewById(R.id.fullname_input);
        saveButton = v.findViewById(R.id.save_button);
        saveButton.setOnClickListener(this);
        exitButton = v.findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
        presenter = new SettingsPresenter(this);
        localDB = new LocalDB(getContext());
        token = localDB.getToken();
        presenter.loadProfile(token);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_button:
                presenter.updateProfile(loginInput.getText().toString(), fullnameInput.getText().toString(), token);
                switchButton(false);
            break;
            case R.id.exit_button:
                localDB.update(null);
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            break;
        }
    }

    @Override
    public void onProfileLoaded(User user) {
        Glide.with(getContext())
            .load(user.getAvatar())
            .into(avatar);
        loginInput.setText(user.getName());
        loginInput.addTextChangedListener(this);
        fullnameInput.setText(user.getFullname());
        fullnameInput.addTextChangedListener(this);
    }

    @Override
    public void showErrorMessage(int message) {
        CustomToast.showToast(getActivity(), getString(message), CustomToast.TOAST_ERROR);
        switchButton(true);
    }

    @Override
    public void onProfileUpdated() {
        CustomToast.showToast(getActivity(), getString(R.string.profile_updated), CustomToast.TOAST_OK);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!buttonStatus) {
            switchButton(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) { }

    private void switchButton(boolean status) {
        if (status) {
            saveButton.setBackground(getResources().getDrawable(R.drawable.button_accent));
        } else {
            saveButton.setBackground(getResources().getDrawable(R.drawable.button_default));
        }
        saveButton.setEnabled(status);
        buttonStatus = status;
    }
}
