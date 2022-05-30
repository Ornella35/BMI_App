package com.example.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.type.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class User_interface extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private EditText height, weight, age;
    private ImageView male, female;
    private LinearLayout maleLayout, femaleLayout;
    private Button btncal;
    private Button btnsave;
    private TextView result,conditions,data;
    private String user ="0";
    private float h = 0, w = 0, bmi = 0;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interface);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getInstance().getReference();
        height = findViewById(R.id.heighttxt);
        weight = findViewById(R.id.weighttxt);
        age = findViewById(R.id.agetxt);
        male = findViewById(R.id.maleimg);
        female = findViewById(R.id.femaleimg);
        conditions = findViewById(R.id.conditions);
        maleLayout = findViewById(R.id.male);
        femaleLayout = findViewById(R.id.female);
        btncal = findViewById(R.id.btncal);
        btnsave = findViewById(R.id.btnsave);
        result = findViewById(R.id.res);
        data = findViewById(R.id.date_display);
       //Button btnShow = findViewById(R.id.btnShow);

        //DateTime dateTime = dateTime.get
        DateFormat  dateFormat = new SimpleDateFormat("dd-MM-yyyy  HH:mm:ss ");
        Calendar  calendar  = Calendar.getInstance();
        String dateTime = dateFormat.format(calendar.getTime());
        data.setText(dateTime);




        btnsave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(User_interface.this, Menu.class));

                String Data = data.getText().toString();
                String Height = height.getText().toString();
                String Weight= weight.getText().toString();
                String Age = age.getText().toString();
               double Result = Double.parseDouble(result.getText().toString());
                String Condition = conditions.getText().toString();
                String userId = mAuth.getUid();

                users user = new users(Height,Weight,Age,Result,Condition,Data);
               /* HashMap<String,Object> hashMap = new HashMap<>();
                //hashMap.put("datadisplay", str0);
                hashMap.put("height", str1);
                hashMap.put("weight", str2);
                hashMap.put("age", str3);
                hashMap.put("result", str4);
                hashMap.put("conditions", str5);*/

                databaseReference.child("users")

                       .child(userId)
                        .child(Data)
                        .setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(User_interface.this, "Data added successfully",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(User_interface.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
            }


        });

        maleLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                male.setBackgroundColor(0xFF21F3F3);
                female.setBackgroundColor(0);
                user = "Male";

            }
        });
        femaleLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                female.setBackgroundColor(0xFFE91EA5);
                male.setBackgroundColor(0);
                user = "Female";

            }
        });

        btncal.setOnClickListener(new OnClickListener() {

            @Override

            public void onClick(View v) {

                String Height = height.getText().toString().trim();
                String Weight= weight.getText().toString().trim();
                String Age= age.getText().toString().trim();
                if (user.equals("0")) {
                    Toast.makeText(User_interface.this, "Please select your Gender", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(Height)) {
                    height.setError("Enter Height");
                    height.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(Weight)) {
                    weight.setError("Enter Weight");
                    weight.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(Age)) {
                    age.setError("Enter Age");
                    age.requestFocus();
                    return;
                } else {

                    calculate();


                }




            }

        });

    }

    private void calculate(){
        /// startActivity(new Intent(User_interface.this, MainActivity.class));


        h = Float.parseFloat(height.getText().toString());
        w = Float.parseFloat(weight.getText().toString());

        float hm;
        hm = h / 100;
        bmi = (w / (hm * hm));
        // Result.setText(Float.toString(bmi));
        if (bmi >= 30){
            // Result.setText(Float.toString(bmi));
            conditions.setText("Obese Weigth");

        }
        else if (bmi >= 25){
            //  Result.setText(Float.toString(bmi));
            conditions.setText("Overweigth");
        }
        else if (bmi >= 18.5){
            // Result.setText(Float.toString(bmi));
            conditions.setText("IdealWeigth");
        }
        else
        {

            conditions.setText("Underweigth");
        }

        result.setText(String.format("%.2f",bmi));


    }
}






