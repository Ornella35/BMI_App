package com.example.bmicalculator;
import android.app.usage.NetworkStats;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class users {

    String Height ;
    String Weight ;
    String Age ;
 double Result ;
    String Condition ;
    String Data;

    public users(double result, String Data) {
       this.Result = result;
        this.Data = Data;
    }

    public users(String height, String weight, String age, double result, String condition, String Data) {

        this.Height = height;
        this. Weight = weight;
        this. Age = age;
        this.Result = result;
        this.Condition = condition;
        this.Data = Data;
    }



    public String getHeight() {
        return Height;
    }

    public String getWeight() {
        return Weight;
    }

    public String getAge() {
        return Age;
    }

    public double getResult() {
        return Result;
    }

    public String getCondition() {
        return Condition;
    }

    public String getData() {
        return Data;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public void setAge(String age) {
        Age = age;
    }

    public void setResult(double result) {
        Result = result;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }

    public void setData(String Data) {
        this.Data = Data;
    }


    public users(){}
    public String toString(){

        return  this.Data
                + "\n Weight: " + this.Weight
                + "\n Result: " + this.Result
                + "\n Condition: " +  this.Condition;
    }


}


