package com.example.project;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.FirebaseException;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Charities> charitiesArrayList;
    Context context;
    myAdapter myAdapter1;
    FirebaseFirestore db;
    // ProgressDialog progressDialog=new ProgressDialog(this);
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home,container,false);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);
        //progressDialog=new ProgressDialog(this);
        //progressDialog.setCancelable(false);
        //progressDialog.setMessage("Fetching Data....");
        //progressDialog.show();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new myAdapter(context,charitiesArrayList));
        db = FirebaseFirestore.getInstance();
        EventChangeListener();
        charitiesArrayList = new ArrayList<Charities>();
        myAdapter1 = new myAdapter(context,charitiesArrayList);
        recyclerView.setAdapter(myAdapter1);

        return view;
    }


   private void EventChangeListener() {
       db.collection("Charities").orderBy("Name", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
           @Override
           public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
               if (error != null) {
                   // if(progressDialog.isShowing())
                   //   progressDialog.dismiss();
                   Log.e("Firebase error", error.getMessage());
                   return;
               } else {


                   for (DocumentChange dc : value.getDocumentChanges()) {
                       if (dc.getType() == DocumentChange.Type.ADDED) {
                           charitiesArrayList.add(dc.getDocument().toObject(Charities.class));
                       }
                       myAdapter1.notifyDataSetChanged();
                       //if(progressDialog.isShowing())
                       //  progressDialog.dismiss();
                   }
               }
           }
       });

   }



}