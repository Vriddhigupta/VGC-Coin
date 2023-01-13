package com.example.vgc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginclient.NewResponse;
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
        Call<NewResponse> loginResponseCall = loginApi.getService().loginuser(loginRequest);
        loginResponseCall.enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                if (response.isSuccessful()) {
                    NewResponse loginResponse = response.body();
                    System.out.println(loginResponse);
                    String message = "Logged in";

                    List<String> Cookielist = response.headers().values("Set-Cookie");
                    String jsessionid = (Cookielist .get(0).split(";"))[0];

                    System.out.println(response.body().getStudentName());



                    assert loginResponse != null;
                    if(loginResponse.getField().equals("First Login")) {
                        Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show();
//                        Intent i = new Intent(Login.this, Login_Firsttime.class);
//                        i.putExtra("cookie", jsessionid);
                        Bundle bundle = new Bundle();
                        bundle.putString("user_name",response.body().getStudentName());
                        bundle.putString("user_id",response.body().getStudentCollegeId());
                        bundle.putString("user_email",response.body().getStudentMailId());
                        bundle.putString("user_number",response.body().getStudentContactNumber());
                        bundle.putString("cookie",jsessionid);
                        Intent info = new Intent(Login.this, Login_Firsttime.class);
                        Intent info2 = new Intent(Login.this, HomePage.class);
                        info.putExtras(bundle);
                        info2.putExtras(bundle);
                        startActivity(info);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show();
                        Bundle bundle = new Bundle();
                        bundle.putString("user_name",response.body().getStudentName());
                        bundle.putString("user_id",response.body().getStudentCollegeId());
                        bundle.putString("user_email",response.body().getStudentMailId());
                        bundle.putString("user_number",response.body().getStudentContactNumber());
                        bundle.putString("cookie",jsessionid);
                        Intent info = new Intent(Login.this, HomePage.class);
                        info.putExtras(bundle);
                        startActivity(info);
                        finish();
                    }
                } else {
                    String message = "Unable to login. An error occured";
                    Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}