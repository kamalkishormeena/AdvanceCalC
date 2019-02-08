package com.app.kk.advancecalc;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.DropDownPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class SettingsFragment extends PreferenceFragmentCompat {

    public SwitchPreferenceCompat mode;
    private Context context;


    @Override
    public void onCreatePreferences(Bundle savedInsatance, String rootkey) {

        setPreferencesFromResource(R.xml.preferences, rootkey);
        final SwitchPreferenceCompat mode = (SwitchPreferenceCompat) findPreference("dark");
        Preference share = (Preference) findPreference("share");
        Preference rate = (Preference) findPreference("rate");
        Preference update = (Preference) findPreference("update");
        final CheckBoxPreference night = (CheckBoxPreference) findPreference("night");
        DropDownPreference color = (DropDownPreference) findPreference("color");

        night.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                return false;
            }
        });

        mode.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                check();
                return false;
            }
        });

        mode.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {

                return true;
            }
        });

        share.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                onShare();
                return false;
            }
        });

        rate.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                //onRate();
                return false;
            }
        });

        if(color.getValue()==null) {
            // to ensure we don't get a null value
            // set first value by default
            color.setValueIndex(0);
        }
        color.setSummary(color.getValue().toString());

        color.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                SharedPreferences color = PreferenceManager.getDefaultSharedPreferences(getActivity());
                preference.setSummary(o.toString());


                return false;
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getPreferenceManager().setSharedPreferencesName("settingsPreference");
        getPreferenceManager().setSharedPreferencesMode(Context.MODE_PRIVATE);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void check() {
        SharedPreferences modepref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean isChecked = modepref.getBoolean("dark", false);
        //String mode = sharedPreferences.getString("dark", Boolean.toString(isChecked));
        Toast.makeText(getActivity(), "isChecked : " + isChecked, Toast.LENGTH_LONG).show();
    }

    public void onShare() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "AdvanceCalC");
        intent.putExtra(Intent.EXTRA_TEXT, "Hey check out my app at: https://play.google.com/store/apps/");
        startActivity(Intent.createChooser(intent, "Share with "));
            /*
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                            "Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);*/
    }

    public void onRate() {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    @Override
    public void onResume() {
        super.onResume();

       /* SwitchPreferenceCompat preference = (SwitchPreferenceCompat) findPreference("dark");
        preference.setSummaryOff("Switch off state updated from code");
        preference.setSummaryOn("Switch on state updated from code");*/

      /*  SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean isChecked = sharedPreferences.getBoolean("night", false);
        Toast.makeText(getActivity(), "isChecked : " + isChecked, Toast.LENGTH_LONG).show();
        */

    }

    @Override
    public void onPause() {
        super.onPause();

    }

}
