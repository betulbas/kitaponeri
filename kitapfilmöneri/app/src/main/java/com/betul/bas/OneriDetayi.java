package com.betul.bas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class OneriDetayi extends AppCompatActivity {
    EditText turu,isim,yayinYili,sayfaSayisi,imdbPuani,not;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oneri_detayi);

        turu = findViewById(R.id.turu);
        isim = findViewById(R.id.ismi);
        yayinYili = findViewById(R.id.yil);
        sayfaSayisi = findViewById(R.id.sayfa);
        imdbPuani = findViewById(R.id.imdb);
        not = findViewById(R.id.notlar);
        Bundle bundle = getIntent().getExtras();
        turu.setText(bundle.getString("turu"));
        isim.setText(bundle.getString("ismi"));
        yayinYili.setText(bundle.getString("yayinYili"));
        sayfaSayisi.setText(bundle.getString("sayfaSayisi"));
        imdbPuani.setText(bundle.getString("imdbPuani"));
        not.setText(bundle.getString("not"));
    }
}
