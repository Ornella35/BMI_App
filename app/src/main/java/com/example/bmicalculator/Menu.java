package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    private Button btncal, history, btnhome,btnstat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btncal = findViewById(R.id.Btncal);
        history = findViewById(R.id.history);
        btnhome = findViewById(R.id.home);
        btnstat = findViewById(R.id.btnstat);
       history.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               hist();
           }

           public void hist() {
               startActivity(new Intent(Menu.this, ShowData.class));
           }
       });
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home();

            }

            private void home() {
                startActivity(new Intent(Menu.this, MainActivity.class));
            }
        });
        btnstat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wykres();
            }

            private void wykres() {
                startActivity(new Intent(Menu.this, GraphActivity.class));
            }
        });
        btncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal();
            }

            private void cal() {
                startActivity(new Intent(Menu.this, User_interface.class));
            }
        });
    }


}