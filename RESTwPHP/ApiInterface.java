package com.imapro.phpmydata;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("index.php")
    Call<Data> saveData(@Field("title") String title,
                        @Field("device") String content);
}
