package com.leandrofavarin.podcasts;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

public class UserPreferences {

    private static final String FILE_NAME = "user_settings";
    private static final String PREF_SELECTED_COUNTRY = "selected_country";

    private final SharedPreferences preferences;

    public UserPreferences(Context context) {
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public boolean hasChooseCountry() {
        return preferences.contains(PREF_SELECTED_COUNTRY);
    }

    public String getChoosenCountry() {
        return preferences.getString(PREF_SELECTED_COUNTRY, Locale.getDefault().getCountry());
    }

    public void setChoosenCountry(String countryValue) {
        preferences.edit().putString(PREF_SELECTED_COUNTRY, countryValue).apply();
    }
}
