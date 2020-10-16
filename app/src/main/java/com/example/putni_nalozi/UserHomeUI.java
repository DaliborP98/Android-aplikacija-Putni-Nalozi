package com.example.putni_nalozi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.putni_nalozi.models.User;

public class UserHomeUI extends AppCompatActivity {


   private CardView novi_nalog;
   private CardView pohranjeni_putni_nalogi;
   private CardView odjava;
   private TextView pozdrav;
   private CardView mojiPutniNalozi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_ui);

        novi_nalog= findViewById(R.id.novi_putni_nalog);
        mojiPutniNalozi =findViewById(R.id.moji_putni_nalozi);
        odjava= findViewById(R.id.odjava);
        pozdrav=findViewById(R.id.pozdravUser);


        User trenutniUser = (User) getIntent().getSerializableExtra("trenutniUser");

        assert trenutniUser != null;
        pozdrav.setText(String.format("Pozdrav, %s %s!", trenutniUser.getIme(), trenutniUser.getPrezime()));

        novi_nalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserHomeUI.this, PutniNalogUpisPodatakaActivity.class);
                intent.putExtra("trenutniUser",trenutniUser);
                startActivity(intent);
            }
        });

        mojiPutniNalozi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHomeUI.this, MojiPutniNalozi.class);
                intent.putExtra("trenutniUser",trenutniUser);
                startActivity(intent);
            }
        });

        odjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserHomeUI.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {}
}
