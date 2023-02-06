package com.example.thebestcalculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class OpenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open);
    }

    public void openCalc(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void openCanvas(View view){
        Intent intent = new Intent(this, CanvasActivity.class);
        startActivity(intent);
    }

    public void exit(View view){
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

}
