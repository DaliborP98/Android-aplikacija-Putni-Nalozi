package com.example.putni_nalozi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.putni_nalozi.models.PutniNalog;
import com.example.putni_nalozi.models.User;

public class PutniNalogStranica1Admin extends AppCompatActivity {

    public static PutniNalogStranica1Admin instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_putni_nalog_stranica1_admin);
        instance = this;

        Button next = findViewById(R.id.nexSide);
        Button exit = findViewById(R.id.exit);
        TextView nazivDrustva = findViewById(R.id.nazivDrustva);
        TextView var = findViewById(R.id.var);
        TextView dan = findViewById(R.id.dan);
        TextView odredujem = findViewById(R.id.odredujem);
        TextView zvanje = findViewById(R.id.zvanje);
        TextView radnoMjesto = findViewById(R.id.radnoMjesto);
        TextView otputujeDana = findViewById(R.id.otputujeDana);
        TextView mjesto = findViewById(R.id.mjesto);
        TextView zadaca = findViewById(R.id.zadaca);
        TextView trajanjePutovanja = findViewById(R.id.trajanjePutovanja);
        TextView putovanjeSlovima = findViewById(R.id.putovanjeSlovima);
        TextView troskoviPutovanja = findViewById(R.id.troskoviPutovanja);
        TextView predujam = findViewById(R.id.predujam);
        TextView auto = findViewById(R.id.vozilo);
        TextView brojPutnogNaloga = findViewById(R.id.brojPutnogNaloga);

        PutniNalog putniNalog = (PutniNalog) getIntent().getSerializableExtra("pregledPutnogNaloga");
        String imeIprezime;

        assert putniNalog != null;

        imeIprezime = putniNalog.getIme() + " " + putniNalog.getPrezime();
        zvanje.setText(putniNalog.getZvanje());
        radnoMjesto.setText(putniNalog.getRadnoMjesto());

        nazivDrustva.setText(putniNalog.getNazivDrustva());
        var.setText(putniNalog.getMjestoFirme());
        dan.setText(putniNalog.getDana());
        auto.setText(putniNalog.getAuto());
        odredujem.setText(imeIprezime);
        otputujeDana.setText(putniNalog.getSluzbenoOtputuje());
        mjesto.setText(putniNalog.getMjestoPutovanja());
        zadaca.setText(putniNalog.getZadaca());
        if (putniNalog.getTrajanjePutovanja() == -1) {
            trajanjePutovanja.setText("");
        } else {
            trajanjePutovanja.setText(String.valueOf(putniNalog.getTrajanjePutovanja()));
        }
        putovanjeSlovima.setText(putniNalog.getTrajanjePutovanjaSlovima());
        troskoviPutovanja.setText(putniNalog.getTroskoviPutovanjaTerete());
        predujam.setText(putniNalog.getIsplataPredujma());
        if (putniNalog.getAuto().equals("AUTO")) {
            auto.setText("");
        }
        if (putniNalog.getBroj() == -1) {
            brojPutnogNaloga.setText("");
        } else {
            brojPutnogNaloga.setText(String.valueOf(putniNalog.getBroj()));
        }


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PutniNalogStranica1Admin.this, PutniNalogStranica2Admin.class);
                intent.putExtra("pregledPutnogNaloga", putniNalog);

                startActivity(intent);

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}