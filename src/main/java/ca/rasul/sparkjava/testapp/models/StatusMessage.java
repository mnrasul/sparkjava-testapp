package ca.rasul.sparkjava.testapp.models;

import java.text.MessageFormat;

import com.google.gson.Gson;

/**
 * Created by nrasul on 10/13/14.
 */
public class StatusMessage {
    private final int status;
    private final String message;

    public StatusMessage(int status, String message, String ... args) {
        this.status = status;
        this.message = MessageFormat.format(ResourceBundleHelper.getMessage(message), args);
    }

    public StatusMessage(Exception e) {
        this.status = 500;
        this.message = e.getMessage();
    }

    public String getMessage() {
        return this.message;
    }

    public String json(){
        return new Gson().toJson(this);
    }
}