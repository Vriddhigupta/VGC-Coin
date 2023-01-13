package com.example.vgc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginclient.RegisterRequest;
import com.example.loginclient.UserResponse;
import com.example.loginclient.loginApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText register_id = findViewById(R.id.registerID);
        Button signup = findViewById(R.id.register);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = register_id.getText().toString();

                RegisterRequest signupRequest = new RegisterRequest();
                signupRequest.setStudentId(register_id.getText().toString());
                registerUser(signupRequest);
            }
        });
    }
    public void registerUser(RegisterRequest signupRequest) {
        Call<UserResponse> signupResponseCall = loginApi.getService().registeruser(signupRequest);
        signupResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse signupResponse = response.body();
                    String message = "Logged in";
                    Toast.makeText(Register.this, message, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Register.this, Login.class));
                    finish();
                } else {
                    String message = "Unable to register. An error occured";
                    Toast.makeText(Register.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(Register.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

}