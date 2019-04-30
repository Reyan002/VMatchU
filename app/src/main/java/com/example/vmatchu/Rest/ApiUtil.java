package com.example.vmatchu.Rest;

public class ApiUtil {
    private ApiUtil() {}

    public static final String BASE_URL = "http://192.168.10.5:8080/apis/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
