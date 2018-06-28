package com.djakapermana.deteksipenyakitlele;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.djakapermana.deteksipenyakitlele.Adapter.AdapterGejala;
import com.djakapermana.deteksipenyakitlele.Model.History;
import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.djakapermana.deteksipenyakitlele.Helper.GeneralFunction.getHari;

public class MainActivity extends AppCompatActivity {

    Map<Integer, String> gejala = new HashMap<>();
    List<Integer> diagnosis;
    Map<String, String> fakta = new HashMap<>();
    Button buttonYes, buttonNo, buttonDiagnosa, buttonPeriksa, buttonSave;
    TextView textViewCiri, textViewKeterangan, textViewSolusi;
    LinearLayout linearLayoutButton, linearLayoutButton1, linearLayoutButton2;
    RecyclerView recyclerViewGejala;
    private ProgressDialog progressDialog;
    AdapterGejala adapterGejala;
    ScrollView scrollViewContain;
    String penyakit;
    int solusi, keterangan;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Konwledge Base
        gejala.put(1, "Perut Ikan Membengkak Dan Mengeluarkan Cairan Getah Bening");
        gejala.put(2, "Luka Di bagian Perut atau Sirif dan Ada pendarahan");
        gejala.put(3, "Ikan Terlihat Lemas");
        gejala.put(4, "Warna Tubuh Ikan Kusam");
        gejala.put(5, "Ikan Terlihat Bringas di dinding atau dasar kolam");
        gejala.put(6, "Ada Ikan Yang Mati");
        gejala.put(7, "Berenang Berputar Putar");
        gejala.put(8, "Muncul membalik Di Permukaan");

        // conclution
        fakta.put("A", "Aeromonas hydrophila dan Pseudomonas hydrophyla");
        fakta.put("B", "Gatal Trichodiniasis");
        fakta.put("C", "Parasit / Jamur saprolegnia");
        fakta.put("D", "Serangan Channel catfish virus");

        buttonYes = findViewById(R.id.btn_yes);
        buttonNo = findViewById(R.id.btn_no);
        buttonDiagnosa = findViewById(R.id.btn_diagnosa);
        buttonPeriksa = findViewById(R.id.btn_periksa);
        buttonSave = findViewById(R.id.btn_save);
        recyclerViewGejala = findViewById(R.id.rec_daftar_gejala);

        textViewCiri = findViewById(R.id.txt_ciri);
        textViewKeterangan = findViewById(R.id.txt_keterangan);
        textViewSolusi = findViewById(R.id.txt_solusi);
        scrollViewContain = findViewById(R.id.scrollcontain);
        linearLayoutButton = findViewById(R.id.lin_button);
        linearLayoutButton1 = findViewById(R.id.lin_button1);
        linearLayoutButton2 = findViewById(R.id.lin_button2);

        diagnosis = new ArrayList<Integer>();

        progressDialog = new ProgressDialog(this);

        loadGejala();

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if (i > gejala.size()) {
                    recyclerViewGejala.setVisibility(View.VISIBLE);
                    linearLayoutButton.setVisibility(View.VISIBLE);
                    linearLayoutButton2.setVisibility(View.VISIBLE);
                    loadListGejala();
                } else {
                    diagnosis.add(i - 1);
                    textViewCiri.setText(gejala.get(i));
                }
            }
        });

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if (i > gejala.size()) {
                    recyclerViewGejala.setVisibility(View.VISIBLE);
                    linearLayoutButton.setVisibility(View.VISIBLE);
                    linearLayoutButton2.setVisibility(View.VISIBLE);
                    loadListGejala();
                } else {
                    textViewCiri.setText(gejala.get(i));
                }
            }
        });



        buttonDiagnosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        buttonPeriksa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                periksaKembali();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Saving Data...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setCancelable(false);
                progressDialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            saveDiagnosis();
                            progressDialog.dismiss();
                        }catch (Exception e){
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                },3000);

            }
        });
    }

    // Inference machine
    private void calculate() {
        progressDialog.setMessage("Diagnosting...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    textViewCiri.setVisibility(View.VISIBLE);
                    textViewKeterangan.setVisibility(View.VISIBLE);
                    textViewSolusi.setVisibility(View.VISIBLE);

                    progressDialog.dismiss();
                    if ((diagnosis.get(0) == 1 && diagnosis.get(1) == 2)) {
                        penyakit = fakta.get("A");
                        keterangan = R.string.ket1;
                        solusi = R.string.solusi1;
                        textViewCiri.setText(penyakit);
                        textViewKeterangan.setText(keterangan);
                        textViewSolusi.setText(solusi);
                    } else if (diagnosis.get(0) == 3 && diagnosis.get(1) == 4 && diagnosis.get(2) == 5) {
                        penyakit = fakta.get("B");
                        keterangan = R.string.ket2;
                        solusi = R.string.solusi2;
                        textViewCiri.setText(penyakit);
                        textViewKeterangan.setText(penyakit);
                        textViewSolusi.setText(penyakit);
                    } else if (diagnosis.get(0) == 3 && diagnosis.get(1) == 6) {
                        penyakit = fakta.get("C");
                        keterangan = R.string.ket3;
                        solusi = R.string.solusi3;
                        textViewCiri.setText(penyakit);
                        textViewKeterangan.setText(keterangan);
                        textViewSolusi.setText(solusi);
                    } else if (diagnosis.get(0) == 7 && diagnosis.get(1) == 8) {
                        penyakit = fakta.get("D");
                        keterangan = R.string.ket4;
                        solusi = R.string.solusi4;
                        textViewCiri.setText(penyakit);
                        textViewKeterangan.setText(keterangan);
                        textViewSolusi.setText(solusi);
                    } else {
                        textViewCiri.setText("Tidak Ditemukan Penyakit Apapun !");
                        textViewKeterangan.setVisibility(View.GONE);
                        textViewSolusi.setVisibility(View.GONE);
                    }

                    loadCalculate();
                    recyclerViewGejala.setVisibility(View.GONE);

                } catch (Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }, 3000);
    }

    private List<String> gejala() {
        List<String> list = new ArrayList<>();
        for (int i : diagnosis) {
            list.add(gejala.get(i));
        }
        return list;
    }

    private void loadListGejala() {
        textViewCiri.setVisibility(View.GONE);
        linearLayoutButton1.setVisibility(View.GONE);
        linearLayoutButton2.setVisibility(View.VISIBLE);
        buttonSave.setVisibility(View.GONE);
        buttonDiagnosa.setVisibility(View.VISIBLE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewGejala.setLayoutManager(linearLayoutManager);
        recyclerViewGejala.setItemAnimator(new DefaultItemAnimator());
        adapterGejala = new AdapterGejala(gejala());
        recyclerViewGejala.setAdapter(adapterGejala);
    }

    private void periksaKembali() {
        diagnosis.removeAll(diagnosis);
        keterangan = 0;
        textViewKeterangan.setVisibility(View.GONE);
        solusi = 0;
        textViewSolusi.setVisibility(View.GONE);
        recyclerViewGejala.setVisibility(View.GONE);
        linearLayoutButton2.setVisibility(View.GONE);
        loadGejala();
    }

    private void loadGejala() {
        i = 1;
        textViewCiri.setText(gejala.get(i));
        textViewCiri.setVisibility(View.VISIBLE);
        linearLayoutButton.setVisibility(View.VISIBLE);
        linearLayoutButton1.setVisibility(View.VISIBLE);
    }

    private void loadCalculate() {
        linearLayoutButton.setVisibility(View.VISIBLE);
        linearLayoutButton2.setVisibility(View.VISIBLE);
        buttonDiagnosa.setVisibility(View.GONE);
        buttonSave.setVisibility(View.VISIBLE);
    }

    private void saveDiagnosis() {
        History history = new History();
        history.setWaktu(getHari());
        history.setPenyakit(penyakit);
        history.setKeterangan(keterangan);
        history.setSolusi(solusi);
        history.save();
        Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
        finish();
    }

}
