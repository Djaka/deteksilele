package com.djakapermana.deteksipenyakitlele;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.djakapermana.deteksipenyakitlele.Adapter.AdapterHistory;
import com.djakapermana.deteksipenyakitlele.Model.History;

import java.util.List;

public class HistoryDiagnosisActivity extends AppCompatActivity {

    RecyclerView recyclerViewHistory;
    AdapterHistory adapterHistory;
    Toolbar toolbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all:

                List<History> list = History.listAll(History.class);
                if(list.size()>0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Pesan");
                    builder.setMessage("Anda Akan Menghapus Semua Data ini?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            History.deleteAll(History.class);
                            loadHistory();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    builder.show();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Pesan");
                    builder.setMessage("Data Masih Kosoong");
                    builder.setCancelable(true);
                    builder.show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_diagnosis);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("History Diagnosis");

        recyclerViewHistory = findViewById(R.id.rec_history);
        loadHistory();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    public void loadHistory() {
        List<History> histories = History.listAll(History.class, "waktu DESC");

        adapterHistory = new AdapterHistory(histories);
        recyclerViewHistory.setAdapter(adapterHistory);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewHistory.setLayoutManager(linearLayoutManager);
        recyclerViewHistory.setItemAnimator(new DefaultItemAnimator());
    }
}