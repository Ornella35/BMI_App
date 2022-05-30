package com.example.bmicalculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GraphActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private TextView result,data;
    private  GraphView graph;
    String Data;
    double Result;
    ArrayList<Double> array2; //array for tmpHr value
    ArrayList<String> array7; //array for hrValueTimestamp
    LineGraphSeries<DataPoint> series ;
 long x = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getInstance().getReference();
        graph =findViewById(R.id.GraphView);
        String userId = mAuth.getUid();
      series = new LineGraphSeries<>();
        graph.addSeries(series);
       SimpleDateFormat  dateFormat = new SimpleDateFormat("dd-MM-yyyy  HH:mm:ss ");
        array2 = new ArrayList<>(); //array for Result
        array7 = new ArrayList<>(); //array for data
        databaseReference.child("users").child(userId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                Result = dataSnapshot.child("result").getValue(Double.class);
                Data = dataSnapshot.child("data").getValue(String.class);
                Log.i("Data value", "Data value " + Data);
                Log.i("Result value", "Result value " + Result);
                array2.add(Result);
                array7.add(Data);
                //x = x+1;
                DataPoint point = new DataPoint(x++, Result);
                series.appendData(point, false, 100);     // x++;
                graph.getGridLabelRenderer().setHorizontalLabelsAngle(20);
                graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
                    @Override
                    public String formatLabel(double value, boolean isValueX) {
                        if (isValueX) {
                            return  Data;//dateFormat.format(new Date((long) value));
                        }
                        else {
                            return super.formatLabel(value, false);
                        }
                    }
                });
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}