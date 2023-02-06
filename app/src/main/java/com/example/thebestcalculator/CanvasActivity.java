package com.example.thebestcalculator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CanvasActivity extends AppCompatActivity {

    private final int leftX = -10;
    private final int rightX = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }
    class DrawView extends View {
        public DrawView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setTextSize(20);
            paint.setAntiAlias(true);
            canvas.drawColor(Color.WHITE);
            for(int i = 0; i< canvas.getHeight();i+=30){
                canvas.drawLine(0,i,canvas.getWidth(),i,paint);
            }
            for(int j = 0; j<canvas.getWidth();j+=30){
                canvas.drawLine(j,0,j,canvas.getHeight(),paint);
            }
            paint.setColor(Color.GREEN);
            for(int k = leftX;k<=rightX;k++){
                int middleX = canvas.getWidth()/2;
                int middleY = canvas.getHeight()/2;
                canvas.drawRect(middleX-30*k,middleY-30*k,middleX-30*(k-1),middleY-30*(k-1),paint);
            }
        }
    }
}
