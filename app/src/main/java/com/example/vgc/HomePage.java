package com.example.vgc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager2;
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
}
