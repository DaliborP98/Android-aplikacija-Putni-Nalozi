package com.example.putni_nalozi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.putni_nalozi.models.PutniNalog;
import com.example.putni_nalozi.models.User;
import com.github.chrisbanes.photoview.PhotoView;

public class PutniNalogStranica2Activity extends AppCompatActivity {

    private Button izmjeni;
    private Button posalji;
    private Button prevSide;
    private TextView sluzbenoOdputuje;
    private TextView zvanje;
    private TextView radnoMjesto;
    private TextView datum;
    private TextView datum2;
    private TextView datumPONOVI;
    private TextView datum2PONOVI;
    private TextView brojSati;
    private TextView brojdnevnica;
    private TextView ukupnoNovca;
    private TextView vozilo;
    private TextView pocetnoStanjeBrojila;
    private TextView zavrsnoStanjeBrojila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_putni_nalog_stranica2);

        izmjeni = findViewById(R.id.izmjeni);
        posalji = findViewById(R.id.posalji);
        prevSide = findViewById(R.id.prevSide);
        sluzbenoOdputuje = findViewById(R.id.sluzbenoOdputuje);
        zvanje = findViewById(R.id.zvanje);
        radnoMjesto = findViewById(R.id.radnoMjesto);
        datum = findViewById(R.id.datum);
        datum2 = findViewById(R.id.datum2);
        datumPONOVI = findViewById(R.id.datumPONOVI);
        datum2PONOVI = findViewById(R.id.datum2PONOVI);
        brojSati = findViewById(R.id.brojSati);
        brojdnevnica = findViewById(R.id.brojdnevnica);
        ukupnoNovca = findViewById(R.id.ukupnoNovca);
        vozilo = findViewById(R.id.vozilo);
        pocetnoStanjeBrojila = findViewById(R.id.pocetnoStanjeBrojila);
        zavrsnoStanjeBrojila = findViewById(R.id.zavrsnoStanjeBrojila);


        PutniNalog putniNalog = (PutniNalog) getIntent().getSerializableExtra("pregledPutnogNaloga");
        User trenutniUser = (User) getIntent().getSerializableExtra("trenutniUser");

        assert putniNalog != null;
        assert trenutniUser != null;
        sluzbenoOdputuje.setText(String.format("%s %s", trenutniUser.getIme(), trenutniUser.getPrezime()));
        zvanje.setText(trenutniUser.getZvanje().trim());
        radnoMjesto.setText(trenutniUser.getRadnoMjesto().trim());
        datum.setText(putniNalog.getOdDatum());
        datum2.setText(putniNalog.getDoDatum());
        datumPONOVI.setText(putniNalog.getOdDatum());
        datum2PONOVI.setText(putniNalog.getDoDatum());
        if (putniNalog.getBrojSati() == -1) {
            brojSati.setText("");
        } else {

            brojSati.setText(String.valueOf(putniNalog.getBrojSati()));
        }
        if (putniNalog.getBrojDnevnica() == -1) {
            brojdnevnica.setText("");
        } else {
            brojdnevnica.setText(String.valueOf(putniNalog.getBrojDnevnica()));
        }
        ukupnoNovca.setText(putniNalog.getUkupno());
        if (putniNalog.getAuto().equals("AUTO")) {
            vozilo.setText("");
        } else {
            vozilo.setText(putniNalog.getAuto());
        }
        pocetnoStanjeBrojila.setText(putniNalog.getPocetnoStanjeBrojila());
        zavrsnoStanjeBrojila.setText(putniNalog.getZavrsnoStanjeBrojila());


        izmjeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Natrag2Activitya();


            }
        });

        prevSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    void Natrag2Activitya() {
        PutniNalogStranica1Activity.instance.finish();
        finish();
    }
}
