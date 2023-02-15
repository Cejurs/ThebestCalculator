package com.example.thebestcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView numberA;
    private TextView numberB;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration conf = getResources().getConfiguration();
        switch (conf.orientation){
            case (Configuration.ORIENTATION_LANDSCAPE):
                setContentView(R.layout.horizontal_calc);
                numberA = findViewById(R.id.numberA2);
                numberB = findViewById(R.id.numberB2);
                result = findViewById(R.id.resultValue2);
                break;
            case (Configuration.ORIENTATION_PORTRAIT):
                setContentView(R.layout.verical_calc);
                numberA = findViewById(R.id.numberA);
                numberB = findViewById(R.id.NumberB);
                result = findViewById(R.id.ResultValue);
                break;
        }
        checkCompatibility();
    }

    private void checkCompatibility(){
        DisplayMetrics display = getResources().getDisplayMetrics();
        if ((display.heightPixels>=1920 & display.widthPixels>=1080)||
                (display.heightPixels>=1080 & display.widthPixels>=1920)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Ошибка!")
                    .setMessage("Несовместимое устройство!")
                    .setCancelable(false)
                    .setNegativeButton("Выход",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    private double[] parseNumbers(){
        double[] array = new double[2];
        try{
            array[0] = Double.parseDouble(numberA.getText().toString());
            array[1] = Double.parseDouble(numberB.getText().toString());
        }
        catch (Exception e){
            array[0] = Double.NaN;
            array[1] = Double.NaN;
        }
        return array;
    }
    private void showError(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Ошибка!")
                .setMessage(message)
                .setNegativeButton("Выход",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void sumTwoNumbers(View view){
        double[] array =parseNumbers();
        if(Double.isNaN(array[0])){
            showError("Неверные данные");
            return;
        }
        Double str = array[0]+array[1];
        result.setText(str.toString());
    }
    public void divideTwoNumbers(View view){
        double[] array = parseNumbers();
        if(Double.isNaN(array[0])){
            showError("Неверные данные");
            return;
        }
        if(array[1]==0){
            showError("Деление на 0");
            return;
        }
        Double str = array[0]/array[1];
        result.setText(str.toString());
    }
    public void minusTwoNumbers(View view){
        double[] array =parseNumbers();
        if(Double.isNaN(array[0])){
            showError("Неверные данные");
            return;
        }
        Double str = array[0]-array[1];
        result.setText(str.toString());
    }
    public void multiplyTwoNumbers(View view){
        double[] array =parseNumbers();
        if(Double.isNaN(array[0])){
            showError("Неверные данные");
            return;
        }
        Double str = array[0]*array[1];
        result.setText(str.toString());
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        switch (newConfig.orientation){
            case (Configuration.ORIENTATION_LANDSCAPE):
                setContentView(R.layout.horizontal_calc);
                CharSequence temp = numberA.getText();
                numberA = findViewById(R.id.numberA2);
                numberA.setText(temp);
                temp = numberB.getText();
                numberB = findViewById(R.id.numberB2);
                numberA.setText(temp);
                result = findViewById(R.id.resultValue2);
                break;
            case (Configuration.ORIENTATION_PORTRAIT):
                setContentView(R.layout.verical_calc);
                CharSequence temp2 = numberA.getText();
                numberA = findViewById(R.id.numberA);
                numberA.setText(temp2);
                temp2 = numberB.getText();
                numberB = findViewById(R.id.numberB2);
                numberA.setText(temp2);
                result = findViewById(R.id.resultValue2);
                break;
        }
    }
}