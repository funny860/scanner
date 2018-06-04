package com.example.phaneendra.loginpage;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class SignUp extends AppCompatActivity {
    private EditText name,email,password,as;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name =(EditText)findViewById(R.id.idno);
        email = (EditText)findViewById(R.id.em);
        password = (EditText)findViewById(R.id.pass);
        as=(EditText)findViewById(R.id.ca);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
    }
    public void signupButtonClicked(View view){
        this.view = view;
        final String name_content,password_content,email_content,cafteria_content;
        name_content = name.getText().toString().trim();
        password_content = password.getText().toString().trim();
        email_content = email.getText().toString().trim();
        cafteria_content = as.getText().toString().trim();

        if(password_content.length()<6)
        {
            Toast.makeText(this,"please enter a valid password",Toast.LENGTH_LONG).show();
        }
        if (!TextUtils.isEmpty(email_content) && !TextUtils.isEmpty(name_content) && !TextUtils.isEmpty(password_content)){
            mAuth.createUserWithEmailAndPassword(email_content,password_content).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        finish();
                        String user_id = mAuth.getCurrentUser().getUid();
                        DatabaseReference current_user_db = mDatabase.child(user_id);
                        current_user_db.child("Id_no").setValue(name_content);
                        current_user_db.child("cafteria").setValue(cafteria_content);
                        startActivity(new Intent(SignUp.this,MainActivity.class));
                    }
                }
            });
        }
        else
        {
            Toast.makeText(this,"your user id or password is incorrect ...please try again",Toast.LENGTH_LONG).show();
        }
    }
    public void loginButtonClicked(View view){
        startActivity(new Intent(SignUp.this,MainActivity.class));
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!= null)
        {
            finish();
            startActivity(new Intent(this,scanner.class));
        }
    }
}
