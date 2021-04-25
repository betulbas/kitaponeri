package com.betul.bas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Profil extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    EditText isim,email,telefon,sifre,sifretekrar;
    Button update,exit;
    int kid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        isim = findViewById(R.id.ad);
        email = findViewById(R.id.email);
        telefon = findViewById(R.id.tel);
        sifre = findViewById(R.id.sifre);
        sifretekrar = findViewById(R.id.sifretekrar);
        SharedPreferences pref = getSharedPreferences("pref",Context.MODE_PRIVATE);
        Database database = new Database(Profil.this);
        ArrayList<ProfilList> profilLists = database.profilList(pref.getInt("kid",0));
        kid = profilLists.get(0).getKid();
        isim.setText(profilLists.get(0).getIsimSoyisim());
        email.setText(profilLists.get(0).getePosta());
        telefon.setText(profilLists.get(0).getTelefon());
        sifre.setText(profilLists.get(0).getSifre());
        sifretekrar.setText(profilLists.get(0).getSifre());
        update = findViewById(R.id.update);
        exit = findViewById(R.id.exit);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isim.getText().toString().isEmpty())
                    isim.setError("");
                else if (email.getText().toString().isEmpty())
                    email.setError("");
                else if (telefon.getText().toString().isEmpty())
                    telefon.setError("");
                else if (sifre.getText().toString().isEmpty())
                    sifre.setError("");
                else if (sifretekrar.getText().toString().isEmpty())
                    sifretekrar.setError("");
                else if (!sifretekrar.getText().toString().equals(sifre.getText().toString())){
                    Toast.makeText(Profil.this, "Şifreleri aynı giriniz.", Toast.LENGTH_SHORT).show();
                }else{
                    Database database = new Database(Profil.this);
                    database.userUpdate(kid,isim.getText().toString(),email.getText().toString(),telefon.getText().toString(),sifre.getText().toString());
                    startActivity(getIntent());
                    finish();
                }
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("pref",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(Profil.this, Login.class));
                finish();
            }
        });
    }
}
