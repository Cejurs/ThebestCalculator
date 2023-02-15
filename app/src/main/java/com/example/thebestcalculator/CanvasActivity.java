package com.example.thebestcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CanvasActivity extends AppCompatActivity {

    private final int leftX = -20;
    private final int rightX = 20;
    private final int step = 30;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
        db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS sinPoints (x FLOAT, y FLOAT,ext TEXT)");
    }
    class DrawView extends View {
        public DrawView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            db.execSQL("DELETE FROM sinPoints");
            int width = canvas.getWidth();
            int height = canvas.getHeight();

            canvas.drawColor(Color.WHITE);

            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(2);
            paint.setColor(Color.BLACK);

            Path grid = createGrid(width,height);
            canvas.drawPath(grid,paint);

            paint.setColor(Color.RED);
            paint.setStrokeWidth(10);

            Path sin = createSin(width,height);
            canvas.drawPath(sin,paint);
            claimExt(canvas,paint);
        }
        private void claimExt(Canvas canvas,Paint paint){
            paint.setColor(Color.GREEN);
            Cursor data = db.rawQuery
                    ("SELECT * FROM sinPoints where ext='YES'",null);
            while(data.moveToNext()){
                float x = data.getFloat(0);
                float y = data.getFloat(1);
                canvas.drawLine(x,y,x,y,paint);
            }
        }
        private Path createSin(int width, int height){
            Path sin = new Path();
            int midY = height/step/2*step;
            sin.moveTo(0,midY);
            int interval = Math.abs(leftX) + Math.abs(rightX);
            float size = width/interval;
            for(float i = 0; i<=interval;i+=0.1){
                float sinValue =(float)Math.sin(i-leftX);
                float y = midY+size*sinValue;
                float x = i*size;
                setValuesOnDB(x,y,sinValue);
                sin.lineTo(x,y);
            }
            return sin;
        }
        private void setValuesOnDB(float x,float y, float sinValue){
            String ext = (sinValue >= 0.999 || sinValue <= -0.999) ? "YES" : "NO";
            ContentValues insertValues = new ContentValues();
            insertValues.put("x",x);
            insertValues.put("y",y);
            insertValues.put("ext",ext);
            db.insert("sinPoints",null,insertValues);
        }
        private Path createGrid(int width,int height){
            Path grid = new Path();
            for(int i = 0; i< height;i+=step){
                grid.moveTo(0,i);
                grid.lineTo(width,i);
            }
            for(int j = 0; j<width;j+=step) {
                grid.moveTo(j,0);
                grid.lineTo(j,height);
            }
            return grid;
        }
    }
}
