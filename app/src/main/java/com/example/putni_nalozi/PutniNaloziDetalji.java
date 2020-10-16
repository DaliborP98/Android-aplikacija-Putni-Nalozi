package com.example.putni_nalozi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.putni_nalozi.models.PutniNalog;
import com.example.putni_nalozi.models.User;

public class PutniNaloziDetalji extends AppCompatActivity {

    private Button izadi;
    private Button pregledaj;
    private TextView dan;
    private TextView imePrezime;
    private TextView zvanje;
    private TextView radnoMjesto;
    private TextView mjesto;
    private TextView opis;
    private TextView putovanje;
    private TextView putovanjeIznos;
    private TextView troskoviPutovanja;
    private TextView predujam;
    private TextView nazivDrustva;
    private TextView var;
    private TextView sluzbenoOdputuje;
    private TextView brojPutnogNaloga;
    private TextView odredujemPONOVI;
    private TextView zvanjePONOVI;
    private TextView na_radnom_mjestuPONOVI;
    private TextView datum;
    private TextView datum2;
    private TextView pocetnoStanjeBrojila;
    private TextView zavrsnoStanjeBrojila;
    private TextView razlika;
    private TextView datumPONOVI;
    private TextView datum2PONOVI;
    private TextView brojSati;
    private TextView brojdnevnica;
    private TextView brojpoludnevnica;
    private TextView ukupnoNovca;
    private TextView vozilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_putni_nalozi_detalji);

        izadi= findViewById(R.id.izađi);
        pregledaj= findViewById(R.id.pregledaj);
        dan= findViewById(R.id.dan);
        imePrezime=findViewById(R.id.ime_i_prezime);
        zvanje=findViewById(R.id.zvanje);
        radnoMjesto=findViewById(R.id.na_radnom_mjestu);
        mjesto=findViewById(R.id.mjesto);
        opis=findViewById(R.id.opis);
        putovanje=findViewById(R.id.putovanje);
        putovanjeIznos=findViewById(R.id.putovanjeIznos);
        troskoviPutovanja=findViewById(R.id.troskoviPutovanja);
        predujam=findViewById(R.id.predujam);
        nazivDrustva=findViewById(R.id.nazivDrustva);
        var=findViewById(R.id.var);
        sluzbenoOdputuje=findViewById(R.id.sluzbenoOdputuje);
        brojPutnogNaloga=findViewById(R.id.brojPutnogNaloga);
        odredujemPONOVI=findViewById(R.id.odredujemPONOVI);
        zvanjePONOVI=findViewById(R.id.zvanjePONOVI);
        na_radnom_mjestuPONOVI=findViewById(R.id.na_radnom_mjestuPONOVI);
        datum=findViewById(R.id.datum);
        datum2=findViewById(R.id.datum2);
        pocetnoStanjeBrojila=findViewById(R.id.pocetnoStanjeBrojila);
        zavrsnoStanjeBrojila=findViewById(R.id.zavrsnoStanjeBrojila);
        razlika=findViewById(R.id.razlika);
        datumPONOVI=findViewById(R.id.datumPONOVI);
        datum2PONOVI=findViewById(R.id.datum2PONOVI);
        brojSati=findViewById(R.id.brojSati);
        brojdnevnica=findViewById(R.id.brojdnevnica);
        brojpoludnevnica=findViewById(R.id.brojpoludnevnica);
        ukupnoNovca=findViewById(R.id.ukupnoNovca);
        vozilo=findViewById(R.id.vozilo);


        User trenutniUser= (User)getIntent().getSerializableExtra("trenutniUser");
        PutniNalog putniNalog= (PutniNalog)getIntent().getSerializableExtra("trenutniPutniNalog");

        assert putniNalog != null;


        dan.setText(putniNalog.getDana());
        if (trenutniUser==null ){
            imePrezime.setText(String.format("%s %s",putniNalog.getUser().getIme(),putniNalog.getUser().getPrezime()));
            zvanje.setText(putniNalog.getUser().getZvanje());
            radnoMjesto.setText(putniNalog.getUser().getRadnoMjesto());
            odredujemPONOVI.setText(String.format("%s %s",putniNalog.getUser().getIme(),putniNalog.getUser().getPrezime()));
            zvanjePONOVI.setText(putniNalog.getUser().getZvanje());
            na_radnom_mjestuPONOVI.setText(putniNalog.getUser().getRadnoMjesto());
        }
        else {
            imePrezime.setText(String.format("%s %s",trenutniUser.getIme(),trenutniUser.getPrezime()));
            zvanje.setText(trenutniUser.getZvanje());
            radnoMjesto.setText(trenutniUser.getRadnoMjesto());
            odredujemPONOVI.setText(String.format("%s %s",trenutniUser.getIme(),trenutniUser.getPrezime()));
            zvanjePONOVI.setText(trenutniUser.getZvanje());
            na_radnom_mjestuPONOVI.setText(trenutniUser.getRadnoMjesto());
        }

        sluzbenoOdputuje.setText(putniNalog.getSluzbenoOtputuje());
        mjesto.setText(putniNalog.getMjestoPutovanja());
        opis.setText(putniNalog.getZadaca());
        putovanjeIznos.setText(String.valueOf(putniNalog.getTrajanjePutovanja()));
        putovanje.setText(putniNalog.getTrajanjePutovanjaSlovima());
        troskoviPutovanja.setText(putniNalog.getTroskoviPutovanjaTerete());
        predujam.setText(putniNalog.getIsplataPredujma());
        datum.setText(putniNalog.getOdDatum());
        datumPONOVI.setText(putniNalog.getOdDatum());
        datum2.setText(putniNalog.getDoDatum());
        datum2PONOVI.setText(putniNalog.getDoDatum());
        pocetnoStanjeBrojila.setText(putniNalog.getPocetnoStanjeBrojila());
        zavrsnoStanjeBrojila.setText(putniNalog.getZavrsnoStanjeBrojila());
        razlika.setText(String.valueOf(putniNalog.getRazlikaBrojila()));
        brojSati.setText(String.valueOf(putniNalog.getBrojSati()));
        brojdnevnica.setText(String.valueOf(putniNalog.getBrojDnevnica()));
        brojpoludnevnica.setText(String.valueOf(putniNalog.getBrojPoludnevnica()));
        ukupnoNovca.setText(putniNalog.getUkupno());
        brojPutnogNaloga.setText(String.valueOf(putniNalog.getBroj()));
        vozilo.setText(putniNalog.getAuto());



        izadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pregledaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(PutniNaloziDetalji.this,PutniNalogStranica1PregledajSvi.class);
                intent.putExtra("pregledPutnogNaloga",putniNalog);
                intent.putExtra("trenutniUser",trenutniUser);
                startActivity(intent);
            }
        });


    }
}