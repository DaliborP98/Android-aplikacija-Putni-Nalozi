package com.example.putni_nalozi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.putni_nalozi.models.PutniNalog;
import com.example.putni_nalozi.models.PutniNaloziAPI;
import com.example.putni_nalozi.models.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.xml.transform.Result;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

public class PregledPutnihNalogaDetalji extends AppCompatActivity implements DialogOdbaci.DialogLisener, DialogPotvrdi.DialogLisener {

    private boolean pritisnuti_back_btn;
    private Button izadi;
    private Button pregledaj;
    private Button posalji;
    private Button odbaci;
    private EditText dan;
    private EditText imePrezime;
    private EditText Nalogbroj;
    private EditText zvanje;
    private EditText radnoMjesto;
    private TextView mjesto;
    private TextView opis;
    private TextView putovanje;
    private TextView putovanjeIznos;
    private TextView troskoviPutovanja;
    private TextView predujam;
    private TextView nazivDrustva;
    private EditText mjestoTvrtke;
    private TextView sluzbenoOdputuje;
    private TextView odredujemPONOVI;
    private TextView zvanjePONOVI;
    private TextView na_radnom_mjestuPONOVI;
    private TextView datum;
    private TextView datum2;
    private EditText pocetnoStanjeBrojila;
    private EditText zavrsnoStanjeBrojila;
    private EditText vozilo;
    private EditText razlika;
    private TextView datumPONOVI;
    private TextView datum2PONOVI;
    private EditText brojSati;
    private EditText brojdnevnica;
    private EditText brojpoludnevnica;
    private EditText ukupnoNovca;
    private Button datumButton;
    private Button datum2Button;
    private Button sluzbenoOdputujeButton;
    private PutniNalog trenutniPutniNalog;
    private PutniNalog putniNalog;
    private int redniBrojNaloga = 0;

    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    Retrofit.Builder builder = new Retrofit.Builder().baseUrl(LoginActivity.SERVER)
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.client(okHttpClient).build();
    PutniNaloziAPI putniNaloziAPI = retrofit.create(PutniNaloziAPI.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregled_putnih_naloga_detalji);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        izadi = findViewById(R.id.izađi);
        pregledaj = findViewById(R.id.pregledaj);
        posalji = findViewById(R.id.posalji);
        dan = findViewById(R.id.dan);
        imePrezime = findViewById(R.id.ime_i_prezime);
        zvanje = findViewById(R.id.zvanje);
        radnoMjesto = findViewById(R.id.na_radnom_mjestu);
        mjesto = findViewById(R.id.mjesto);
        opis = findViewById(R.id.opis);
        putovanje = findViewById(R.id.putovanje);
        putovanjeIznos = findViewById(R.id.putovanjeIznos);
        troskoviPutovanja = findViewById(R.id.troskoviPutovanja);
        predujam = findViewById(R.id.predujam);
        nazivDrustva = findViewById(R.id.nazivDrustva);
        mjestoTvrtke = findViewById(R.id.var);
        mjestoTvrtke.addTextChangedListener(textWatcher);
        sluzbenoOdputuje = findViewById(R.id.sluzbenoOdputuje);
        odredujemPONOVI = findViewById(R.id.odredujemPONOVI);
        zvanjePONOVI = findViewById(R.id.zvanjePONOVI);
        na_radnom_mjestuPONOVI = findViewById(R.id.na_radnom_mjestuPONOVI);
        datum = findViewById(R.id.datum);
        datum2 = findViewById(R.id.datum2);
        pocetnoStanjeBrojila = findViewById(R.id.pocetnoStanjeBrojila);
        zavrsnoStanjeBrojila = findViewById(R.id.zavrsnoStanjeBrojila);
        vozilo = findViewById(R.id.vozilo);
        Nalogbroj = findViewById(R.id.broj);
        razlika = findViewById(R.id.razlika);
        datumPONOVI = findViewById(R.id.datumPONOVI);
        datum2PONOVI = findViewById(R.id.datum2PONOVI);
        brojSati = findViewById(R.id.brojSati);
        brojdnevnica = findViewById(R.id.brojdnevnica);
        brojpoludnevnica = findViewById(R.id.brojpoludnevnica);
        ukupnoNovca = findViewById(R.id.ukupnoNovca);
        datumButton = findViewById(R.id.datumButton);
        datum2Button = findViewById(R.id.datum2Button);
        sluzbenoOdputujeButton = findViewById(R.id.sluzbenoOdputujeButton);
        findViewById(R.id.loading).setVisibility(View.INVISIBLE);
        dan.addTextChangedListener(textWatcher);
        odbaci = findViewById(R.id.odbaci);


        sluzbenoOdputujeButton.addTextChangedListener(textWatcher);


        trenutniPutniNalog = (PutniNalog) getIntent().getSerializableExtra("trenutniPutniNalog");
        assert trenutniPutniNalog != null;


        datum.setText(trenutniPutniNalog.getOdDatum());
        datum2.setText(trenutniPutniNalog.getDoDatum());
        datumPONOVI.setText(trenutniPutniNalog.getOdDatum());
        datum2PONOVI.setText(trenutniPutniNalog.getDoDatum());
        pocetnoStanjeBrojila.setText(String.valueOf(trenutniPutniNalog.getPocetnoStanjeBrojila()));
        zavrsnoStanjeBrojila.setText(String.valueOf(trenutniPutniNalog.getZavrsnoStanjeBrojila()));
        razlika.setText(String.valueOf(trenutniPutniNalog.getRazlikaBrojila()));
        brojSati.setText(String.valueOf(trenutniPutniNalog.getBrojSati()));
        brojdnevnica.setText(String.valueOf(trenutniPutniNalog.getBrojDnevnica()));
        brojpoludnevnica.setText(String.valueOf(trenutniPutniNalog.getBrojPoludnevnica()));
        ukupnoNovca.setText(trenutniPutniNalog.getUkupno());
        putovanje.setText(String.valueOf(trenutniPutniNalog.getTrajanjePutovanja()));
        putovanjeIznos.setText(trenutniPutniNalog.getTrajanjePutovanjaSlovima());
        if (trenutniPutniNalog.getAuto().equals("AUTO")) {
            vozilo.setText("");
        }
        troskoviPutovanja.setText(trenutniPutniNalog.getTroskoviPutovanjaTerete());
        predujam.setText(trenutniPutniNalog.getIsplataPredujma());
        opis.setText(trenutniPutniNalog.getZadaca());
        mjesto.setText(trenutniPutniNalog.getMjestoPutovanja());
        sluzbenoOdputuje.setText(trenutniPutniNalog.getSluzbenoOtputuje());
        mjestoTvrtke.setText(trenutniPutniNalog.getMjestoFirme());
        dan.setText(trenutniPutniNalog.getDana());
        imePrezime.setText(String.format("%s %s", trenutniPutniNalog.getUser().getIme(), trenutniPutniNalog.getUser().getPrezime()));
        imePrezime.addTextChangedListener(textWatcher);
        zvanje.setText(trenutniPutniNalog.getUser().getZvanje());
        zvanje.addTextChangedListener(textWatcher);
        radnoMjesto.setText(trenutniPutniNalog.getUser().getRadnoMjesto());
        radnoMjesto.addTextChangedListener(textWatcher);
        mjesto.addTextChangedListener(textWatcher);
        opis.addTextChangedListener(textWatcher);
        putovanje.addTextChangedListener(textWatcher);
        putovanjeIznos.addTextChangedListener(textWatcher);
        troskoviPutovanja.addTextChangedListener(textWatcher);
        predujam.addTextChangedListener(textWatcher);
        odredujemPONOVI.setText(String.format("%s %s", trenutniPutniNalog.getUser().getIme(), trenutniPutniNalog.getUser().getPrezime()));
        zvanjePONOVI.setText(trenutniPutniNalog.getUser().getZvanje());
        na_radnom_mjestuPONOVI.setText(trenutniPutniNalog.getUser().getRadnoMjesto());
        datum.addTextChangedListener(textWatcher);
        datum2.addTextChangedListener(textWatcher);
        datumPONOVI.addTextChangedListener(textWatcher);
        datum2PONOVI.addTextChangedListener(textWatcher);
        pocetnoStanjeBrojila.addTextChangedListener(textWatcher);
        zavrsnoStanjeBrojila.addTextChangedListener(textWatcher);
        vozilo.addTextChangedListener(textWatcher);
        brojSati.addTextChangedListener(textWatcher);
        ukupnoNovca.addTextChangedListener(textWatcher);
        razlika.addTextChangedListener(textWatcher);
        Nalogbroj.setText(String.valueOf(trenutniPutniNalog.getBroj()));
        Nalogbroj.addTextChangedListener(textWatcher);
        mjestoTvrtke.addTextChangedListener(textWatcher);
        brojdnevnica.addTextChangedListener(textWatcher);
        brojpoludnevnica.addTextChangedListener(textWatcher);

        izadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pregledaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (putovanje.getText().toString().isEmpty()) {
                    putovanje.setText("-1");
                }
                if (razlika.getText().toString().isEmpty()) {
                    razlika.setText("-1");
                }
                if (brojSati.getText().toString().isEmpty()) {
                    brojSati.setText("-1");

                }
                if (brojdnevnica.getText().toString().isEmpty()) {
                    brojdnevnica.setText("-1");
                }
                if (brojpoludnevnica.getText().toString().isEmpty()) {
                    brojpoludnevnica.setText("-1");
                }
                if (Nalogbroj.getText().toString().isEmpty()) {
                    Nalogbroj.setText("-1");
                }
                if (razlika.getText().toString().isEmpty()) {
                    razlika.setText("-1");
                }
                String imeIPrezime = imePrezime.getText().toString();
                String ime = "";
                String prezime = "";

                if (imeIPrezime.length() != 0) {
                    if (!imeIPrezime.contains(" ")) {
                        ime = imeIPrezime;
                    } else {

                        String[] odvoji = imeIPrezime.split(" ");
                        ime = odvoji[0];
                        prezime = odvoji[1];

                    }

                }

                PutniNalog nalog = new PutniNalog(nazivDrustva.getText().toString(), Integer.parseInt(Nalogbroj.getText().toString()), mjestoTvrtke.getText().toString(), dan.getText().toString(), ime, prezime,
                        zvanje.getText().toString(), radnoMjesto.getText().toString(), sluzbenoOdputuje.getText().toString(), mjesto.getText().toString(), opis.getText().toString(),
                        Integer.parseInt(putovanje.getText().toString()), putovanjeIznos.getText().toString(), vozilo.getText().toString(), troskoviPutovanja.getText().toString(), predujam.getText().toString(),
                        datum.getText().toString().trim(), datum2.getText().toString().trim(), pocetnoStanjeBrojila.getText().toString().trim(), zavrsnoStanjeBrojila.getText().toString().trim(),
                        Integer.parseInt(razlika.getText().toString().trim()), Double.parseDouble(brojSati.getText().toString().trim()), Integer.parseInt(brojdnevnica.getText().toString().trim()),
                        Integer.parseInt(brojpoludnevnica.getText().toString().trim()), ukupnoNovca.getText().toString().trim());

                if (razlika.getText().toString().equals("-1")) {
                    razlika.setText("");
                }
                if (Nalogbroj.getText().toString().equals("-1")) {
                    Nalogbroj.setText("");
                }
                if (putovanje.getText().toString().equals("-1")) {
                    putovanje.setText("");
                }
                if (razlika.getText().toString().equals("-1")) {
                    razlika.setText("");
                }
                if (brojSati.getText().toString().equals("-1")) {
                    brojSati.setText("");

                }
                if (brojdnevnica.getText().toString().equals("-1")) {
                    brojdnevnica.setText("");
                }
                if (brojpoludnevnica.getText().toString().equals("-1")) {
                    brojpoludnevnica.setText("");
                }

                Intent intent = new Intent(PregledPutnihNalogaDetalji.this, PutniNalogStranica1Admin.class);
                intent.putExtra("pregledPutnogNaloga", nalog);
                startActivity(intent);
            }
        });


        datumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datum.addTextChangedListener(textWatcher);
                prikaziKalendarOdlazak();

            }

        });


        datum2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datum2.addTextChangedListener(textWatcher);
                prikaziKalendarDolazak();
            }
        });

        sluzbenoOdputujeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sluzbenoOdputuje.addTextChangedListener(textWatcher);
                prikaziKalendar();
            }
        });

        posalji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPotvrdi dialog = new DialogPotvrdi();
                dialog.show(getSupportFragmentManager(), "Dialog Potvrdi");
            }
        });

        odbaci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogOdbaci dialogOdbaci = new DialogOdbaci();
                dialogOdbaci.show(getSupportFragmentManager(), "Dialog Odbaci");
            }
        });
    }

    @Override
    public void odbaciPutniNalog() {

        findViewById(R.id.loading).setVisibility(View.VISIBLE);

        Call<Void> izbrisiPutniNalog = putniNaloziAPI.izbrisiPutniNalog(trenutniPutniNalog.getId());

        izbrisiPutniNalog.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(PregledPutnihNalogaDetalji.this, "Greška! Putni nalog nije odbačen\nPokušajte ponovo...", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(PregledPutnihNalogaDetalji.this, "Putni nalog je uspješno odbačen!\nPreusmjerit ćemo vas...", Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            PregledPutnihNaloga.instance.finish();
                            finish();
                        }
                    }, 2500);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(PregledPutnihNalogaDetalji.this, "Greška u komunikaciji sa serverom!\nPokušajte ponovo kasnije...", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void potvrdiPutniNalog() {

        findViewById(R.id.loading).setVisibility(View.VISIBLE);

        String imeIPrezime = imePrezime.getText().toString();
        String ime = "";
        String prezime = "";

        if (imeIPrezime.length() != 0) {
            if (!imeIPrezime.contains(" ")) {
                ime = imeIPrezime;
            } else {

                String[] odvoji = imeIPrezime.split(" ");
                ime = odvoji[0];
                prezime = odvoji[1];

            }

        }

        putniNalog = new PutniNalog(trenutniPutniNalog.getId(), nazivDrustva.getText().toString(), Integer.parseInt(Nalogbroj.getText().toString()), mjestoTvrtke.getText().toString(), dan.getText().toString(), ime, prezime,
                zvanje.getText().toString(), radnoMjesto.getText().toString(), sluzbenoOdputuje.getText().toString(), mjesto.getText().toString().trim(), opis.getText().toString().trim(),
                Integer.parseInt(putovanje.getText().toString()), putovanjeIznos.getText().toString(), vozilo.getText().toString(), troskoviPutovanja.getText().toString(), predujam.getText().toString(),
                datum.getText().toString().trim(), datum2.getText().toString().trim(), pocetnoStanjeBrojila.getText().toString().trim(), zavrsnoStanjeBrojila.getText().toString().trim(),
                Integer.parseInt(razlika.getText().toString().trim()), Double.parseDouble(brojSati.getText().toString().trim()), Integer.parseInt(brojdnevnica.getText().toString().trim()),
                Integer.parseInt(brojpoludnevnica.getText().toString().trim()), ukupnoNovca.getText().toString().trim());

        Call<Void> updatePutniNalog = putniNaloziAPI.updatePutniNalog(trenutniPutniNalog.getUser().getID(), trenutniPutniNalog.getId(), putniNalog);

        updatePutniNalog.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(PregledPutnihNalogaDetalji.this, "Greška! Putni nalog nije potvrđen\nPokušajte ponovo...", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(PregledPutnihNalogaDetalji.this, "Putni nalog je uspješno potvrđen!\nPreusmjerit ćemo vas...", Toast.LENGTH_LONG).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            PregledPutnihNaloga.instance.finish();
                            finish();
                        }
                    }, 3000);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(PregledPutnihNalogaDetalji.this, "Greška u komunikaciji sa serverom!\nPokušajte ponovo kasnije...", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void prikaziKalendar() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

                sluzbenoOdputuje.setText(sdf.format(calendar.getTime()));

            }
        };

        new DatePickerDialog(PregledPutnihNalogaDetalji.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }


    private void prikaziKalendarOdlazak() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());

                        datum.setText(String.format("%s%s", sdf.format(calendar.getTime()), ":00"));
                    }
                };

                new TimePickerDialog(PregledPutnihNalogaDetalji.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        };

        new DatePickerDialog(PregledPutnihNalogaDetalji.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void prikaziKalendarDolazak() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());

                        datum2.setText(String.format("%s%s", sdf.format(calendar.getTime()), ":00"));
                    }
                };

                new TimePickerDialog(PregledPutnihNalogaDetalji.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        };

        new DatePickerDialog(PregledPutnihNalogaDetalji.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }


    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String sMjesto = mjesto.getText().toString().trim();
            String sOpis = opis.getText().toString().trim();
            String sPutovanje = putovanje.getText().toString().trim();
            String sPutovanjeIznos = putovanjeIznos.getText().toString().trim();
            String sTroskoviPutovanja = troskoviPutovanja.getText().toString().trim();
            String sPredujam = predujam.getText().toString().trim();
            String sDatum = datum.getText().toString().trim();
            String sDatum2 = datum.getText().toString().trim();
            String sPocetnoStanjeBrojila = pocetnoStanjeBrojila.getText().toString().trim();
            String sZavrsnoStanjeBrojila = zavrsnoStanjeBrojila.getText().toString().trim();
            boolean km = false;
            String povratakDatum = datum2.getText().toString().trim();
            boolean nijeGreska = false;
            String sSluzbenoOdputuje = sluzbenoOdputuje.getText().toString().trim();
            String Odredujem = imePrezime.getText().toString().trim();
            String sZvanje = zvanje.getText().toString().trim();
            String sRadnoMjesto = radnoMjesto.getText().toString().trim();
            String nalogBroj = Nalogbroj.getText().toString().trim();
            String mjTvrtke = mjestoTvrtke.getText().toString().trim();
            String sDan = dan.getText().toString().trim();
            String sRazlika = razlika.getText().toString().trim();
            String sBrojSati = brojSati.getText().toString().trim();
            String sBrojDnevnica = brojdnevnica.getText().toString().trim();
            String sBrojPoludnevnica = brojpoludnevnica.getText().toString().trim();
            String sUkupno = ukupnoNovca.getText().toString().trim();
            String sVozilo = vozilo.getText().toString().trim();


            odredujemPONOVI.setText(Odredujem);
            zvanjePONOVI.setText(sZvanje);
            na_radnom_mjestuPONOVI.setText(sRadnoMjesto);


            if (sPutovanje.equals("0")) {
                putovanje.setError("Ne može biti nula!");
            } else if (sPutovanje.startsWith("0")) {
                putovanje.setError("Broj dana ne može početi sa nulom!");
            }

            if (!sPocetnoStanjeBrojila.isEmpty() && !sZavrsnoStanjeBrojila.isEmpty() && sPocetnoStanjeBrojila.length() < 16 && sZavrsnoStanjeBrojila.length() < 16) {

                if (Long.parseLong(sPocetnoStanjeBrojila) < Long.parseLong(sZavrsnoStanjeBrojila)) {

                    km = true;
                    long razlikaKM = Long.parseLong(sZavrsnoStanjeBrojila) - Long.parseLong(sPocetnoStanjeBrojila);
                    razlika.removeTextChangedListener(this);
                    razlika.setText(String.valueOf(razlikaKM));

                } else if (Long.parseLong(sPocetnoStanjeBrojila) == Long.parseLong(sZavrsnoStanjeBrojila)) {

                    zavrsnoStanjeBrojila.setError("Broj početnog stanja i završnog stanja ne može biti jednak");
                    razlika.removeTextChangedListener(this);
                    razlika.setText("");

                } else {
                    zavrsnoStanjeBrojila.setError("Broj početnog stanja je veči od broja završnog stanja");
                    razlika.removeTextChangedListener(this);
                    razlika.setText("");
                }
                //      razlika.addTextChangedListener(this);

            }

            try {

                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
                Date d1 = sdf.parse(sDatum);
                Date d2 = sdf.parse(povratakDatum);
                assert d1 != null;
                assert d2 != null;
                long razlika = d2.getTime() - d1.getTime();

                if (razlika < 0) {
                    datum.setText("Pogrešan unos!");
                    datum2.setText("Pogrešan unos!");
                    datumPONOVI.setText("Pogrešan unos!");
                    datum2PONOVI.setText("Pogrešan unos!");
                    brojSati.setText("Greška!");
                    brojdnevnica.setText("Greška!");
                    brojpoludnevnica.setText("Greška!");
                    ukupnoNovca.setText("Greška!");
                } else {

                    datumPONOVI.removeTextChangedListener(this);
                    datum2PONOVI.removeTextChangedListener(this);
                    datumPONOVI.setText(datum.getText().toString().trim());
                    datum2PONOVI.setText(datum2.getText().toString().trim());
                    datumPONOVI.addTextChangedListener(this);
                    datum2PONOVI.addTextChangedListener(this);

                    nijeGreska = true;
                    double razlikaSati = TimeUnit.MILLISECONDS.toHours(razlika) % 24;
                    long razlikaDana = TimeUnit.MILLISECONDS.toDays(razlika) % 365;
                    double razlikaMinute = TimeUnit.MILLISECONDS.toMinutes(razlika) % 60;


                    razlikaSati += razlikaDana * 24;
                    razlikaSati += (razlikaMinute / 60);


                    String stringRazlikaSati = String.valueOf(Math.round(razlikaSati * 100.0) / 100.0);

                    long dnevnice = 0;
                    long poludnevnice = 0;
                    boolean zastava = false;

                    while (razlikaSati > 7.99) {


                        if (razlikaSati <= 12) {
                            poludnevnice++;
                            razlikaSati -= 12;
                        } else if (razlikaSati > 12 && razlikaSati < 24 && !zastava) {
                            dnevnice++;
                            razlikaSati -= 24;

                        } else if (razlikaSati > 24) {
                            dnevnice++;
                            razlikaSati -= 24;
                            zastava = true;
                        } else {
                            razlikaSati -= 12;
                            poludnevnice++;

                        }


                    }

                    brojdnevnica.removeTextChangedListener(this);
                    brojdnevnica.setText(String.valueOf(dnevnice));
                    brojpoludnevnica.removeTextChangedListener(this);
                    brojpoludnevnica.setText(String.valueOf(poludnevnice));


                    if (!sDatum.isEmpty() && !povratakDatum.isEmpty()) {
                        brojSati.removeTextChangedListener(this);
                        brojSati.setText(stringRazlikaSati);
                        //     brojSati.addTextChangedListener(this);
                    }

                    if ((razlikaMinute / 60) > 50) {
                        poludnevnice++;

                    }

                    long ukupno;
                    ukupno = (dnevnice * 170) + (poludnevnice * 85);

                    ukupnoNovca.removeTextChangedListener(this);
                    ukupnoNovca.setText(String.format("%s kn", ukupno));
                    //      ukupnoNovca.addTextChangedListener(this);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }


            posalji.setEnabled(!sMjesto.isEmpty() && !sOpis.isEmpty() && !sPutovanje.isEmpty() && !sPutovanje.equals("0") && !sPutovanje.startsWith("0") && !sPutovanjeIznos.isEmpty()
                    && !sTroskoviPutovanja.isEmpty() && !sPredujam.isEmpty() && !sDatum.isEmpty() && !sDatum2.isEmpty() && !sPocetnoStanjeBrojila.isEmpty() && !sZavrsnoStanjeBrojila.isEmpty()
                    && km && nijeGreska && !sSluzbenoOdputuje.isEmpty() && !Odredujem.isEmpty() && !sZvanje.isEmpty() && !sRadnoMjesto.isEmpty() && !nalogBroj.isEmpty() && !mjTvrtke.isEmpty()
                    && !sDan.isEmpty() && !razlika.getText().toString().isEmpty() && !sBrojSati.isEmpty() && !sBrojPoludnevnica.isEmpty() && !sBrojDnevnica.isEmpty() && !sUkupno.isEmpty() && !sVozilo.isEmpty()
            );

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void onBackPressed() {

        if (pritisnuti_back_btn) {
            super.onBackPressed();
            return;
        }

        this.pritisnuti_back_btn = true;
        Toast.makeText(this, "Pritisni BACK button ponovo za izlazak", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                pritisnuti_back_btn = false;
            }
        }, 2000);
    }


}
