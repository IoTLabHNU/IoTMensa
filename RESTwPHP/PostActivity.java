package com.imapro.phpmydata;


import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;


public class PostActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextContent;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextContent = findViewById(R.id.edit_text_content);

    }
    //Builds new menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        return true;
    }
    //Handles data input
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:

                String title = editTextTitle.getText().toString().trim();
                String content = editTextContent.getText().toString().trim();

                if (title.isEmpty()) {
                    editTextTitle.setError("Please enter a title");

                } else if (content.isEmpty()) {
                    editTextContent.setError("Enter a valid content");
                } else {
                    saveData(title, content);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    //Transfers String attributes from text input
    public void saveData(final String title, final String content) {

        //Implements Singleton-object from Retrofit-Class
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Data> call = apiInterface.saveData(title, content);

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

            }

            //Returns to MainActivity after execution
            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                finish();

            }
        });


    }
}
