package com.app.kk.advancecalc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.SwitchPreferenceCompat;

public class SettingsActivity2 extends AppCompatActivity {
    SharedPref sharedpref;
    SwitchPreferenceCompat switchPreferenceCompat;
    public static final String KEY_PREF_MODE="dark";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        sharedpref = new SharedPref(SettingsActivity2.this);
        if(sharedpref.loadNightModeState()==true) {
            setTheme(R.style.DarkTheme_Bar);
        }
        else  setTheme(R.style.AppTheme_Bar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);

       // getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment())
       // .commit();

        /*SharedPreferences modePref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                if(modePref.getBoolean(KEY_PREF_MODE, true)){
                    sharedpref.setNightModeState(true);
                    restartApp();

                } else {
                    sharedpref.setNightModeState(false);

                }*/


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
