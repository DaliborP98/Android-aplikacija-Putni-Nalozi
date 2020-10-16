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

public class PregledPutnihNaloga extends AppCompatActivity {

    protected static PregledPutnihNaloga instance;
    private List<PutniNalog> nedefiniraniPutniNaloziList;
    protected static RecyclerView recyclerView;
    private AdapterNedefinirano adapterNedefinirano;
    private RecyclerView.LayoutManager layoutManager;
    private TextView upozorenje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregled_putnih_naloga);
        instance=this;
        recyclerView = findViewById(R.id.recycleView1);
        upozorenje=findViewById(R.id.upozorenje);
        nedefiniraniPutniNalozi();
    }

    private void nedefiniraniPutniNalozi() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();



        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(LoginActivity.SERVER)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.client(okHttpClient).build();

        PutniNaloziAPI putniNaloziAPI = retrofit.create(PutniNaloziAPI.class);

        Call<List<PutniNalog>> nedefinirano=putniNaloziAPI.getNedefiniranePutneNaloge();

        nedefinirano.enqueue(new Callback<List<PutniNalog>>() {
            @Override
            public void onResponse(Call<List<PutniNalog>> call, Response<List<PutniNalog>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(PregledPutnihNaloga.this, "Greška! Ne možemo učitati Putne naloge iz baze podataka\nPokušajte ponovo kasnije...", Toast.LENGTH_LONG).show();
                }
                else {
                    nedefiniraniPutniNaloziList=response.body();

                    recyclerView.setHasFixedSize(true);
                    adapterNedefinirano = new AdapterNedefinirano(nedefiniraniPutniNaloziList);
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapterNedefinirano);

                    if (Objects.requireNonNull(recyclerView.getAdapter()).getItemCount()==0){

                        upozorenje.setText("Trenutno nema putnih naloga!");
                    }

                    adapterNedefinirano.setOnClickLisener(new AdapterNedefinirano.OnItemClickLisener() {
                        @Override
                        public void onItemClick(int position) {
                            PutniNalog putniNalog = nedefiniraniPutniNaloziList.get(position);
                            Intent intent = new Intent(PregledPutnihNaloga.this, PregledPutnihNalogaDetalji.class);
                            intent.putExtra("trenutniPutniNalog", putniNalog);
                            startActivity(intent);

                        }
                    });

                }

            }

            @Override
            public void onFailure(Call<List<PutniNalog>> call, Throwable t) {

                Toast.makeText(PregledPutnihNaloga.this, "Greška u komunikaciji sa serverom!\nPokušajte ponovo kasnije...", Toast.LENGTH_LONG).show();
            }
        });


    }

}