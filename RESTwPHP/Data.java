package com.imapro.phpmydata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    //Variables need to match fields in SQL-table
    @Expose
    @SerializedName("id")
    private Integer id;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("created")
    private String date;
    @Expose
    @SerializedName("success")
    private Boolean success;
    @Expose
    @SerializedName("message")
    private String message;


    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDevice() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
