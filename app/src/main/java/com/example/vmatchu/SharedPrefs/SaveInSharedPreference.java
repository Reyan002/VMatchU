package com.example.vmatchu.SharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

public class SaveInSharedPreference {
    Context context;
    static SaveInSharedPreference saveInSharedPreference;

    public SaveInSharedPreference(Context context) {
        this.context = context;
    }

    public static SaveInSharedPreference getInSharedPreference(Context context) {
        if (saveInSharedPreference == null) {
            saveInSharedPreference = new SaveInSharedPreference(context);
        }
        return saveInSharedPreference;
    }

    public void saveCityId(String id){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("city_id",id);
        editor.apply();
    }

    public String getCityId(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("city_id","");
    }

    public void saveAreaId(String id){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("area_id",id);
        editor.apply();
    }

    public String getAreaId(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("area_id","");
    }

    public void saveSubAreaId(String id){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("sub_area_id",id);
        editor.apply();
    }

    public String getSubAreaId(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("sub_area_id","");
    }

    public void saveSectorId(String id){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("sector_id",id);
        editor.apply();
    }

    public String getSectorId(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("sector_id","");
    }

    public void saveUserId(String id){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_id",id);
        editor.apply();
    }

    public String getUserId(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("user_id","");
    }


}
