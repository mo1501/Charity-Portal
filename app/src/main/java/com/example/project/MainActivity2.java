package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemReselectedListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static androidx.navigation.Navigation.createNavigateOnClickListener;
import static androidx.navigation.Navigation.findNavController;

public class MainActivity2 extends AppCompatActivity  {

   BottomNavigationView bottomNavigationView;
    final Fragment fragment1 = new HomeFragment();
    final Fragment fragment2 = new FavouritesFragment();
    final Fragment fragment3 = new ProfileFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;
    Fragment selectedFragment = null;

    RecyclerView recyclerView;
    ArrayList<Charities> charitiesArrayList;
    myAdapter myAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog=new ProgressDialog(this);

                /*switch (item.getItemId()) {
                    case R.id.homeFragment:
                        // mTextMessage.setText(R.string.title_home);
                        switchToHome();
                        break;

                    case R.id.favouritesFragment:
                        // mTextMessage.setText(R.string.title_dashboard);
                        switchToFaves();
                        break;

                    case R.id.profileFragment:
                        //  mTextMessage.setText(R.string.title_notifications);
                        switchToProfile();
                        break;
                }
                return true;
            };



                 */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data....");
        progressDialog.show();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        charitiesArrayList = new ArrayList<>();
        myAdapter = new myAdapter(MainActivity2.this,charitiesArrayList);
        recyclerView.setAdapter(myAdapter);
        EventChangeListener();
       /* fm.beginTransaction().add(R.id.homeFragment, fragment1, "1").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.favouritesFragment, fragment2, "2").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.profileFragment,fragment3, "3").commit();


        */
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
                 switch (item.getItemId()) {

                    case R.id.homeFragment:
                        // mTextMessage.setText(R.string.title_home);
                        selectedFragment = new HomeFragment();
                        //fm.beginTransaction().hide(active).show(fragment1).commit();
                        //active = fragment1;
                        break;
                        //switchToHome();


                    case R.id.favouritesFragment:
                        selectedFragment = new FavouritesFragment();
                        break;
                        // mTextMessage.setText(R.string.title_dashboard);
                        //switchToFaves();
                        //break;
                        //fm.beginTransaction().hide(active).show(fragment2).commit();
                        //active = fragment2;
                        //return true;


                     case R.id.profileFragment:
                         selectedFragment = new ProfileFragment();
                         break;
                        //  mTextMessage.setText(R.string.title_notifications);
                        //switchToProfile();
                        //break;
                         //fm.beginTransaction().hide(active).show(fragment3).commit();
                         //active = fragment3;
                         //return true;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment)
                        .commit();
                //return true;
                return true;
            };

        });


    }

public void EventChangeListener(){
        db.collection("Charities").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable  QuerySnapshot value, @Nullable  FirebaseFirestoreException error) {
                if(error != null){
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Firebase error",error.getMessage());
                    return;
                }
                for(DocumentChange dc: value.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED){
                        charitiesArrayList.add(dc.getDocument().toObject(Charities.class));
                    }
                    myAdapter.notifyDataSetChanged();
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                }
            }
        });
}
  /*  public void switchToHome() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.homeFragment, HomeFragment.class, null).commit();

    }
        public void switchToFaves () {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.favouritesFragment, FavouritesFragment.class, null).commit();
        }
        public void switchToProfile () {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.profileFragment, ProfileFragment.class, null).commit();
        }



   */


}