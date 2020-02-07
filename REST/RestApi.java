package com.imapro.restclient;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RestApi {

    @Headers("apikey:yourdbkey")
    @POST("folder/targetfolder")
    Call<Message> postMessage(@Body Message message);

}
