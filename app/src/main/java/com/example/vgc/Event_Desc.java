package com.example.vgc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Event_Desc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_desc);

        String event_name;
        String event_banner;
        String event_date;
        String event_start;
        String event_end;
        String event_desc;
        String event_venue;
        String event_contact;
        Bundle bundle = getIntent().getExtras();


       event_banner =  bundle.getString("event_banner");
        event_name = bundle.getString("event_name");
        event_desc = bundle.getString("event_desc");
        event_venue = bundle.getString("event_venue");
        event_date = bundle.getString("event_date");
        event_start = bundle.getString("event_start");
        event_end = bundle.getString("event_end");
        event_contact = bundle.getString("event_contact");

        ImageView banner = findViewById(R.id.event_banner);
        TextView name = findViewById(R.id.event_name);
        TextView description = findViewById(R.id.event_description);
        TextView date = findViewById(R.id.event_date);
        TextView time = findViewById(R.id.event_time);
        TextView venue = findViewById(R.id.event_venue);
        TextView contact = findViewById(R.id.event_contact);

        name.setText(event_name);
        description.setText(event_desc);
        date.setText(event_date);
        venue.setText(event_venue);
        time.setText(event_start+" "+"-"+" "+event_end);
        contact.setText(event_contact);


    }

}