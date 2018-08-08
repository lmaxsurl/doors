package logunov.maxim.data.entity;

import com.google.gson.annotations.SerializedName;

public class HttpError extends Exception {

    public HttpError(String message, int code){
        this.message = message;
        this.code = code;
    }

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
