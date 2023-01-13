package com.example.vgc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class AddApplication extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    Spinner spinner_date_slots;
    private TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_application);

        dateText = findViewById(R.id.date_of_the_event);
        findViewById(R.id.calender_btn).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        spinner_date_slots = (Spinner) findViewById(R.id.event_category_spinner);

        //spinner for category
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.category, R.layout.spinner_view_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_date_slots.setAdapter(adapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + (month+1) + "/" + year;
        dateText.setText(date);
    }
}


//
//
//
//import androidx.annotation.RequiresApi;
//        import androidx.appcompat.app.AppCompatActivity;
//
//        import android.app.DatePickerDialog;
//        import android.os.Build;
//        import android.os.Bundle;
//        import android.view.View;
//        import android.widget.ArrayAdapter;
//        import android.widget.DatePicker;
//        import android.widget.Spinner;
//        import android.widget.TextView;
//
//        import java.util.Calendar;
//
//        import android.app.DatePickerDialog;
//        import android.os.Bundle;
//        import android.view.View;
//        import android.widget.DatePicker;
//        import android.widget.TextView;
//
//        import java.util.Calendar;
//
//public class AddApplication extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
//
//    private TextView dateText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_application);
//        dateText = findViewById(R.id.date_of_the_event);
//
//        findViewById(R.id.calender_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDatePickerDialog();
//            }
//        });
//
//    }
//
//    public void showDatePickerDialog(){
//        DatePickerDialog datePickerDialog = new DatePickerDialog(
//                this,
//                this,
//                Calendar.getInstance().get(Calendar.YEAR),
//                Calendar.getInstance().get(Calendar.MONTH),
//                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
//        datePickerDialog.show();
//    }
//
//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        String date = dayOfMonth + "/" + (month+1) + "/" + year;
//        dateText.setText(date);
//    }
//}
//
