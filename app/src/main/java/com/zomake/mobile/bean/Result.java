package com.zomake.mobile.bean;

/**
 * Created by wojiushiwn on 2017/3/29.
 * desc:
 */

public class Result {

    private boolean result;
    private String errorMessage;
    private String error;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
