package com.example.app4.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {

    //SharedPreferences guarda informações
    private SharedPreferences mSharedPreferences;

    //Construtor
    public SecurityPreferences(Context mContext)
    {
        this.mSharedPreferences = mContext.getSharedPreferences("FestaFimAno", Context.MODE_PRIVATE);
    }

    public void storeString(String key, String value)
    {
        this.mSharedPreferences.edit().putString(key, value).apply();
    }

    public String getStoredString(String key)
    {
        return this.mSharedPreferences.getString(key, "");
    }
}
