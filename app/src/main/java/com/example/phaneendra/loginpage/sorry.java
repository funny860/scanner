package com.example.phaneendra.loginpage;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Scanner;

public class sorry extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorry);
        MediaPlayer ring= MediaPlayer.create(sorry.this,R.raw.wrong);
        ring.start();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.Exit)
        {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent n = new Intent(this,Scanner.class);
            startActivity(n);
        }
        return super.onOptionsItemSelected(item);
    }


}
