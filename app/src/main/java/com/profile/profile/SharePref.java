package com.profile.profile;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePref {


    // database class
    private static SharePref instance;
    private String PREFRENCE = "ProfileEdit";

    private Context context;
    private SharedPreferences sharedPreferences;

    private String IMAGE = "image";
    private String NAME = "name";
    private String PHONE = "phone";
    private String EMAIL = "email";
    private String DESCRIPTION = "description";
    private String LAST_NAME = "lName";


    public SharePref(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFRENCE, 0);
    }

    public static SharePref getInstance(Context context) {
        if (instance == null) {
            instance = new SharePref(context);
        }
        return instance;
    }


    public void setImage(String image) {
        sharedPreferences.edit().putString(IMAGE, image).apply();
    }

    public String getIMAGE() {
        return sharedPreferences.getString(IMAGE, "");
    }

    public void setLAST_NAME(String last_name) {
        sharedPreferences.edit().putString(LAST_NAME, last_name).apply();
    }

    public String getLAST_NAME() {
        return sharedPreferences.getString(LAST_NAME, "");
    }


    public void setNAME(String name) {
        sharedPreferences.edit().putString(NAME, name).apply();
    }

    public String getNAME() {
        return sharedPreferences.getString(NAME, "");
    }

    public void setPHONE(String phone) {
        sharedPreferences.edit().putString(PHONE, phone).apply();
    }

    public String getPHONE() {
        return sharedPreferences.getString(PHONE, "");
    }


    public String getEMAIL() {
        return sharedPreferences.getString(EMAIL, "");
    }

    public void setEMAIL(String email) {
        sharedPreferences.edit().putString(EMAIL, email).apply();
    }

    public String getDESCRIPTION() {
        return sharedPreferences.getString(DESCRIPTION, "");
    }

    public void setDESCRIPTION(String description) {
        sharedPreferences.edit().putString(DESCRIPTION, description).apply();
    }
}
