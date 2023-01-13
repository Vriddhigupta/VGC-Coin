package com.example.vgc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BalancePage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_page);
        TextView owner = findViewById(R.id.owner);
        Bundle bundle = getIntent().getExtras();

        String email1;
        String id1;
        String number1;
        Bundle intent = getIntent().getExtras();
        email1 = intent.getString("user_email");
        id1 = intent.getString("user_id");
        number1 = intent.getString("user_number");
        String jsessionid = intent.getString("cookie");
            String name1 = bundle.getString("user_name");
            owner.setText(name1);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.balance_nav);

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
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("user_name",name1);
                        bundle1.putString("user_id",id1);
                        bundle1.putString("user_email",email1);
                        bundle1.putString("user_number",number1);
                        bundle1.putString("cookie",jsessionid);
                        Intent info1 = new Intent(getApplicationContext(), BalancePage.class);
                        info1.putExtras(bundle1);
//                        startActivity(info1);
//                        finish();
                        return true;

                    case R.id.application_form_nav:
                        startActivity(new Intent(getApplicationContext(),ApplicationPage.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile_nav:
                        Bundle bundle = new Bundle();
                        bundle.putString("user_name",name1);
                        bundle.putString("user_id",id1);
                        bundle.putString("user_email",email1);
                        bundle.putString("user_number",number1);
                        bundle.putString("cookie",jsessionid);
                        Intent info = new Intent(getApplicationContext(), ProfilePage.class);
                        info.putExtras(bundle);
                        startActivity(info);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });
    }
}