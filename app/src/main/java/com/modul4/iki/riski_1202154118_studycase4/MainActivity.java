package com.modul4.iki.riski_1202154118_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnList, btnCari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //set tampilan konten sesuai dengan activity_main

        btnList = (Button)findViewById(R.id.btn_list_mahasiswa); //cari dengan id button_list pada XML
        btnCari = (Button)findViewById(R.id.btn_cari_gambar); // cari dengan id button_cari pada XML

        btnList.setOnClickListener(new View.OnClickListener() { //eksekusi tombol jika di klik button list
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ListMahasiswa.class); //pindah tab dari MainActiviy menuju ListMahasiswa
                startActivity(i); //start aktivitas intent
            }
        });

        btnCari.setOnClickListener(new View.OnClickListener() { //eksekusi tombol jika di klik button cari
            @Override
            public void onClick(View view) {
                Intent j = new Intent(MainActivity.this, CariGambar.class); //pindah tab dari MainActivity menuju CariGambar
                startActivity(j); //start aktivitas intent
            }
        });
    }
}
