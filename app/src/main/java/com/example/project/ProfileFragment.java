package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    EditText editTextTextfirstNameU1,editTextTextlastnameU1,editTextTextemail1U1,editTextnumberU1;
    private EditText fname,lname,email,number;
    CollectionReference ref;
    Button updatebutton,logout;
    Button pdreset;
    EditText editTextemail;
    FirebaseAuth Auth;
    private DatabaseReference mDatabaseFirst_name,mDatabaseUser_lastname,mDatabaseemail,mDatabaseUser_number;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_profile,container,false);
       /* ref = FirebaseFirestore.getInstance().collection("Users");
        editTextTextfirstNameU1= view.findViewById(R.id.editTextTextfirstNameU1);
        editTextTextlastnameU1= view.findViewById(R.id.editTextTextlastnameU1);
        editTextTextemail1U1= view.findViewById(R.id.editTextTextemail1U1);
        editTextnumberU1= view.findViewById(R.id.editTextnumberU1);



        updatebutton = view.findViewById(R.id.button_update);
        fname = editTextTextfirstNameU1.getText().toString().trim();
        lname = editTextTextlastnameU1.getText().toString().trim();
        email = editTextTextemail1U1.getText().toString().trim();
        number = editTextnumberU1.getText().toString().trim();



        */
        /*FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db =FirebaseFirestore.getInstance();
        final String user_id = mAuth.getCurrentUser().getUid();
        DocumentReference docRef = db.collection("Users").document(user_id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

         */
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        //FirebaseFirestore user_id1= FirebaseFirestore.getInstance().document(m).getId();
       // String id = FirebaseDatabase.document(mCurrentUserId).collection("my-chats")
                //.document().getId();
        //String id =ref.document().collection("Users").getId();
       // final String user_id = mAuth.getCurrentUser().getUid();
        fname = (EditText) view.findViewById(R.id.editTextTextfirstNameU1);
        lname = (EditText) view.findViewById(R.id.editTextTextlastnameU1);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        String userid = user.getUid();
        String useremail = user.getEmail();
        String userfname = user.getDisplayName();
        fname.setText(userid);
        lname.setText(useremail);
        //email.setText(userid);

        logout = view.findViewById(R.id.button2);
        pdreset = view.findViewById(R.id.button5);

        pdreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent2 = new Intent(v.getContext(), passwordResetPage.class);
                startActivity(myIntent2);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });
        /*mDatabaseFirst_name = FirebaseDatabase.getInstance().getReference().child("Users").child(id).child("firstname");
        mDatabaseUser_lastname = FirebaseDatabase.getInstance().getReference().child("Users").child(id).child("lastname");
        mDatabaseemail = FirebaseDatabase.getInstance().getReference().child("Users").child(id).child("email");
        mDatabaseUser_number = FirebaseDatabase.getInstance().getReference().child("Users").child(id).child("number");

        mDatabaseFirst_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                fname.setText(dataSnapshot.getValue(String.class), EditText.BufferType.EDITABLE);
                //(dataSnapshot.getValue(String.class));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabaseUser_lastname.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lname.setText(dataSnapshot.getValue(String.class));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabaseemail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                email.setText(dataSnapshot.getValue(String.class));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabaseUser_number.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                number.setText(dataSnapshot.getValue(String.class));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

         */




        // Inflate the layout for this fragment
        return view;
    }

}