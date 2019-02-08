package com.app.kk.advancecalc;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    SharedPreferences mySharedPref, switchPref ;
    public SharedPref(Context context) {
    mySharedPref = context.getSharedPreferences("filename",Context.MODE_PRIVATE);
    switchPref = context.getSharedPreferences("filename",Context.MODE_PRIVATE);

    }

    public void setKey(Boolean mode){
        SharedPreferences.Editor editor = switchPref.edit();
        editor.putBoolean("dark",mode);
        editor.commit();
    }

    public Boolean loadKey(){
        Boolean mode = switchPref.getBoolean("dark",false);
        return mode;
    }

    // this method will save the nightMode State : True or False
    public void setNightModeState(Boolean state) {
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean("NightMode",state);
        editor.commit();
    }
    // this method will load the Night Mode State
    public Boolean loadNightModeState (){
        Boolean state = mySharedPref.getBoolean("NightMode",false);
        return  state;
    }
}
