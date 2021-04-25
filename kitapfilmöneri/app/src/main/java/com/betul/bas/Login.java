package com.betul.bas;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Login extends AppCompatActivity {

    Button giris;
    EditText eMail,sifre;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        eMail = findViewById(R.id.email);
        sifre = findViewById(R.id.sifre);
        giris = findViewById(R.id.login);
        register = findViewById(R.id.signUp);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
        if (pref.getBoolean("session",false)){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        giris.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitPrefEdits")
            @Override
            public void onClick(View view) {
                if (eMail.getText().toString().isEmpty()){
                    eMail.setError("Email giriniz.");
                }else if (sifre.getText().toString().isEmpty()){
                    sifre.setError("Şifrenizi giriniz.");
                }else {
                    Database database = new Database(Login.this);
                    Cursor cursor = database.query("SELECT * FROM UyelerTablosu where EPosta='"+eMail.getText().toString()+"'" +
                            " and Sifre='"+sifre.getText().toString()+"'");
                    while (cursor.moveToNext()) {
                        int kid = cursor.getInt(0);
                        String isim = cursor.getString(1);
                        String eMail = cursor.getString(2);
                        String telefon = cursor.getString(3);
                        String sifre = cursor.getString(4);
                        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
                        editor = pref.edit();
                        editor.putInt("kid",kid);
                        editor.putString("isim",isim);
                        editor.putString("email",eMail);
                        editor.putString("isim",telefon);
                        editor.putString("isim",sifre);
                        editor.putBoolean("session",true);
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}
