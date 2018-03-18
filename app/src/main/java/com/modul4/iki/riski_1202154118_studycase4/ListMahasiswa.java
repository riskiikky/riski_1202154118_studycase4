package com.modul4.iki.riski_1202154118_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class ListMahasiswa extends AppCompatActivity {

    private ListView mListView;
    private ProgressBar mProgressBar;
    private String [] mUsers= {
            "Riski","kevin","Andi","Anton","Arif",
            "Joni","Malik","Angga","Steve","Siti",
            "Azhar","Melki","Ryan","Ade","Iqbal",
            "Toni",}; //input string user dalam class ListMahasiswa

    private AddItemToListView mAddItemToListView;
    private Button mStartAsyncTask;  //MeniInisialisasi Array dan semua komponen yang akan digunakan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_mahasiswa); //set tampilan konten sesuai dengan list_mahasiswa

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mListView = (ListView) findViewById(R.id.listView);
        mStartAsyncTask = (Button) findViewById(R.id.button_startAsyncTask);
        mListView.setVisibility(View.GONE);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>())); //setAdapter terhadap ListView dengan menggunakan Array

        mStartAsyncTask.setOnClickListener(new View.OnClickListener() { //eksekusi tombol jika di klik StartAsyncTask
            @Override
            public void onClick(View view) {
                mAddItemToListView = new AddItemToListView(); //menambahkan item user ke dalam AsyncTask
                mAddItemToListView.execute(); //lakukan eksekusi menambahkan item ke list
            }
        });
    }

    public class AddItemToListView extends AsyncTask<Void, String, Void>{

        private ArrayAdapter<String> mAdapter; //set Adapter
        private int counter=1;
        ProgressDialog mProgressDialog = new ProgressDialog(ListMahasiswa.this); //tambahkan progress dialog didalam kelas ListMahasiswa
        //Deklarasi semua komponen yang akan digunakan

        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter();

            //untuk progress dialog
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setMessage("Please wait....");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0);

            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() { //eksekusi tombol cancel pada proses AsyncTask sedang berjalan
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mAddItemToListView.cancel(true); //fungsi tombol cancel dapat memberhentikan proses yang sedang berjalan
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialogInterface.dismiss(); //memberhentikan tampilan dialog pesan
                }
            });
            mProgressDialog.show();

            //Method ini digunakan untuk melakukan eksekusi progress dialog sebelum method onPostExecute dijalankan
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (String item : mUsers){ //item user
                publishProgress(item);
                try {
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace(); //menimpa list
                }
                if(isCancelled()){
                    mAddItemToListView.cancel(true); //jika klik tombol cancel dapat memberhentikan proses
                }
            }
            return null;

            //Method ini digunakan untuk melakukan aktivitas dibackground menggunakan AsyncTask
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);
            Integer current_status = (int) ((counter/(float)mUsers.length)*100);
            mProgressBar.setProgress(current_status); //progress dialog bar status
            mProgressDialog.setProgress(current_status); //progress dialog status sekarang
            mProgressDialog.setMessage(String.valueOf(current_status+"%"));
            counter++; //tambahkan

            //Method ini digunakan untuk menghitung presentase progress dialog
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mProgressBar.setVisibility(View.GONE);
            mProgressDialog.dismiss();
            mListView.setVisibility(View.VISIBLE);

            //Method ini digunakan untuk melakukan eksekusi setImageBitmap setelah method doInBackground dijalankan
        }
    }
}
