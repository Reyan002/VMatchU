package com.example.vmatchu.Rest;

import com.example.vmatchu.Pojo.InsertPropertyResponse;
import com.example.vmatchu.Pojo.PropertyType;
import com.example.vmatchu.Pojo.UserLogin;
import com.example.vmatchu.Pojo.UserSignup;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
    @GET("signup.php")
    Call<UserSignup> postSignUp(@Query("username") String username,
                                @Query("email") String email,
                                @Query("password") String password,
                                @Query("registerDate") String date);


    @GET("login.php")
    Call<UserLogin> postLogin(@Query("username") String username,
                              @Query("password") String password);

    @GET("getProperty.php")
    Call<PropertyType> getProperty();


    @GET("addSellProperty.php")
    Call<InsertPropertyResponse> postInsertSellProperty(@Query("propertyTitle") String propertyTitle,
                                                    @Query("userId") String userId, @Query("propertyType") String propertyType,
                                                    @Query("propertyTypeRentOrPurchase") String propertyTypeRentOrPurchase,
                                                    @Query("propertyCountry") String propertyCountry,
                                                    @Query("propertyCity") String propertyCity, @Query("propertyArea") String propertyArea,
                                                    @Query("propertySubArea") String propertySubArea,
                                                    @Query("propertySector") String propertySector,
                                                    @Query("propertyPrice") String propertyPrice,
//                                                    @Field("propertyMaxPrice") String propertyMaxPrice,
                                                    @Query("propertyDetailsSize") String propertyDetailsSize,
//                                                    @Field("propertyDetailsMaxSize") String propertyDetailsMaxSize,
                                                    @Query("propertyDetailsAreaType") String propertyDetailsAreaType,
                                                    @Query("propertyDetailsRooms") String propertyDetailsRooms,
                                                    @Query("propertyDetailsBedrooms") String propertyDetailsBedrooms,
                                                    @Query("propertyDetailsBathrooms") String propertyDetailsBathrooms,
                                                    @Query("propertyDetailsGarages") String propertyDetailsGarages,
                                                    @Query("propertyDescription") String propertyDescription,
                                                    @Query("propertyImage") String propertyImage,
                                                    @Query("propertyVideoUrl") String propertyVideoUrl,
                                                    @Query("propertyImage360Url") String propertyImage360Url);

    @POST("addPurchaseProperty.php")
    @FormUrlEncoded
    Call<InsertPropertyResponse> postInsertPurchaseProperty(@Field("propertyTitle") String propertyTitle,
                                                    @Field("userId") String userId, @Field("propertyType") String propertyType,
                                                    @Field("propertyTypeRentOrPurchase") String propertyTypeRentOrPurchase,
                                                    @Field("propertyCountry") String propertyCountry,
                                                    @Field("propertyCity") String propertyCity, @Field("propertyArea") String propertyArea,
                                                    @Field("propertySubArea") String propertySubArea,
                                                    @Field("propertySector") String propertySector,
                                                    @Field("propertyMinPrice") String propertyMinPrice,
                                                    @Field("propertyMaxPrice") String propertyMaxPrice,
                                                    @Field("propertyDetailsMinSize") String propertyDetailsMinSize,
                                                    @Field("propertyDetailsMaxSize") String propertyDetailsMaxSize,
                                                    @Field("propertyDetailsAreaType") String propertyDetailsAreaType,
                                                    @Field("propertyDetailsRooms") String propertyDetailsRooms,
                                                    @Field("propertyDetailsBedrooms") String propertyDetailsBedrooms,
                                                    @Field("propertyDetailsBathrooms") String propertyDetailsBathrooms,
                                                    @Field("propertyDetailsGarages") String propertyDetailsGarages,
                                                    @Field("propertyDescription") String propertyDescription,
                                                    @Field("propertyImage") String propertyImage,
                                                    @Field("propertyVideoUrl") String propertyVideoUrl,
                                                    @Field("propertyImage360Url") String propertyImage360Url);

}
