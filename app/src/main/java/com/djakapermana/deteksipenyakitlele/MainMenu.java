package com.djakapermana.deteksipenyakitlele;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    TextView textViewExam, textViewHistory, textViewAbout, textViewTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        textViewExam = findViewById(R.id.txt_exam);
        textViewHistory = findViewById(R.id.txt_history);
        textViewAbout = findViewById(R.id.txt_about);
        textViewTips = findViewById(R.id.txt_tips);

        textViewExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go To Exam for diagnosis

                Intent intent = new Intent(MainMenu.this, MainActivity.class);
                startActivity(intent);
            }
        });

        textViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go To History Diagnosis

                Intent intent = new Intent(MainMenu.this, HistoryDiagnosisActivity.class);
                startActivity(intent);
            }
        });

        textViewAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go To About Apps
                Intent intent = new Intent(MainMenu.this, AboutApps.class);
                startActivity(intent);
            }
        });

        textViewTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go To Tips And Trick
                Intent intent = new Intent(MainMenu.this, TipsActivity.class);
                startActivity(intent);
            }
        });
    }
}
