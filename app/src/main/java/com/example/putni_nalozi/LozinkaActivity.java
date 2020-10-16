package com.example.putni_nalozi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.putni_nalozi.models.PutniNaloziAPI;
import com.example.putni_nalozi.models.User;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LozinkaActivity extends AppCompatActivity {

    private EditText email;
    private Button restartaj_lozinku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lozinka);

        email= findViewById(R.id.upisana_email_adresa);
        restartaj_lozinku= findViewById(R.id.button_restartaj_lozinku);
        findViewById(R.id.loading).setVisibility(View.INVISIBLE);

        restartaj_lozinku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ValjaniEmail()){

                }
                else {
                    findViewById(R.id.loading).setVisibility(View.VISIBLE);
                   posaljiLozinku(email.getText().toString().trim());
                }
            }
        });
    }

    private boolean ValjaniEmail() {

        if (email.getText().toString().trim().isEmpty()) {
            email.setError("E-mail polje ne može biti prazno");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()) {
            email.setError("Format E-mail adrese nije valjan");
            return false;

        } else {
            return true;
        }
    }

    private void posaljiLozinku(String email) {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(LoginActivity.SERVER)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.client(okHttpClient).build();

        PutniNaloziAPI putniNaloziAPI = retrofit.create(PutniNaloziAPI.class);

        Call<Void> provjeriLozinku= putniNaloziAPI.getLozinka(email);

        provjeriLozinku.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (!response.isSuccessful()){

                    findViewById(R.id.loading).setVisibility(View.INVISIBLE);
                    Toast.makeText(LozinkaActivity.this,"E-mail adresa ne postoji!\nRegistrirajte se.",Toast.LENGTH_LONG).show();
                }
                else {
                    findViewById(R.id.loading).setVisibility(View.INVISIBLE);
                    Toast.makeText(LozinkaActivity.this,"Vaša lozinka je poslana.\nProvjerite e-mail poštu!",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                findViewById(R.id.loading).setVisibility(View.INVISIBLE);
                Toast.makeText(LozinkaActivity.this, "Greška u komunikaciji sa serverom. Pokušajte ponovo kasnije.", Toast.LENGTH_LONG).show();

            }
        });

    }
}
