package com.example.putni_nalozi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.putni_nalozi.models.User;

public class Admin_homeUI extends AppCompatActivity {

    private CardView novi_nalog;
    private CardView pogledajSvePutneNaloge;
    private CardView mojiPutniNalozi;
    private CardView pregledPutnihNaloga;
    private CardView odjava;
    private TextView pozdrav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_ui);

        novi_nalog= findViewById(R.id.novi_putni_nalog);
        mojiPutniNalozi=(CardView) findViewById(R.id.moji_putni_nalozi);
        pogledajSvePutneNaloge= findViewById(R.id.pogledajSvePutneNaloge);
        pregledPutnihNaloga= findViewById(R.id.pregled_putnih_naloga);
        pozdrav = findViewById(R.id.pozdravUser);
        odjava= findViewById(R.id.odjava);

        User trenutniUser = (User) getIntent().getSerializableExtra("trenutniUser");

        assert trenutniUser != null;
        pozdrav.setText(String.format("Pozdrav, %s %s!", trenutniUser.getIme(), trenutniUser.getPrezime()));


        novi_nalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Admin_homeUI.this, PutniNalogUpisPodatakaAdmin.class);
                intent.putExtra("trenutniUser",trenutniUser);
                startActivity(intent);
            }
        });

        mojiPutniNalozi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_homeUI.this,MojiPutniNalozi.class);
                intent.putExtra("trenutniUser",trenutniUser);
                startActivity(intent);
            }
        });



        odjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Admin_homeUI.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        pogledajSvePutneNaloge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (Admin_homeUI.this,PogledajSvePutneNaloge.class);
                startActivity(intent);
            }
        });

        pregledPutnihNaloga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (Admin_homeUI.this,PregledPutnihNaloga.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed() { }
}
