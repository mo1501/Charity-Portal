package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class passwordResetPage extends AppCompatActivity {
    Button loginrsp;
    EditText editTextemail;
    FirebaseAuth Auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset_page);
        loginrsp = findViewById(R.id.button);
        editTextemail = findViewById(R.id.editTextTextEmailAddress3);

        Auth = FirebaseAuth.getInstance();
        loginrsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextemail.getText().toString().trim();
                if(email.isEmpty()){
                    editTextemail.setError("Email is required!");
                    editTextemail.requestFocus();
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editTextemail.setError("Please provide valid email!");
                    editTextemail.requestFocus();
                }
                Auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull  Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(passwordResetPage.this,"Check your email to reset your password",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(passwordResetPage.this,MainActivity.class));
                        }else{
                            Toast.makeText(passwordResetPage.this,"Something went wrong try again",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
   /* public void resetPassword(){
        String email = editTextemail.getText().toString().trim();
        if(email.isEmpty()){
            editTextemail.setError("Email is required!");
            editTextemail.requestFocus();
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextemail.setError("Please provide valid email!");
            editTextemail.requestFocus();
        }
        Auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull  Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(passwordResetPage.this,"Check your email to reset your password",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(passwordResetPage.this,MainActivity.class));
                }else{
                    Toast.makeText(passwordResetPage.this,"Something went wrong try again",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    */
}