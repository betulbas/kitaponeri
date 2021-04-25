package com.betul.bas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText isim,email,telefon,sifre,sifretekrar;
    Button kayit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        isim = findViewById(R.id.ad);
        email = findViewById(R.id.email);
        telefon = findViewById(R.id.tel);
        sifre = findViewById(R.id.sifre);
        sifretekrar = findViewById(R.id.sifretekrar);
        kayit = findViewById(R.id.register);
        kayit.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(Register.this, "Şifreleri aynı giriniz.", Toast.LENGTH_SHORT).show();
                }else{
                    Database database = new Database(Register.this);
                    database.userAdd(isim.getText().toString(),email.getText().toString(),telefon.getText().toString(),sifre.getText().toString());
                }
            }
        });
    }
}
