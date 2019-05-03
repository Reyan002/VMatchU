package com.example.vmatchu.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InsertPropertyResponse {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("error")
    @Expose
    private String error;

    public String getCode() {
        return code;
    }

    public String getError() {
        return error;
    }
}
