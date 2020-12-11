package com.newlag.poster.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.newlag.poster.R;
import com.newlag.poster.utils.CustomToast;

public class RegNameFragment extends Fragment implements View.OnClickListener {

    private EditText nameInput;
    private Button nextButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reg_name, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        nameInput = v.findViewById(R.id.login_input);
        nextButton = v.findViewById(R.id.register_button);
        nextButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String name = nameInput.getText().toString();
        if (name.length() > 1 && name.length() <= 36) {
            setFragment(RegPhotoFragment.newInstance(getContext(), name));
        } else {
            CustomToast.showToast(getActivity(), getString(R.string.nickname_error), CustomToast.TOAST_ERROR);
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
