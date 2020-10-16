package com.example.putni_nalozi;

import androidx.annotation.Nullable;
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

public class PutniNalogStranica1Activity extends AppCompatActivity {

    public static PutniNalogStranica1Activity instance;
    private boolean pritisnuti_back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_putni_nalog_stranica1);
        instance=this;

        Button next = findViewById(R.id.nexSide);
        Button izmjeni = findViewById(R.id.izmjeni);
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



        PutniNalog putniNalog= (PutniNalog) getIntent().getSerializableExtra("pregledPutnogNaloga");
        User trenutniUser= (User) getIntent().getSerializableExtra("trenutniUser");

        assert putniNalog != null;
        assert trenutniUser != null;
        String imeIprezime=trenutniUser.getIme()+" "+trenutniUser.getPrezime();
        nazivDrustva.setText(putniNalog.getNazivDrustva());
        var.setText(putniNalog.getMjestoFirme());
        dan.setText(putniNalog.getDana());
        odredujem.setText(imeIprezime);
        zvanje.setText(trenutniUser.getZvanje());
        radnoMjesto.setText(trenutniUser.getRadnoMjesto());
        otputujeDana.setText(putniNalog.getSluzbenoOtputuje());
        mjesto.setText(putniNalog.getMjestoPutovanja());
        zadaca.setText(putniNalog.getZadaca());
        if (putniNalog.getTrajanjePutovanja()==-1){
            trajanjePutovanja.setText("");
        }
        else {
            trajanjePutovanja.setText(String.valueOf(putniNalog.getTrajanjePutovanja()));
        }
        putovanjeSlovima.setText(putniNalog.getTrajanjePutovanjaSlovima());
        troskoviPutovanja.setText(putniNalog.getTroskoviPutovanjaTerete());
        predujam.setText(putniNalog.getIsplataPredujma());
        if (putniNalog.getAuto().equals("AUTO")){
            auto.setText("");
        }


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(PutniNalogStranica1Activity.this,PutniNalogStranica2Activity.class);
                intent.putExtra("pregledPutnogNaloga",putniNalog);
                intent.putExtra("trenutniUser",trenutniUser);

                startActivity(intent);

            }
        });

        izmjeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
            }
        });
    }

}
