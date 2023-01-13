package com.example.vgc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginclient.EventResponse;
import com.example.loginclient.NewResponse;
import com.example.loginclient.UserRequest;
import com.example.loginclient.loginApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.JsonElement;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends AppCompatActivity {
    
    BottomNavigationView bottomNavigationView;
    ArrayList<EventResponse> listEvent = new ArrayList<>();
    private ViewPager2 viewPager2;
    private TextView committee1;
    private ImageView logo1;
    private TextView datetime1;
    private TextView description1;
    private CardView view1;
    private CardView view2;
    private CardView view3;
    private TextView committee2;
    private ImageView logo2;
    private TextView datetime2;
    private TextView description2;
    private TextView committee3;
    private ImageView logo3;
    private TextView datetime3;
    private TextView description3;


    private Handler sliderHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


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

    getEvents(jsessionid);
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
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("user_name",name1);
                        bundle1.putString("user_id",id1);
                        bundle1.putString("user_email",email1);
                        bundle1.putString("user_number",number1);
                        bundle1.putString("cookie",jsessionid);
                        Intent info1 = new Intent(getApplicationContext(), BalancePage.class);
                        info1.putExtras(bundle1);
                        startActivity(info1);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.application_form_nav:
                        Bundle bundle = new Bundle();
                        bundle.putString("user_name",name1);
                        bundle.putString("user_id",id1);
                        bundle.putString("user_email",email1);
                        bundle.putString("user_number",number1);
                        bundle.putString("cookie",jsessionid);
                        Intent info = new Intent(getApplicationContext(), ApplicationPage.class);
                        info.putExtras(bundle);
                        startActivity(info);

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile_nav:

                        Bundle bundle2 = new Bundle();
                        bundle2.putString("user_name",name1);
                        bundle2.putString("user_id",id1);
                        bundle2.putString("user_email",email1);
                        bundle2.putString("user_number",number1);
                        bundle2.putString("cookie",jsessionid);
                        Intent info2 = new Intent(getApplicationContext(), ProfilePage.class);
                        info2.putExtras(bundle2);
                        startActivity(info2);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });
        viewPager2 = findViewById(R.id.viewPagerImageSlider);
        List<SliderItems> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItems(R.drawable.slider_image2));
        sliderItems.add(new SliderItems(R.drawable.slider_image2));
        sliderItems.add(new SliderItems(R.drawable.slider_image1));
        sliderItems.add(new SliderItems(R.drawable.slider_image2));

        viewPager2.setAdapter(new SliderAdapter(sliderItems,viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1-Math.abs(position);
                page.setScaleY(0.90f + r * 0.18f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 5000); // slide duration 2 seconds
            }
        });


    }



    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };
    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 2000);
    }

    public void getEvents(String cookie) {
        Call<List<EventResponse>> eventResponseCall = loginApi.getService().event_details(cookie);
        eventResponseCall.enqueue(new Callback<List<EventResponse>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<EventResponse>> call, Response<List<EventResponse>> response) {
                if (response.isSuccessful()) {
                    List<EventResponse> eventResponse = response.body();
                    System.out.println(eventResponse);
                    String message = "Logged in";

                        EventResponse event = eventResponse.get(0);

                        view1 = findViewById(R.id.view1);
                        committee1 = findViewById(R.id.committee1);
                        datetime1 = findViewById(R.id.datetime1);
                        description1 = findViewById(R.id.description1);
                        logo1 = findViewById(R.id.imageView1);

                        committee1.setText(event.getEventName());
                        datetime1.setText(event.getEventDate()+"  "+event.getEventStartTime()+"-"+event.getEventEndTime());
                        description1.setText(event.getEventDescription());
                        Picasso.get().load(event.getEventFile()).into(logo1);

//                        Bundle bundle = new Bundle();
//                        bundle.putString("event_banner",event.getEventFile());
//                        bundle.putString("event_name",event.getEventName());
//                        bundle.putString("event_desc",event.getEventDescription());
//                        bundle.putString("event_venue",event.getEventVenue());
//                        bundle.putString("event_date",event.getEventDate());
//                        bundle.putString("event_start",event.getEventStartTime());
//                        bundle.putString("event_end",event.getEventEndTime());
//                        bundle.putString("event_contact",event.getEventContact());

                        view1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Bundle bundle = new Bundle();
                                bundle.putString("event_banner",event.getEventFile());
                                bundle.putString("event_name",event.getEventName());
                                bundle.putString("event_desc",event.getEventDescription());
                                bundle.putString("event_venue",event.getEventVenue());
                                bundle.putString("event_date",event.getEventDate());
                                bundle.putString("event_start",event.getEventStartTime());
                                bundle.putString("event_end",event.getEventEndTime());
                                bundle.putString("event_contact",event.getEventContact());
                                Intent info = new Intent(HomePage.this, Event_Desc.class);
                                info.putExtras(bundle);
                                startActivity(info);
                                finish();
                            }
                        });

                    EventResponse event1 = eventResponse.get(1);

                    view2 = findViewById(R.id.view2);
                    committee2 = findViewById(R.id.committee2);
                    datetime2 = findViewById(R.id.datetime2);
                    description2 = findViewById(R.id.description2);
                    logo2 = findViewById(R.id.imageView2);

                    committee2.setText(event1.getEventName());
                    datetime2.setText(event1.getEventDate()+"  "+event1.getEventStartTime()+"-"+event1.getEventEndTime());
                    description2.setText(event1.getEventDescription());
                    Picasso.get().load(event1.getEventFile()).into(logo2);



                    view2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent info = new Intent(HomePage.this, Event_Desc.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("event_banner",event.getEventFile());
                            bundle.putString("event_name",event.getEventName());
                            bundle.putString("event_desc",event.getEventDescription());
                            bundle.putString("event_venue",event.getEventVenue());
                            bundle.putString("event_date",event.getEventDate());
                            bundle.putString("event_start",event.getEventStartTime());
                            bundle.putString("event_end",event.getEventEndTime());
                            bundle.putString("event_contact",event.getEventContact());
                            info.putExtras(bundle);
                            startActivity(info);
                            finish();
                        }
                    });

                    EventResponse event2 = eventResponse.get(2);

                    view3 = findViewById(R.id.view3);
                    committee3 = findViewById(R.id.committee3);
                    datetime3 = findViewById(R.id.datetime3);
                    description3 = findViewById(R.id.description3);
                    logo3 = findViewById(R.id.imageView3);

                    committee3.setText(event2.getEventName());
                    datetime3.setText(event2.getEventDate()+"  "+event2.getEventStartTime()+"-"+event2.getEventEndTime());
                    description3.setText(event2.getEventDescription());
                    Picasso.get().load(event2.getEventFile()).into(logo3);



                    view3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent info = new Intent(HomePage.this, Event_Desc.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("event_banner",event.getEventFile());
                            bundle.putString("event_name",event.getEventName());
                            bundle.putString("event_desc",event.getEventDescription());
                            bundle.putString("event_venue",event.getEventVenue());
                            bundle.putString("event_date",event.getEventDate());
                            bundle.putString("event_start",event.getEventStartTime());
                            bundle.putString("event_end",event.getEventEndTime());
                            bundle.putString("event_contact",event.getEventContact());
                            info.putExtras(bundle);
                            startActivity(info);
                            finish();
                        }
                    });
//                        URL url = null;
//                        try {
//                            url = new URL(event.getEventFile());
//                        } catch (MalformedURLException e) {
//                            e.printStackTrace();
//                        }
//                        Bitmap bmp = null;
//                        try {
//                            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        logo.setImageBitmap(bmp);



//                    assert loginResponse != null;
//                    if(loginResponse.getField().equals("First Login")) {
//                        Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show();
////                        Intent i = new Intent(Login.this, Login_Firsttime.class);
////                        i.putExtra("cookie", jsessionid);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("user_name",response.body().getStudentName());
//                        bundle.putString("user_id",response.body().getStudentCollegeId());
//                        bundle.putString("user_email",response.body().getStudentMailId());
//                        bundle.putString("user_number",response.body().getStudentContactNumber());
//                        bundle.putString("cookie",jsessionid);
//                        Intent info = new Intent(Login.this, Login_Firsttime.class);
//                        Intent info2 = new Intent(Login.this, HomePage.class);
//                        info.putExtras(bundle);
//                        info2.putExtras(bundle);
//                        startActivity(info);
//                        finish();
//                    }
//                    else
//                    {
//                        Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show();
//                        Bundle bundle = new Bundle();
//                        bundle.putString("user_name",response.body().getStudentName());
//                        bundle.putString("user_id",response.body().getStudentCollegeId());
//                        bundle.putString("user_email",response.body().getStudentMailId());
//                        bundle.putString("user_number",response.body().getStudentContactNumber());
//                        bundle.putString("cookie",jsessionid);
//                        Intent info = new Intent(Login.this, HomePage.class);
//                        info.putExtras(bundle);
//                        startActivity(info);
//                        finish();
//                    }
                } else {
                    String message = "Unable to login. An error occured";
                    Toast.makeText(HomePage.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<EventResponse>> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(HomePage.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
