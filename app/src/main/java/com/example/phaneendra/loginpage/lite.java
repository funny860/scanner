package com.example.phaneendra.loginpage;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class lite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite);
        MediaPlayer ring= MediaPlayer.create(lite.this,R.raw.corr);
        ring.start();
    }
}
