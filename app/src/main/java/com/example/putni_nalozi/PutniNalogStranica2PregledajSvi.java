package com.example.putni_nalozi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.putni_nalozi.models.PutniNalog;
import com.example.putni_nalozi.models.User;

public class PutniNalogStranica2PregledajSvi extends AppCompatActivity {

    private Button exit;
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
        setContentView(R.layout.activity_putni_nalog_stranica2_pregledaj_svi);

        exit = findViewById(R.id.exit);
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
        if (trenutniUser==null){
            sluzbenoOdputuje.setText(String.format("%s %s",putniNalog.getUser().getIme(),putniNalog.getUser().getPrezime()));
            zvanje.setText(putniNalog.getUser().getZvanje().trim());
            radnoMjesto.setText(putniNalog.getUser().getRadnoMjesto().trim());
        }
        else {
            sluzbenoOdputuje.setText(String.format("%s %s",trenutniUser.getIme(),trenutniUser.getPrezime()));
            zvanje.setText(trenutniUser.getZvanje().trim());
            radnoMjesto.setText(trenutniUser.getRadnoMjesto().trim());
        }
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
        vozilo.setText(putniNalog.getAuto());
        pocetnoStanjeBrojila.setText(putniNalog.getPocetnoStanjeBrojila());
        zavrsnoStanjeBrojila.setText(putniNalog.getZavrsnoStanjeBrojila());


        exit.setOnClickListener(new View.OnClickListener() {
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
        PutniNalogStranica1PregledajSvi.instance.finish();
        finish();
    }
}