package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static androidx.navigation.Navigation.findNavController;

public class MainActivity2 extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
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
        }



    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        BottomNavigationItemView bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }
    public void switchToHome() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.homeFragment, new HomeFragment()).commit();
    }
    public void switchToFaves() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.profileFragment, new FavouritesFragment()).commit();
    }
    public void switchToProfile() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.favouritesFragment, new ProfileFragment()).commit();
    }
}