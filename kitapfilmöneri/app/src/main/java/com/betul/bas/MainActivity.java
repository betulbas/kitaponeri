package com.betul.bas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.betul.bas.fragment.PageAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PageAdapter sectionsPagerAdapter = new PageAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.fab){
            startActivity(new Intent(MainActivity.this, Profil.class));
        }
        if (id == R.id.fab2){
            startActivity(new Intent(MainActivity.this, YeniOneriEkle.class));
            finish();
        }
    }
}