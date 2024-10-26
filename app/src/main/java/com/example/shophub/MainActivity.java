package com.example.shophub;

import android.annotation.SuppressLint;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnview;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnview=findViewById(R.id.bottom);
        bnview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if (id==R.id.cartage_nav){
                    Loadfrag(new cart(),false);

                }
                else if (id==R.id.favorite_nav) {
                    Loadfrag(new favorite(),false);
                }
                else if (id == R.id.profiler_nav) {
                    Loadfrag(new account(),false);

                } else {//home
                    Loadfrag(new Home_Fragment(),true);
                }
                return true;
            }
        });
        bnview.setSelectedItemId(R.id.homepage_nav);




    }
    public  void Loadfrag(Fragment fragment, boolean flag){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction fg=fm.beginTransaction();

        if (flag)
            fg.add(R.id.fg,fragment);
        else
            fg.replace(R.id.fg,fragment);
        fg.commit();


    }
}