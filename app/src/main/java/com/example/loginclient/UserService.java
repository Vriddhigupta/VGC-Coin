package com.example.loginclient;
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

}
