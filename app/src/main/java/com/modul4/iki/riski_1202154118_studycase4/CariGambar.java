package com.modul4.iki.riski_1202154118_studycase4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class CariGambar extends AppCompatActivity {

    private EditText edCari;
    private Button btnCari;
    private ImageView ivCari;
    private ProgressDialog progressDialog;
    private String text;

    //Deklarasi semua komponen yang akan digunakan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cari_gambar); //set tampilan konten sesuai dengan car_gambar

        edCari = (EditText) findViewById(R.id.ed_cari_gambar); //cari id dengan EditText ed_cari_gambar pada XML
        btnCari = (Button) findViewById(R.id.btn_cari); //cari id dengan button btn_cari pada XML
        ivCari = (ImageView) findViewById(R.id.iv_cari); //cari id dengan ImageView iv_cari pada XML

    }

    public void cariGambar(View view) {
        text = edCari.getText().toString();
        //Mengubah EditText ke dalam bentuk String
        if (text.isEmpty()) {
            Toast.makeText(this, "Masukkan URL gambar terlebih dahulu", Toast.LENGTH_LONG).show();
            //Jika EditText kosong akan memunculkan Toast
        } else {
            new DownloadGambar().execute(text);
            //Jika EditText berisi String maka akan di eksekusi
        }
    }

    private class DownloadGambar extends AsyncTask<String, Void, Bitmap> {
        // untuk proses dialog
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CariGambar.this); //menambahkan progress dialaog di dalam kelas CariGambar
            progressDialog.setTitle("Cari Gambar"); //progress dialog judul
            progressDialog.setMessage("Loading Gambar"); //progress dialog pesan
            progressDialog.setIndeterminate(false);
            progressDialog.show(); //progress dialog tampil

            //Method ini digunakan untuk melakukan eksekusi progress dialog sebelum method onPostExecute dijalankan
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;

            //Method ini digunakan untuk melakukan aktivitas dibackground menggunakan AsyncTask
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ivCari.setImageBitmap(bitmap);
            progressDialog.dismiss();

            //Method ini digunakan untuk melakukan eksekusi setImageBitmap setelah method doInBackground dijalankan
        }
    }
}