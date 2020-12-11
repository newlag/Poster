package com.newlag.poster.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalDB {

    private static final String ACCOUNT_PREFERENCES = "account";
    private static final String ACCOUNT_TOKEN = "token";

    private SharedPreferences account;

    public LocalDB(Context context) {
        account = context.getSharedPreferences(ACCOUNT_PREFERENCES, Context.MODE_PRIVATE);
    }

    public boolean isAuth() {
        return (account.contains(ACCOUNT_TOKEN));
    }

    public String getToken() {
        return account.getString(ACCOUNT_TOKEN, null);
    }

    public void update(String token) {
        account.edit()
            .putString(ACCOUNT_TOKEN, token)
            .apply();
    }
}
