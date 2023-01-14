package com.example.loginclient;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {
    @POST("/student/login")
    Call<NewResponse> loginuser(@Body UserRequest loginRequest);

    @POST("/student/signup")
    Call<UserResponse> registeruser(@Body RegisterRequest registerRequest);

    @POST("/student/changepassword")
    Call<UserResponse> changepassword(@Header("Cookie") String cookie, @Body ChangeRequest changeRequest);

    @GET("/student/logout")
    Call<UserResponse> user_logout(@Header("Cookie") String cookie);

    @GET("/student/getevents")
    Call<List<EventResponse>> event_details(@Header("Cookie") String cookie);

    @GET("/student/getapplications")
    Call<List<AppResponse>> app_details(@Header("Cookie") String cookie);

    @GET("/student/balance")
    Call<Integer> get_balance(@Header("Cookie") String cookie);

    @POST("/student/canteentransfer")
    Call<UserResponse> send_canteen(@Header("Cookie") String cookie, @Body BalanceResponse balanceResponse);

    @POST("/student/stationerytransfer")
    Call<UserResponse> send_xerox(@Header("Cookie") String cookie, @Body BalanceResponse balanceResponse);

    @POST("/student/setapplication")
    Call<UserResponse> send_app(@Header("Cookie") String cookie, @Body AppRequest appRequest);

}
