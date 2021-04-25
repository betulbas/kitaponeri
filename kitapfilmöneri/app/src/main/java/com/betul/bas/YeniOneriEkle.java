package com.betul.bas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class YeniOneriEkle extends AppCompatActivity {


    ImageView turu2;
    Spinner turu;
    EditText isim,yayinYili,sayfaSayisi,imdbPuani,not;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oneri_ekle);
        turu = findViewById(R.id.turu);
        isim = findViewById(R.id.ismi);
        yayinYili = findViewById(R.id.yil);
        sayfaSayisi = findViewById(R.id.sayfa);
        imdbPuani = findViewById(R.id.imdb);
        not = findViewById(R.id.notlar);
        ArrayList<String> liste = new ArrayList<>();
        liste.add("Film");
        liste.add("Kitap");
        liste.add("Müzik");
        liste.add("Oyun");
        final ArrayAdapter adapter = new ArrayAdapter(YeniOneriEkle.this,R.layout.spinner,liste);
        turu.setAdapter(adapter);
        turu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setDropDownViewResource(R.layout.spinner2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button ekle = findViewById(R.id.ekle);
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isim.getText().length() == 0){
                    Toast.makeText(YeniOneriEkle.this, "İsim giriniz.", Toast.LENGTH_SHORT).show();
                }else{
                    Database database = new Database(YeniOneriEkle.this);
                    database.oneriAdd(turu.getSelectedItem().toString(),isim.getText().toString(),
                            yayinYili.getText().toString(),sayfaSayisi.getText().toString(),imdbPuani.getText().toString(),
                            not.getText().toString());
                    startActivity(new Intent(YeniOneriEkle.this, MainActivity.class));
                    finish();
                }
            }
        });
        turu2 = findViewById(R.id.turu2);
        turu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turu.performClick();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(YeniOneriEkle.this, MainActivity.class));
        finish();
    }
}
