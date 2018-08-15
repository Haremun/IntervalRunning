package com.example.kamil.intervalrunning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Chronometer;

import com.example.kamil.intervalrunning.Gui.ChronometerGui;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.chronometer_main)
    Chronometer chronometer_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ChronometerGui chronometerGui = new ChronometerGui(chronometer_main);
        chronometerGui.setChronometers();

    }
}
