package com.app.kk.advancecalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;

public class SettingsActivity2 extends AppCompatActivity {
    SharedPref sharedpref;
    public static final String KEY_PREF_MODE="dark";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        sharedpref = new SharedPref(this);
        if(sharedpref.loadNightModeState()==true) {
            setTheme(R.style.DarkTheme_Bar);
        }
        else  setTheme(R.style.AppTheme_Bar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);

       // getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment())
       // .commit();

        SharedPreferences modepref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        if(modepref.getBoolean("dark", true)){
            sharedpref.setNightModeState(true);


        } else {
            sharedpref.setNightModeState(false);

        }
    }


    public void refreshActivity() {
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();

    }

    @Override
    public void onBackPressed() {
        refreshActivity();
        super.onBackPressed();
    }

    public void restartApp(){
        Intent intent = new Intent(SettingsActivity2.this, SettingsActivity2.class);
        startActivity(intent);
        finish();
    }

}
