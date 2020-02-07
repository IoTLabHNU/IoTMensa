package com.imapro.restclient;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //MainActivity elements
    private EditText editTextTitle;
    private EditText editTextContent;

    private static final String HTTP_URL = "https://yourtargetdomain.com/";

    //Object from the interface
    private RestApi restApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextContent = findViewById(R.id.edit_text_content);

        //Creates an instance from the Retrofit-class
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HTTP_URL)
                .addConverterFactory(GsonConverterFactory.create()) //use json to pass the response
                .build();

        //Activates the REST-method
        restApi = retrofit.create(RestApi.class);
    }

    //Handles button-click
    public void postMsg(View v) {
        //Inserts data input into an new object
        Message message = new Message(editTextTitle.getText().toString(),
                editTextContent.getText().toString());

        //Executes the POST-method
        Call<Message> call = restApi.postMessage(message);

        //Handles the response from the web server
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                //Saves the response body
                Message messageResponse = response.body();

                String res = "";
                res += "Title: " + messageResponse.getTitle() + "\n";
                res += "Content: " + messageResponse.getContent();

                Toast.makeText(MainActivity.this, "New Message posted: " + res, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
