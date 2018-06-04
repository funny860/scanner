package com.example.phaneendra.loginpage;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class dcafe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dcafe);
        MediaPlayer ring= MediaPlayer.create(dcafe.this,R.raw.corr);
        ring.start();
    }
}
