package com.example.putni_nalozi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.putni_nalozi.models.PutniNalog;
import com.example.putni_nalozi.models.PutniNaloziAPI;

import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PogledajSvePutneNaloge extends AppCompatActivity {

    private List<PutniNalog> sviPutniNaloziList;
    private RecyclerView recyclerView;
    private AdapterSviPutniNalozi adapterSviPutniNalozi;
    private RecyclerView.LayoutManager layoutManager;
    private TextView upozorenje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pogledaj_sve_putne_naloge);
        recyclerView = findViewById(R.id.recycleView);
        upozorenje=findViewById(R.id.upozorenje);
        sviPutniNalozi();
    }

    private void sviPutniNalozi(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();



        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(LoginActivity.SERVER)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.client(okHttpClient).build();

        PutniNaloziAPI putniNaloziAPI = retrofit.create(PutniNaloziAPI.class);

        Call<List<PutniNalog>> sviPutniNalozi = putniNaloziAPI.getPutneNaloge();

        sviPutniNalozi.enqueue(new Callback<List<PutniNalog>>() {
            @Override
            public void onResponse(Call<List<PutniNalog>> call, Response<List<PutniNalog>> response) {

                if (!response.isSuccessful()){
                    Toast.makeText(PogledajSvePutneNaloge.this, "Greška! Ne možemo učitati Putne naloge iz baze podataka\nPokušajte ponovo kasnije...", Toast.LENGTH_LONG).show();
                }
                else {

                    sviPutniNaloziList = response.body();

                    recyclerView.setHasFixedSize(true);
                    adapterSviPutniNalozi = new AdapterSviPutniNalozi(sviPutniNaloziList);
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapterSviPutniNalozi);

                    if (Objects.requireNonNull(recyclerView.getAdapter()).getItemCount()==0){

                        upozorenje.setText("U bazi podataka ne postoje putni nalozi.");
                    }

                    adapterSviPutniNalozi.setOnClickLisener(new AdapterSviPutniNalozi.OnItemClickLisener() {
                        @Override
                        public void onItemClick(int position) {
                            PutniNalog putniNalog = sviPutniNaloziList.get(position);
                            Intent intent = new Intent(PogledajSvePutneNaloge.this, PutniNaloziDetalji.class);
                            intent.putExtra("trenutniPutniNalog", putniNalog);
                            startActivity(intent);
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<List<PutniNalog>> call, Throwable t) {
                Toast.makeText(PogledajSvePutneNaloge.this, "Greška u komunikaciji sa serverom!\nPokušajte ponovo kasnije...", Toast.LENGTH_LONG).show();
            }
        });
    }
}