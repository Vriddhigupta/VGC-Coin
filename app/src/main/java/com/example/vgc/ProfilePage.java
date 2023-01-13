package com.example.vgc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginclient.NewResponse;
import com.example.loginclient.UserResponse;
import com.example.loginclient.loginApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView name;
    TextView id;
    TextView email;
    TextView phone;
    TextView name_initial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        Button reset = findViewById(R.id.reset_pass);
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.profile_nav);
        name = findViewById(R.id.student_name);
        id = findViewById(R.id.stud_id);
        email = findViewById(R.id.student_email);
        phone = findViewById(R.id.student_number);
        name_initial = findViewById(R.id.name_initial);
        Bundle bundle = getIntent().getExtras();
        if (bundle!= null) {
            String name1 = bundle.getString("user_name");
            String email1 = bundle.getString("user_email");
            String id1 = bundle.getString("user_id");
            String number1 = bundle.getString("user_number");
            name.setText(name1);
            id.setText(id1);
            email.setText(email1);
            phone.setText(number1);
        }
        String jsessionid = bundle.getString("cookie");
        System.out.println(bundle.getString("user_name"));


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

        Button logout = findViewById(R.id.logout);
       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Call<UserResponse> loginResponseCall = loginApi.getService().user_logout(jsessionid);
               loginResponseCall.enqueue(new Callback<UserResponse>() {
                   @Override
                   public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                       if (response.isSuccessful()) {
                           UserResponse loginResponse = response.body();
                           System.out.println(loginResponse);
                           String message = "Logged out";
                           Toast.makeText(ProfilePage.this, message, Toast.LENGTH_LONG).show();
                           Intent i = new Intent(ProfilePage.this, Login.class);
                           startActivity(i);
                           finish();
                       }}
                   @Override
                   public void onFailure(Call<UserResponse> call, Throwable t) {
                       String message = t.getLocalizedMessage();
                       Toast.makeText(ProfilePage.this, message, Toast.LENGTH_LONG).show();
                   }
               });


           }
       });
    }
}