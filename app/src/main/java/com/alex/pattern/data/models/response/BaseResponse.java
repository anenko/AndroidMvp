package com.alex.pattern.data.models.response;

import com.google.gson.annotations.SerializedName;


/**
 * Created by Alex
 */

public class BaseResponse {

    @SerializedName("success")
    private boolean isSuccess;
    @SerializedName("error")
    private ErrorResponse error;

    public boolean isSuccess() {
        return isSuccess;
    }

    public ErrorResponse getError() {
        return error;
    }

}
