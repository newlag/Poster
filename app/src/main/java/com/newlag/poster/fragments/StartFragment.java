package com.newlag.poster.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.newlag.poster.R;

public class StartFragment extends Fragment implements View.OnClickListener {

    private Button registerButton, authButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_start_page, container, false);
        init(v);
        return v;
    }

    private void init (View v) {
        registerButton = v.findViewById(R.id.register_button);
        registerButton.setOnClickListener(this);
        authButton = v.findViewById(R.id.pass_input);
        authButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_button:
                setFragment(new RegNameFragment());
            break;
            case R.id.pass_input:
                setFragment(new LoginFragment());
            break;
        }
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
