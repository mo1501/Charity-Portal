package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class MainActivity extends AppCompatActivity {
    Button buttonLogin;
    EditText editTextEmail,editTextPassword ;

    //FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = findViewById(R.id.loginButton);
        editTextEmail = findViewById(R.id.editTextTextEmailAddress2);
        editTextPassword = findViewById(R.id.editTextTextPassword2);
    /*
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextEmail.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                    editTextEmail.requestFocus();
                }else if( editTextPassword.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Please enter valid password", Toast.LENGTH_SHORT).show();
                    editTextPassword.requestFocus();
                }
                FirebaseFirestore lg = FirebaseFirestore.getInstance();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                lg.collection("Users")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                    for(QueryDocumentSnapshot doc : task.getResult()){
                                        String a=doc.getString("email");
                                        String b=doc.getString("password");
                                        String a1=editTextEmail.getText().toString().trim();
                                        String b1=editTextPassword.getText().toString().trim();
                                        if(a==a1 && b==b1) {
                                            Intent home = new Intent(MainActivity.this, MainActivity2.class);
                                            startActivity(home);
                                            Toast.makeText(MainActivity.this, "Logged In", Toast.LENGTH_SHORT).show();

                                        }else
                                            Toast.makeText(MainActivity.this, "Cannot login,incorrect Email and Password", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            }
                        });

            }
        });
*/
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });



    }
        public void goToRegisterPage(View v){
        Intent intentr = new Intent(MainActivity.this,Registrationpage.class);
        startActivity(intentr);
        }
        public void changePassword(View v){
        Intent intentp = new Intent(MainActivity.this,passwordResetPage.class);
        startActivity(intentp);
        }
        private void loginUser(){
        String email = editTextEmail.getText().toString();
        String pass = editTextPassword.getText().toString();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!pass.isEmpty()){
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(email , pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(MainActivity.this, "Login Successfully !!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this , MainActivity2.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Login Failed !!", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                editTextPassword.setError("Empty Fields Are not Allowed");
            }
        }else if(email.isEmpty()){
            editTextEmail.setError("Empty Fields Are not Allowed");
        }else{
            editTextEmail.setError("Pleas Enter Correct Email");
        }
    }


}