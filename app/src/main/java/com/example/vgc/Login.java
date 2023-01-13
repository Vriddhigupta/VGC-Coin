package com.example.vgc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginclient.UserRequest;
import com.example.loginclient.UserResponse;
import com.example.loginclient.loginApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText user_id = findViewById(R.id.studentId);
        EditText user_password = findViewById(R.id.studentPass);
        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = user_id.getText().toString();
                String password = user_password.getText().toString();

                if(id.equals("")||password.equals(""))
                {
                    Toast.makeText(Login.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
//                    Boolean checkuserpass = DB.checkcredentials(email,password);
//                    Boolean checkuserpass2 = DB2.checkusernamepassword(email,password);
//                    if(checkuserpass==true||checkuserpass2==true)
//                    {
//                        SharedPreferences sharedPreferences = getSharedPreferences(login_activity.PREFS_NAME,0);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putBoolean("hasLoggedIn",true);
//                        editor.commit();
//                        Toast.makeText(login_activity.this,"Sign in successful",Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(login_activity.this,dashboard.class);
//                        startActivity(intent);
//                    }
//                    else
//                    {
//                        Toast.makeText(LoginActivity.this,"Invalid credentials",Toast.LENGTH_SHORT).show();
//                    }
                    UserRequest loginRequest = new UserRequest();
                    loginRequest.setStudentId(user_id.getText().toString());
                    loginRequest.setStudentPassword(user_password.getText().toString());
                    loginUser(loginRequest);
                }
            }
        });

    }


    public void loginUser(UserRequest loginRequest) {
        Call<UserResponse> loginResponseCall = loginApi.getService().loginuser(loginRequest);
        loginResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse loginResponse = response.body();
                    String message = "Logged in";
                    List<String> Cookielist = response.headers().values("Set-Cookie");
                    String jsessionid = (Cookielist .get(0).split(";"))[0];
                    Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Login.this, Login_Firsttime.class);
                    i.putExtra("cookie", jsessionid);
                    startActivity(i);
                    finish();
                } else {
                    String message = "Unable to login. An error occured";
                    Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}