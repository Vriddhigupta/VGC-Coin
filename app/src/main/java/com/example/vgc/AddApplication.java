package com.example.vgc;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginclient.AppRequest;
import com.example.loginclient.UserResponse;
import com.example.loginclient.loginApi;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddApplication extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final int SELECT_PICTURE = 200 ;
    EditText event_name;
    EditText organizer_name;
    EditText description;
    Button submit_app;
    Spinner spinner_date_slots;
    private TextView dateText;
    String date;
    String jsessionid;
    Button upload;
    ImageView IVPreviewImage;
    Uri selectedImageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_application);

        Bundle extras = getIntent().getExtras();
        jsessionid = extras.getString("cookie");


        upload = findViewById(R.id.upload_btn);
        IVPreviewImage = findViewById(R.id.IVPreviewImage);


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        event_name = findViewById(R.id.event_name);
        organizer_name = findViewById(R.id.organizer_name);
        description = findViewById(R.id.description);
        submit_app = findViewById(R.id.submit_app);

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


        submit_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String event_name1 = event_name.getText().toString();
                String organizer_name1 = organizer_name.getText().toString();
                String desc1 = description.getText().toString();
                String cat_text = spinner_date_slots.getSelectedItem().toString();
                AppRequest appRequest = new AppRequest();
                appRequest.setStudentApplicationName(event_name1);
                appRequest.setStudentApplicationDescription(desc1);
                appRequest.setStudentApplicationOrganizer(organizer_name1);
                appRequest.setStudentApplicationDate(date);
                appRequest.setStudentApplicationCategory(cat_text);
                appRequest.setFile(selectedImageUri);
                createApplication(appRequest);
            }
        });

//        TextView imagepath = findViewById(R.id.imagepath);
//        imagepath.setText(selectedImageUri.toString());

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
        date = dayOfMonth + "/" + (month+1) + "/" + year;
        dateText.setText(date);
    }

    public void createApplication(AppRequest appRequest)
    {
        Call<UserResponse> signupResponseCall = loginApi.getService().send_app(jsessionid,appRequest);
        signupResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse signupResponse = response.body();
                    String message = "Submitted";
                    Toast.makeText(AddApplication.this, message, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AddApplication.this, ApplicationPage.class));
                    finish();
                } else {
                    String message = "An error occured";
                    Toast.makeText(AddApplication.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(AddApplication.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        launchSomeActivity.launch(i);
    }
    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap=null;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        IVPreviewImage.setImageBitmap(selectedImageBitmap);
                    }
                }
            });
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
