package com.alex.pattern.data.models.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex
 */

public class ErrorResponse {

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
