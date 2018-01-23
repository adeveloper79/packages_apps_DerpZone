package com.derpcaf.derpzone.fragments;

import com.android.internal.logging.nano.MetricsProto;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ContentResolver; 
import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.support.v14.preference.SwitchPreference;
import com.android.settings.R;

import com.android.settings.SettingsPreferenceFragment;

public class GestureSettings extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {

    private static final String SCREEN_OFF_ANIMATION = "screen_off_animation";
    private ListPreference mScreenOffAnimation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContentResolver resolver = getActivity().getContentResolver(); 

        addPreferencesFromResource(R.xml.derpzone_gestures);
        // Screen Off Animations 
        mScreenOffAnimation = (ListPreference) findPreference(SCREEN_OFF_ANIMATION); 
        int screenOffStyle = Settings.System.getInt(resolver, 
                 Settings.System.SCREEN_OFF_ANIMATION, 0); 
        mScreenOffAnimation.setValue(String.valueOf(screenOffStyle)); 
        mScreenOffAnimation.setSummary(mScreenOffAnimation.getEntry()); 
        mScreenOffAnimation.setOnPreferenceChangeListener(this); 
    }
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        ContentResolver resolver = getActivity().getContentResolver(); 
        if (preference == mScreenOffAnimation) { 
            Settings.System.putInt(getContentResolver(), 
                    Settings.System.SCREEN_OFF_ANIMATION, Integer.valueOf((String) newValue)); 
            int valueIndex = mScreenOffAnimation.findIndexOfValue((String) newValue); 
            mScreenOffAnimation.setSummary(mScreenOffAnimation.getEntries()[valueIndex]); 
            return true; 
        } 
        return false;
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.DERPZONE;
    }

}
