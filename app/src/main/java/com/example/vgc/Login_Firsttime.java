package com.example.vgc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginclient.ChangeRequest;
import com.example.loginclient.RegisterRequest;
import com.example.loginclient.UserResponse;
import com.example.loginclient.loginApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Firsttime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_firsttime);
        EditText Changed_pass = findViewById(R.id.ChangedPass);
        EditText confirm_pass = findViewById(R.id.confirmPass);
        Button change = findViewById(R.id.Change);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = Changed_pass.getText().toString();
                String id1 = confirm_pass.getText().toString();

                if(id.equals(id1)){
                    ChangeRequest changeRequest = new ChangeRequest();
                    changeRequest.setStudentPassword(confirm_pass.getText().toString());
                    changePass(changeRequest);
                }
            }
        });
    }
    public void changePass(ChangeRequest changeRequest) {
        Intent intent = getIntent();
        String jsessionid = intent.getExtras().getString("cookie");
        Call<UserResponse> signupResponseCall = loginApi.getService().changepassword(jsessionid, changeRequest);
        signupResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse signupResponse = response.body();
                    String message = "Logged in";
                    Toast.makeText(Login_Firsttime.this, message, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Login_Firsttime.this, Login.class));
                    finish();
                } else {
                    String message = "Unable to register. An error occured";
                    Toast.makeText(Login_Firsttime.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(Login_Firsttime.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}