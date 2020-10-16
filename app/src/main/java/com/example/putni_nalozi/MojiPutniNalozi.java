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
import com.example.putni_nalozi.models.User;

import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MojiPutniNalozi extends AppCompatActivity {

    private List<PutniNalog> mojiPutniNaloziList;
    private RecyclerView recyclerView;
    private AdapterMojiPutniNalozi adapterMojiPutniNalozi;
    private RecyclerView.LayoutManager layoutManager;
    private User trenutniUser;
    private TextView upozorenje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moji_putni_nalozi);
        recyclerView = findViewById(R.id.recycleView);
        upozorenje=findViewById(R.id.upozorenje);

        trenutniUser = (User) getIntent().getSerializableExtra("trenutniUser");


        assert trenutniUser != null;
        mojiPutniNalozi(trenutniUser.getID());

    }

    private void mojiPutniNalozi(int id) {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(LoginActivity.SERVER)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.client(okHttpClient).build();

        PutniNaloziAPI putniNaloziAPI = retrofit.create(PutniNaloziAPI.class);

        Call<List<PutniNalog>> mojiPutniNalozi = putniNaloziAPI.getPutneNalogeByUser(id);

        mojiPutniNalozi.enqueue(new Callback<List<PutniNalog>>() {
            @Override
            public void onResponse(Call<List<PutniNalog>> call, Response<List<PutniNalog>> response) {

                if (!response.isSuccessful()) {

                    Toast.makeText(MojiPutniNalozi.this, "Greška! Ne možemo učitati vaše Putne naloge\nPokušajte ponovo kasnije...", Toast.LENGTH_LONG).show();
                } else {

                    mojiPutniNaloziList = response.body();

                    recyclerView.setHasFixedSize(true);
                    adapterMojiPutniNalozi = new AdapterMojiPutniNalozi(mojiPutniNaloziList);
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapterMojiPutniNalozi);

                    if (Objects.requireNonNull(recyclerView.getAdapter()).getItemCount()==0){

                        upozorenje.setText("Vaši putni nalozi su prazni.");
                    }


                    adapterMojiPutniNalozi.setOnClickLisener(new AdapterMojiPutniNalozi.OnItemClickLisener() {
                        @Override
                        public void onItemClick(int position) {
                            PutniNalog putniNalog = mojiPutniNaloziList.get(position);
                            Intent intent = new Intent(MojiPutniNalozi.this, PutniNaloziDetalji.class);
                            intent.putExtra("trenutniPutniNalog", putniNalog);
                            intent.putExtra("trenutniUser", trenutniUser);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<PutniNalog>> call, Throwable t) {

                Toast.makeText(MojiPutniNalozi.this, "Greška u komunikaciji sa serverom!\nPokušajte ponovo kasnije...", Toast.LENGTH_LONG).show();

            }
        });

    }

}