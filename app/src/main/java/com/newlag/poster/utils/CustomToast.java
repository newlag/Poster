package com.newlag.poster.utils;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.newlag.poster.R;

public class CustomToast {

    public static final int TOAST_ERROR = 1;
    public static final int TOAST_OK = 2;

    public static void showToast(Activity activity, String message, int code) {
        View layout;
        switch (code) {
            case TOAST_ERROR:
                layout = activity.getLayoutInflater().inflate(R.layout.toast_error, (ViewGroup) activity.findViewById(R.id.toast_root));
            break;
            default:
                layout = activity.getLayoutInflater().inflate(R.layout.toast_success, (ViewGroup) activity.findViewById(R.id.toast_root));
        }

        TextView textView = layout.findViewById(R.id.toast_text);
        textView.setText(message);
        Toast toast = new Toast(activity.getApplicationContext());
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
