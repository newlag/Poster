package com.newlag.poster.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.newlag.poster.interfaces.CreateInterface;
import com.newlag.poster.presenters.CreatePresenter;
import com.newlag.poster.utils.CustomToast;
import com.newlag.poster.utils.LocalDB;

public class CreateFragment extends Fragment implements TextWatcher, View.OnClickListener, CreateInterface {

    private EditText textInput;
    private TextView textLength;
    private Button publishButton;

    private CreatePresenter presenter;

    private String token;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        textLength = v.findViewById(R.id.text_length);
        textInput = v.findViewById(R.id.login_input);
        textInput.addTextChangedListener(this);
        publishButton = v.findViewById(R.id.save_button);
        publishButton.setOnClickListener(this);
        presenter = new CreatePresenter(this);
        LocalDB localDB = new LocalDB(getContext());
        token = localDB.getToken();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        textLength.setText(textInput.getText().toString().length() + "/256");
    }

    @Override
    public void afterTextChanged(Editable s) { }

    @Override
    public void onClick(View v) {
        presenter.uploadPost(token, textInput.getText().toString());
    }

    @Override
    public void showErrorMessage(int message) {
        CustomToast.showToast(getActivity(), getString(message), CustomToast.TOAST_ERROR);
    }

    @Override
    public void onPostUploaded() {
        CustomToast.showToast(getActivity(), getString(R.string.post_uploaded), CustomToast.TOAST_OK);
        textInput.setText(null);
    }
}
