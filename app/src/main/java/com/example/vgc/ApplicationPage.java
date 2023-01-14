package com.example.vgc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginclient.AppResponse;
import com.example.loginclient.EventResponse;
import com.example.loginclient.loginApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplicationPage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Button to_add_appli_page;
    private TextView app_title4;
    private TextView app_date4;
    private TextView app_category4;
    private TextView app_status4;

    private TextView app_title5;
    private TextView app_date5;
    private TextView app_category5;
    private TextView app_status5;

    private TextView app_title7;
    private TextView app_date7;
    private TextView app_category7;
    private TextView app_status7;

    private TextView app_title8;
    private TextView app_date8;
    private TextView app_category8;
    private TextView app_status8;

    private CardView view4;
    private CardView view5;
    private CardView view7;
    private CardView view8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_page);

        String name1;
        String email1;
        String id1;
        String number1;
        String jsessionid;
        Bundle intent = getIntent().getExtras();


        name1 = intent.getString("user_name");
        email1 = intent.getString("user_email");
        id1 = intent.getString("user_id");
        number1 = intent.getString("user_number");
        jsessionid = intent.getString("cookie");

        getApplications(jsessionid);
        to_add_appli_page =(Button) findViewById(R.id.to_add_appli_btn);
        to_add_appli_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ApplicationPage.this, AddApplication.class);
                intent.putExtra("cookie", jsessionid);
                startActivity(intent);
            }
        });





        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.application_form_nav);

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
                        return true;

                    case R.id.profile_nav:
                        startActivity(new Intent(getApplicationContext(),ProfilePage.class));
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
    public void getApplications(String cookie) {
        Call<List<AppResponse>> appResponseCall = loginApi.getService().app_details(cookie);
        appResponseCall.enqueue(new Callback<List<AppResponse>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<AppResponse>> call, Response<List<AppResponse>> response) {
                if (response.isSuccessful()) {
                    List<AppResponse> eventResponse = response.body();
                    System.out.println(eventResponse);


                    AppResponse event = eventResponse.get(eventResponse.size()-1);

                    view4 = findViewById(R.id.view4);
                    app_title4 = findViewById(R.id.app_title4);
                    app_date4 = findViewById(R.id.app_date4);
                    app_category4 = findViewById(R.id.app_category4);
                    app_status4 = findViewById(R.id.app_status4);

                    app_title4.setText(event.getStudentApplicationName());
                    app_date4.setText(event.getStudentApplicationDate());
                    app_category4.setText(event.getStudentApplicationCategory());
                    app_status4.setText(event.getStudentApplicationStatus());



                    view4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            bundle.putString("app_name",event.getStudentApplicationName());
                            bundle.putString("app_date",event.getStudentApplicationDate());
                            bundle.putString("app_desc",event.getStudentApplicationDescription());
                            bundle.putString("app_cat",event.getStudentApplicationCategory());
                            bundle.putString("app_stat",event.getStudentApplicationStatus());
                            bundle.putString("app_file",event.getStudentApplicationFile());
                            bundle.putString("app_coins",event.getStudentApplicationIssuedCoins());
                            bundle.putString("app_org",event.getStudentApplicationOrganizer());
                            Intent info = new Intent(ApplicationPage.this, ApplicationViewPage.class);
                            info.putExtras(bundle);
                            startActivity(info);
                            finish();
                        }
                    });

                    AppResponse event1 = eventResponse.get(eventResponse.size()-2);

                    view5 = findViewById(R.id.view5);
                    app_title5 = findViewById(R.id.app_title5);
                    app_date5 = findViewById(R.id.app_date5);
                    app_category5 = findViewById(R.id.app_category5);
                    app_status5 = findViewById(R.id.app_status5);

                    app_title5.setText(event1.getStudentApplicationName());
                    app_date5.setText(event1.getStudentApplicationDate());
                    app_category5.setText(event1.getStudentApplicationCategory());
                    app_status5.setText(event1.getStudentApplicationStatus());



                    view5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            bundle.putString("app_name",event1.getStudentApplicationName());
                            bundle.putString("app_date",event1.getStudentApplicationDate());
                            bundle.putString("app_desc",event1.getStudentApplicationDescription());
                            bundle.putString("app_cat",event1.getStudentApplicationCategory());
                            bundle.putString("app_stat",event1.getStudentApplicationStatus());
                            bundle.putString("app_file",event1.getStudentApplicationFile());
                            bundle.putString("app_coins",event1.getStudentApplicationIssuedCoins());
                            bundle.putString("app_org",event1.getStudentApplicationOrganizer());
                            Intent info = new Intent(ApplicationPage.this, ApplicationViewPage.class);
                            info.putExtras(bundle);
                            startActivity(info);
                            finish();
                        }
                    });

                    AppResponse event2 = eventResponse.get(eventResponse.size()-3);

                    view7 = findViewById(R.id.view7);
                    app_title7 = findViewById(R.id.app_title7);
                    app_date7 = findViewById(R.id.app_date7);
                    app_category7 = findViewById(R.id.app_category7);
                    app_status7 = findViewById(R.id.app_status7);

                    app_title7.setText(event2.getStudentApplicationName());
                    app_date7.setText(event2.getStudentApplicationDate());
                    app_category7.setText(event2.getStudentApplicationCategory());
                    app_status7.setText(event2.getStudentApplicationStatus());



                    view7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            bundle.putString("app_name",event2.getStudentApplicationName());
                            bundle.putString("app_date",event2.getStudentApplicationDate());
                            bundle.putString("app_desc",event2.getStudentApplicationDescription());
                            bundle.putString("app_cat",event2.getStudentApplicationCategory());
                            bundle.putString("appp_stat",event2.getStudentApplicationStatus());
                            bundle.putString("app_file",event2.getStudentApplicationFile());
                            bundle.putString("app_coins",event2.getStudentApplicationIssuedCoins());
                            bundle.putString("app_org",event2.getStudentApplicationOrganizer());
                            Intent info = new Intent(ApplicationPage.this, ApplicationViewPage.class);
                            info.putExtras(bundle);
                            startActivity(info);
                            finish();
                        }
                    });

//                    AppResponse event3 = eventResponse.get(3);
//
//                    view8 = findViewById(R.id.view8);
//                    app_title8 = findViewById(R.id.app_title8);
//                    app_date8 = findViewById(R.id.app_date8);
//                    app_category8 = findViewById(R.id.app_category8);
//                    app_status8 = findViewById(R.id.app_status8);
//
//                    app_title8.setText(event3.getStudentApplicationName());
//                    app_date8.setText(event3.getStudentApplicationDate());
//                    app_category8.setText(event3.getStudentApplicationCategory());
//                    app_status8.setText(event3.getStudentApplicationStatus());
//
//
//
//                    view8.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Bundle bundle = new Bundle();
//                            bundle.putString("app_name",event3.getStudentApplicationName());
//                            bundle.putString("app_date",event3.getStudentApplicationDate());
//                            bundle.putString("app_desc",event3.getStudentApplicationDescription());
//                            bundle.putString("app_cat",event3.getStudentApplicationCategory());
//                            bundle.putString("appp_stat",event3.getStudentApplicationStatus());
//                            bundle.putString("app_file",event3.getStudentApplicationFile());
//                            bundle.putString("app_coins",event3.getStudentApplicationIssuedCoins());
//                            bundle.putString("app_org",event3.getStudentApplicationOrganizer());
//                            Intent info = new Intent(ApplicationPage.this, ApplicationViewPage.class);
//                            info.putExtras(bundle);
//                            startActivity(info);
//                            finish();
//                        }
//                    });

                } else {
                    String message = "Unable to login. An error occured";
                    Toast.makeText(ApplicationPage.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<AppResponse>> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(ApplicationPage.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

}