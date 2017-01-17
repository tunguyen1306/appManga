package com.example.tunguyen.manga.view.model;

import android.content.Context;
import android.content.SharedPreferences;


public class Preference {
    private static String PREF_NAME = "pref";
    private static SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);}

    public static void savePreference(Context context) {


        SharedPreferences.Editor edit = getPref(context).edit();
        edit.putInt("IdAdvertRefer", AdvertDto.IdAdvertRefer);
        edit.putString("NameAdvertRefer", AdvertDto.NameAdvertRefer);


        edit.clear();
        edit.commit();
    }
    public static void restorePreference(Context context) {
        AdvertDto.IdAdvertRefer=getPref(context).getInt("IdAdvertRefer",0);
        AdvertDto.NameAdvertRefer=getPref(context).getString("NameAdvertRefer", "");

    }
}
