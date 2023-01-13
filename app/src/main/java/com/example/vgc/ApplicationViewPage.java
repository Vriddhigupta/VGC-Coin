package com.example.vgc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ApplicationViewPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_view_page);

        String app_name;
        String app_org;
        String app_cat;
        String app_coins;
        String app_status;
        String app_date;
        String app_description;

        Bundle bundle = getIntent().getExtras();


        app_name =  bundle.getString("app_name");
        app_org = bundle.getString("app_org");
        app_cat = bundle.getString("app_cat");
        app_coins = bundle.getString("app_coins");
        app_status = bundle.getString("app_stat");
        app_date = bundle.getString("app_date");
        app_description = bundle.getString("app_desc");



        TextView name = findViewById(R.id.name_of_event_display);
        TextView organizer = findViewById(R.id.name_of_organizer_disp_form);
        TextView category = findViewById(R.id.category_of_event_disp_form);
        TextView coins = findViewById(R.id.coins_display_in_form);
        TextView status = findViewById(R.id.status_display_form);
        TextView date = findViewById(R.id.date_display_in_form);
        TextView description = findViewById(R.id.description_display_in_form);



        name.setText(app_name);
        description.setText(app_description);
        date.setText(app_date);
        organizer.setText(app_org);
        category.setText(app_cat);
        coins.setText(app_coins);
        status.setText(app_status);



    }
}