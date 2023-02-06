package com.example.tryslyus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private ImageView photo;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        photo = (ImageView) findViewById(R.id.imagePet);
        text = (TextView) findViewById(R.id.PetName);

        mProgressBar.setVisibility(View.INVISIBLE);


        PetAPI petAPI = PetAPI.retrofit.create(PetAPI.class);
        final Call<Pet> call = petAPI.getData();


        call.enqueue(new Callback<Pet>() {
                         @Override
                         public void onResponse(Call<Pet> call, Response<Pet> response) {
                             if (response.isSuccessful()) {
                                 Pet pet = response.body();
                                 text.setText(pet.getName());

                                 Picasso.with(MainActivity.this).load("https://kingsize-cat.ru/images/offsprings/mafia/mafia-13.jpg").into(photo);

                                 mProgressBar.setVisibility(View.INVISIBLE);
                             } else {
                                 // Обрабатываем ошибку
                                 ResponseBody errorBody = response.errorBody();
                                 try {
                                     Toast.makeText(MainActivity.this, errorBody.string(),
                                             Toast.LENGTH_SHORT).show();
                                     mProgressBar.setVisibility(View.INVISIBLE);
                                 } catch (IOException e) {
                                     e.printStackTrace();
                                 }
                             }
                         }

                         @Override
                         public void onFailure(Call<Pet> call, Throwable throwable)  {
                             Toast.makeText(MainActivity.this, "Что-то пошло не так " + throwable.getMessage(),
                                     Toast.LENGTH_SHORT).show();
                             Log.d("ERRORS_GIT", ""+throwable.getMessage());
                             mProgressBar.setVisibility(View.INVISIBLE);
                         }
                     }
        );
    }
}