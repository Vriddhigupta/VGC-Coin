package com.example.vgc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginclient.AppResponse;
import com.example.loginclient.BalanceResponse;
import com.example.loginclient.UserResponse;
import com.example.loginclient.loginApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalancePage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Button pay_to_canteen;
    Button pay_to_xerox;
    private EditText amount;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    private Button save;
    private Button cancel;
    private Integer eventResponse;
    private TextView balance;
    private String jsessionid;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_page);
        TextView owner = findViewById(R.id.owner);
        balance = findViewById(R.id.balance);


        pay_to_canteen = findViewById(R.id.pay_by_id);
        pay_to_xerox = findViewById(R.id.pay_by_qr);
        String email1;
        String id1;
        String number1;
        Bundle intent = getIntent().getExtras();
        email1 = intent.getString("user_email");
        id1 = intent.getString("user_id");
        number1 = intent.getString("user_number");
        jsessionid = intent.getString("cookie");
        String name1 = intent.getString("user_name");
        owner.setText(name1);
        getBalance(jsessionid);
        pay_to_canteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               createTransactions_canteen();
               getBalance(jsessionid);
            }
        });
        pay_to_xerox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTransactions_xerox();
                getBalance(jsessionid);
            }
        });
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.balance_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home_nav:
                        startActivity(new Intent(getApplicationContext(), HomePage.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.balance_nav:
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("user_name", name1);
                        bundle1.putString("user_id", id1);
                        bundle1.putString("user_email", email1);
                        bundle1.putString("user_number", number1);
                        bundle1.putString("cookie", jsessionid);
                        Intent info1 = new Intent(getApplicationContext(), BalancePage.class);
                        info1.putExtras(bundle1);
//                        startActivity(info1);
//                        finish();
                        return true;

                    case R.id.application_form_nav:
                        startActivity(new Intent(getApplicationContext(), ApplicationPage.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.profile_nav:
                        Bundle bundle = new Bundle();
                        bundle.putString("user_name", name1);
                        bundle.putString("user_id", id1);
                        bundle.putString("user_email", email1);
                        bundle.putString("user_number", number1);
                        bundle.putString("cookie", jsessionid);
                        Intent info = new Intent(getApplicationContext(), ProfilePage.class);
                        info.putExtras(bundle);
                        startActivity(info);
                        finish();
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }
        });
    }
    public void createTransactions_canteen() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View transactionView = getLayoutInflater().inflate(R.layout.popup, null);
        amount = transactionView.findViewById(R.id.amount);

        save = transactionView.findViewById(R.id.send);
        cancel = transactionView.findViewById(R.id.cancel);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = amount.getText().toString();
                BalanceResponse balanceResponse = new BalanceResponse();
                balanceResponse.setAmount(value);
                Call<UserResponse> appResponseCall = loginApi.getService().send_canteen(jsessionid, balanceResponse);
                appResponseCall.enqueue(new Callback<UserResponse>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                        UserResponse eventResponse = response.body();
                        System.out.println(eventResponse);
                        Toast.makeText(BalancePage.this, "Coin Transferred successfully", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        String message = t.getLocalizedMessage();
                        Toast.makeText(BalancePage.this, message, Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BalancePage.this,BalancePage.class);
                startActivity(i);
            }
        });
        dialogBuilder.setView(transactionView);
        dialog = dialogBuilder.create();
        dialog.show();
    }
    public void createTransactions_xerox() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View transactionView = getLayoutInflater().inflate(R.layout.popup, null);
        amount = transactionView.findViewById(R.id.amount);

        save = transactionView.findViewById(R.id.send);
        cancel = transactionView.findViewById(R.id.cancel);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = amount.getText().toString();
                BalanceResponse balanceResponse = new BalanceResponse();
                balanceResponse.setAmount(value);
                Call<UserResponse> appResponseCall = loginApi.getService().send_xerox(jsessionid, balanceResponse);
                appResponseCall.enqueue(new Callback<UserResponse>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                        UserResponse eventResponse = response.body();
                        System.out.println(eventResponse);
                        Toast.makeText(BalancePage.this, "Coin Transferred successfully", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        String message = t.getLocalizedMessage();
                        Toast.makeText(BalancePage.this, message, Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BalancePage.this,BalancePage.class);
                startActivity(i);
            }
        });
                dialogBuilder.setView(transactionView);
        dialog = dialogBuilder.create();
        dialog.show();
    }

//        dialogBuilder.setView(transactionView);
//        dialog = dialogBuilder.create();
//        dialog.show();
//
//    }
        public void getBalance(String cookie){
            Call<Integer> appResponseCall = loginApi.getService().get_balance(cookie);
            appResponseCall.enqueue(new Callback<Integer>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {

                    eventResponse = response.body();
                    System.out.println(eventResponse);
                    balance.setText(eventResponse.toString());


                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    String message = t.getLocalizedMessage();
                    Toast.makeText(BalancePage.this, message, Toast.LENGTH_LONG).show();
                }
            });

        }}

