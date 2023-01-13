package com.example.vgc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ApplicationPage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_page);

        Intent intent = getIntent();
        String jsessionid = intent.getExtras().getString("cookie");
        System.out.println(jsessionid);

//        Button submit = findViewById(R.id.submit_application);
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.application_form_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home_nav:
                        Intent intent = new Intent(getApplicationContext(),HomePage.class);
                        intent.putExtra("cookie", jsessionid);
                        startActivity(intent);

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.balance_nav:
                        Intent intent2 = new Intent(getApplicationContext(),BalancePage.class);
                        intent2.putExtra("cookie", jsessionid);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.application_form_nav:
                        return true;

                    case R.id.profile_nav:
                        Intent intent1 = new Intent(getApplicationContext(),ProfilePage.class);
                        intent1.putExtra("cookie", jsessionid);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i2 = new Intent(getApplicationContext(), HomePage.class);
//                i2.putExtra("cookie", jsessionid);
//                startActivity(i2);
//                finish();
//            }
//        });
    }
}