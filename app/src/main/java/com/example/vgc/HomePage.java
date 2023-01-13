package com.example.vgc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomePage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Intent intent = getIntent();
        String jsessionid = intent.getExtras().getString("cookie");

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.home_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home_nav:
                        return true;

                    case R.id.balance_nav:
                        Intent i = new Intent(getApplicationContext(), BalancePage.class);
                        i.putExtra("cookie", jsessionid);
                        startActivity(i);
                        finish();
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.application_form_nav:
                        Intent i2 = new Intent(getApplicationContext(), ApplicationPage.class);
                        i2.putExtra("cookie", jsessionid);
                        startActivity(i2);
                        finish();
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile_nav:
                        Intent i3 = new Intent(getApplicationContext(), ProfilePage.class);
                        i3.putExtra("cookie", jsessionid);
                        startActivity(i3);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });
    }
}