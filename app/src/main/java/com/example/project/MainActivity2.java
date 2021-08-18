package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;

import android.content.ClipData;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemReselectedListener;

import static androidx.navigation.Navigation.createNavigateOnClickListener;
import static androidx.navigation.Navigation.findNavController;

public class MainActivity2 extends AppCompatActivity  {

   BottomNavigationView bottomNavigationView;
    final Fragment fragment1 = new HomeFragment();
    final Fragment fragment2 = new FavouritesFragment();
    final Fragment fragment3 = new ProfileFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

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
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fm.beginTransaction().add(R.id.homeFragment, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.favouritesFragment, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.profileFragment,fragment1, "1").commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
                 switch (item.getItemId()) {
                    case R.id.homeFragment:
                        // mTextMessage.setText(R.string.title_home);
                        fm.beginTransaction().hide(active).show(fragment1).commit();
                        active = fragment1;
                        return true;
                        //switchToHome();
                        //break;

                    case R.id.favouritesFragment:
                        // mTextMessage.setText(R.string.title_dashboard);
                        //switchToFaves();
                        //break;
                        fm.beginTransaction().hide(active).show(fragment2).commit();
                        active = fragment2;
                        return true;


                     case R.id.profileFragment:
                        //  mTextMessage.setText(R.string.title_notifications);
                        //switchToProfile();
                        //break;
                         fm.beginTransaction().hide(active).show(fragment3).commit();
                         active = fragment3;
                         return true;
                }
                //return true;
                return false;
            };

        });


    }


    public void switchToHome() {
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




}