package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    private Button btnLogout, btnShow, btngraph;
    private TextView datadisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar calendar  = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        datadisplay = findViewById(R.id.date_display);
        datadisplay.setText(currentDate);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        btnLogout = findViewById(R.id.btnLogout);
       // btnShow = findViewById(R.id.btnShow);
       // btngraph = findViewById(R.id.btngraph);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();

            }
        });
       /* btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Showdata();
            }

            public void Showdata() {
                startActivity(new Intent(MainActivity.this, ShowData.class));
            }
        });
       btngraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { Graph(); }

            public void Graph() {
                startActivity(new Intent(MainActivity.this, GraphActivity.class));
            }
        });*/
    }


    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(MainActivity.this, login.class));
        }
    }


    public void Logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this, login.class));
    }
}
