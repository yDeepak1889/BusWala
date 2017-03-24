package com.example.ydeepak.BusWala;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.activity_setting1, new SettingDetails())
                    .commit();
        }
    }

    public static class SettingDetails extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.pref_settings);

            //Preference connectionPref = findPreference("check");
            Preference connectionPref1 = findPreference("busName");
            // Set summary to be the user-description for the selected value
            //connectionPref.setSummary(PreferenceManager.getDefaultSharedPreferences(getActivity()).getBoolean("check", false));
            connectionPref1.setSummary(PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("busName", ""));

        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            //Log.i("dekh", "call to ho raha h ");
            if (key.equals("check")) {
                Preference connectionPref = findPreference("check");
                // Set summary to be the user-description for the selected value
                //connectionPref.setSummary(PreferenceManager.getDefaultSharedPreferences(getActivity()).getBoolean("check", false));
            } else if (key.equals("busName")) {
                Preference connectionPref = findPreference("busName");
                connectionPref.setSummary(PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("busName", ""));
            }
        }


        @Override
        public void onResume() {
            super.onResume();
            // Set up a listener whenever a key changes
            getPreferenceScreen().getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            // Unregister the listener whenever a key changes
            getPreferenceScreen().getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(this);
        }
    }
}
