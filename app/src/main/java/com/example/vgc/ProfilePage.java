package com.example.vgc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfilePage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        Button reset = findViewById(R.id.reset_pass);
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.profile_nav);

        Intent intent = getIntent();

        String jsessionid = intent.getExtras().getString("cookie");
        System.out.println(jsessionid);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home_nav:
                        startActivity(new Intent(getApplicationContext(),HomePage.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.balance_nav:
                        startActivity(new Intent(getApplicationContext(),BalancePage.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.application_form_nav:
                        startActivity(new Intent(getApplicationContext(),ApplicationPage.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile_nav:
                        return true;
                }

                return false;
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(getApplicationContext(), Login_Firsttime.class);
                i2.putExtra("cookie", jsessionid);
                startActivity(i2);
                finish();
            }
        });
    }
}