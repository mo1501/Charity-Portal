package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Registrationpage extends AppCompatActivity {
    EditText editTextFirstName,editTextLastName,
            editTextUserType,editTextEmail,
            editTextPassword,editTextPassword2,editTextNumber;
    Button buttonRegister;
    private FirebaseAuth mAuth;

     //FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationpage);




            editTextFirstName = findViewById(R.id.editTextTextfirstName);
            editTextLastName = findViewById(R.id.editTextTextlastname);
            editTextEmail = findViewById(R.id.editTextTextemail1);
            editTextPassword = findViewById(R.id.editTextTextPassword1);
            editTextPassword2 = findViewById(R.id.editTextTextPassword2);
            editTextNumber = findViewById(R.id.editTextnumber);
            buttonRegister = findViewById(R.id.registerButton2);
        mAuth = FirebaseAuth.getInstance();

            buttonRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String firstname = editTextFirstName.getText().toString().trim();
                    String lastname = editTextLastName.getText().toString().trim();
                    String email = editTextEmail.getText().toString().trim();
                    String password1 = editTextPassword.getText().toString().trim();
                    String password2 = editTextPassword2.getText().toString().trim();
                    String number = editTextNumber.getText().toString().trim();

                    if(firstname.equals("") && lastname.equals("") && firstname.equals("") &&
                            firstname.equals("") && firstname.equals("") )
                    {
                        Toast.makeText(Registrationpage.this, "All field are required", Toast.LENGTH_SHORT).show();
                    }
                    if(!password1.equals(password2))
                    {
                        Toast.makeText(Registrationpage.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                        editTextPassword.requestFocus();
                        editTextPassword2.requestFocus();
                    }
                    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        editTextEmail.setError("Please provide valid email");
                        editTextEmail.requestFocus();
                    }

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    User user = new User (firstname,lastname,email,password2,number);
                    db.collection("Users")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(Registrationpage.this, "Welcome", Toast.LENGTH_SHORT).show();
                                    createUser();
                                    Intent intent1 = new Intent(Registrationpage.this,MainActivity2.class);
                                    startActivity(intent1);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Registrationpage.this, "Not Registered", Toast.LENGTH_SHORT).show();
                                }
                            });
                   /* mAuth.createUserWithEmailAndPassword(email,password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull  Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                User user = new User (firstname,lastname,email,password2,number);
                                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().
                                        getCurrentUser().getUid()).setValue(user)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull  Task<Void> task) {
                                                if(task.isSuccessful()){

                                                    Toast.makeText(Registrationpage.this, "Welcome", Toast.LENGTH_SHORT).show();

                                                    Intent mainactivity2intent = new Intent(Registrationpage.this,MainActivity2.class);
                                                    startActivity(mainactivity2intent);
                                                }else{
                                                    Toast.makeText(Registrationpage.this, "Not Registered1", Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });
                            }else{
                                Toast.makeText(Registrationpage.this, "Not Registered", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
*/
                }
            });

        }


    public void createUser(){
        String email = editTextEmail.getText().toString();
        String pass = editTextPassword2.getText().toString();



                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Registrationpage.this, "Registered Successfully in Auth!!", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(Registrationpage.this,MainActivity2.class);
                                startActivity(intent1);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registrationpage.this, "Registration Error in Auth !!", Toast.LENGTH_SHORT).show();
                    }
                });

    }










        }

