package com.app.kk.advancecalc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    SharedPref sharedpref;
    private Switch theme;
    private CoordinatorLayout coordinatorLayout;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPref(this);
        if(sharedpref.loadNightModeState()==true) {
            setTheme(R.style.DarkTheme_Bar);
        }
        else  setTheme(R.style.AppTheme_Bar);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.actvitiy_settings);

        theme=findViewById(R.id.switch1);
        theme.setContentDescription("Turn it on");

        if (sharedpref.loadNightModeState()==true) {
            theme.setChecked(true);
        }

       theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedpref.setNightModeState(true);
                    restartApp();
                }
                else {
                    sharedpref.setNightModeState(false);
                    restartApp();
                }
            }
        });

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
        Intent i=new Intent(getApplicationContext(),SettingsActivity.class);
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

}
