package com.example.phaneendra.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by phaneendra on 03-05-2018.
 */

public class scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView mScannerView;

    String cafteria;
    private FirebaseAuth mAuth;
    private FirebaseUser mcurrent = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("Users").child(mcurrent.getUid());

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner);

    }

    public void onclick(View v) {
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }



    @Override
    public void handleResult(Result result) {


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cafteria = dataSnapshot.child("User").child("cafteria").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //do anything with result here
        Log.v("handleResult", result.getText());
        /*Bundle b =new Bundle();
        b.putString("result",result.getText().toString());
        Intent i = new Intent(this,Main2Activity.class);
        i.putExtras(b);
        startActivity(i);*/

        String ww = result.getText().toString();
        switch (ww) {
            case "LBF":
                Intent initial = new Intent(this, lite.class);
                startActivity(initial);
                break;
            case "DCAFE":
                Intent in = new Intent(this, dcafe.class);
                startActivity(in);
                break;
            default:
                Intent intent = new Intent(this, sorry.class);
                startActivity(intent);
                break;
        }

        /*
        AlertDialog.Builder  builder =  new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();*/
        //mScannerView.resumeCameraPreview(this);
      /*  if (ww.equals("LBF"))
        {
            Intent i=new Intent(this,lbfenter.class);
            startActivity(i);
        }
        else if (ww.equals("DCAFE"))
        {
            Intent i=new Intent(this,dcafeenter.class);
            startActivity(i);;
        }
        else
        {

            Intent i=new Intent(this,Sorrycan.class);
            startActivity(i);
        }
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.logout)
        {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent n = new Intent(this,SignUp.class);
            startActivity(n);
        }
        return super.onOptionsItemSelected(item);
    }
}