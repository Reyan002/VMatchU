package com.example.vmatchu.Rest;

import com.example.vmatchu.Pojo.UserLogin;
import com.example.vmatchu.Pojo.UserSignup;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @POST("signup.php")
    @FormUrlEncoded
    Call<UserSignup> postSignUp(@Field("username") String username,
                                @Field("email") String email,
                                @Field("password") String password,
                                @Field("registerDate") String date);


    @Headers({
            "Content-Type:application/json"
    })
    @POST("login.php")
    @FormUrlEncoded
    Call<UserLogin> postLogin(@Field("username") String username,
                              @Field("password") String password);
}
