package com.example.putni_nalozi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.putni_nalozi.models.PutniNaloziAPI;
import com.example.putni_nalozi.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistracijaActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN
            = Pattern.compile("^" +
            "(?=.*[0-9])" +         //bar 1 broj
            "(?=.*[a-zA-Z])" +      //velika-mala slova
            ".{6,20}" +               //duzina 6-20
            "$");


    EditText ime;
    EditText prezime;
    EditText username;
    EditText password;
    EditText password_repeat;
    Button registrija_se;
    TextView prijavi_se;
    EditText zvanje;
    EditText radno_mjesto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);

        ime = (EditText) findViewById(R.id.ime);
        prezime = (EditText) findViewById(R.id.prezime);
        username = (EditText) findViewById(R.id.Username);
        password = (EditText) findViewById(R.id.edittext_password);
        password_repeat = (EditText) findViewById(R.id.edittext_password_repeat);
        registrija_se = (Button) findViewById(R.id.btn_registriraj_se);
        prijavi_se = (TextView) findViewById(R.id.textview_prijavi_se);
        zvanje = (EditText) findViewById(R.id.Zvanje);
        radno_mjesto = (EditText) findViewById(R.id.radnoMjesto);
        findViewById(R.id.loading).setVisibility(View.INVISIBLE);

        prijavi_se.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(RegistracijaActivity.this, LoginActivity.class);
                startActivity(login);

            }
        });

        registrija_se.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ValjanoIme() | !ValjanoPrezime() | !ValjaniEmail() | !ValjaniPassword() | !ValjanoRadnoMjesto() | !ValjanoZvanje()) {

                } else {

                    User user = null;
                    try {
                        user = new User(ime.getText().toString().trim(), prezime.getText().toString().trim(),
                                radno_mjesto.getText().toString().trim(), zvanje.getText().toString().trim(),
                                username.getText().toString().trim().toLowerCase(), LoginActivity.enkripcija(password.getText().toString()), "USER");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    RegistracijaUsera(user);
                }
            }
        });
    }



    private void RegistracijaUsera(User user) {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(LoginActivity.SERVER)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();


        PutniNaloziAPI putniNaloziAPI = retrofit.create(PutniNaloziAPI.class);

        Call<Void> addUser = putniNaloziAPI.add(user);

        addUser.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    findViewById(R.id.loading).setVisibility(View.VISIBLE);
                    Toast.makeText(RegistracijaActivity.this, "Korisnik sa unešenom e-mail adresom već postoji.", Toast.LENGTH_LONG).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            findViewById(R.id.loading).setVisibility(View.INVISIBLE);
                        }
                    }, 500);

                } else {
                    findViewById(R.id.loading).setVisibility(View.VISIBLE);
                    Toast.makeText(RegistracijaActivity.this, "Uspješno ste se registrirali. Preusmjerit čemo vas na Prijavu!", Toast.LENGTH_LONG).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            Intent intent = new Intent(RegistracijaActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }, 1500);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                findViewById(R.id.loading).setVisibility(View.VISIBLE);
                Toast.makeText(RegistracijaActivity.this, "Greška u komunikaciji sa serverom. Pokušajte ponovo kasnije...", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        findViewById(R.id.loading).setVisibility(View.INVISIBLE);
                    }
                }, 500);


            }
        });


    }


    private boolean ValjanoIme() {

        String inputIme = ime.getText().toString().trim();
        if (inputIme.isEmpty()) {
            ime.setError("Ime ne može biti prazno");
            return false;
        } else if (inputIme.length() < 3) {
            ime.setError("Ime ne može biti manje od 3 slova");
            return false;

        } else {
            //    ime.setText(null);
            return true;
        }
    }

    private boolean ValjanoPrezime() {

        String inputPrezime = prezime.getText().toString().trim();
        if (inputPrezime.isEmpty()) {
            prezime.setError("Prezime ne može biti prazno");
            return false;
        } else if (inputPrezime.length() < 3) {
            prezime.setError("Prezime ne može biti manje od 3 slova");
            return false;

        } else {
            //   prezime.setText(null);
            return true;
        }
    }

    private boolean ValjaniEmail() {

        String inputEmail = username.getText().toString().trim();
        if (inputEmail.isEmpty()) {
            username.setError("E-mail ne može biti prazno");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()) {
            username.setError("Format E-mail adrese nije valjan");
            return false;

        } else {
            //username.setText(null);
            //     username.setError(null);
            return true;
        }
    }


    private boolean ValjaniPassword() {


        String inputPw = password.getText().toString();
        String inputPonovljeniPW = password_repeat.getText().toString();
        if (inputPw.isEmpty()) {
            password.setError("Lozinka ne može biti prazno");
            password_repeat.setError("Lozinka ne može biti prazno");
            return false;
        } else if (inputPw.length() < 6) {
            password.setError("Lozinka je pre kratka");
            return false;
        } else if (inputPw.length() > 20) {
            password.setError("Lozinka je pre duga");
            return false;

        } else if (!PASSWORD_PATTERN.matcher(inputPw).matches()) {
            password.setError("Lozinka ne sadrži broj");
            return false;

        } else if (!inputPw.equals(inputPonovljeniPW)) {
            password.setError("Lozinke se ne podudaraju");
            password_repeat.setError("Lozinke se ne podudaraju");
            return false;

        } else {
            //   password.setError(null);
            return true;
        }

    }

    private boolean ValjanoZvanje() {

        String provjera = zvanje.getText().toString().trim();
        if (provjera.isEmpty()) {
            zvanje.setError("Zvanje ne može biti prazno");
            return false;

        } else {
            return true;
        }


    }

    private boolean ValjanoRadnoMjesto() {

        String provjera = radno_mjesto.getText().toString().trim();
        if (provjera.isEmpty()) {
            radno_mjesto.setError("Radno mjesto ne može biti prazno");
            return false;

        } else {
            return true;
        }


    }

}
