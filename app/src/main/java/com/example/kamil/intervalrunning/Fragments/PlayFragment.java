package com.example.kamil.intervalrunning.Fragments;


import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import com.example.kamil.intervalrunning.Enums.CurrentActivity;
import com.example.kamil.intervalrunning.Gui.ChronometerGui;
import com.example.kamil.intervalrunning.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends Fragment {


    public PlayFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.chronometer_main)
    Chronometer chronometer_main;
    @BindView(R.id.cardView_communication)
    CardView cardViewCommunication;

    ChronometerGui chronometerGui;
    CurrentActivity currentActivity = CurrentActivity.Stop;

    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_play, container, false);
        unbinder = ButterKnife.bind(this, view);
        chronometerGui = new ChronometerGui(chronometer_main);
        chronometerGui.setChronometers();

        return view;
    }

    @OnClick(R.id.cardView_communication)
    public void onClickCardCommunication(){
        switch (currentActivity){
            case Stop:{
                chronometerGui.startChronometer(chronometer_main.getId());
                currentActivity = CurrentActivity.Walk;
            }
        }
    }

    @OnLongClick(R.id.cardView_communication)
    public boolean onLongClick(){
        currentActivity = CurrentActivity.Stop;
        Vibrator v = (Vibrator) Objects.requireNonNull(getContext()).getSystemService(Context.VIBRATOR_SERVICE);
        AudioManager audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        if (v != null && audioManager != null && audioManager.getRingerMode() != AudioManager.RINGER_MODE_SILENT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(80, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(80);
            }
            chronometerGui.stopChrometer(chronometer_main.getId());
        }
        return true;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
