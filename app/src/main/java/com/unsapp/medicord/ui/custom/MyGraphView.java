package com.unsapp.medicord.ui.custom; // Declara el paquete donde reside la clase

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.IntSummaryStatistics;
import java.util.Locale;

public class MyGraphView extends View {

    private Paint barPaint;
    private Paint textPaint;
    private String[] daysOfWeek;
    private int[] dataPoints;
    private int maximum = 0;
    private Paint legend;

    public MyGraphView(Context context) {
        super(context);
        init();
    }

    public MyGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setDataPoints(int[] dataPoints) {
        this.dataPoints = dataPoints;
        this.daysOfWeek = getLastDays(dataPoints.length);
        IntSummaryStatistics stats = Arrays.stream(dataPoints)
                .summaryStatistics();
        this.maximum = stats.getMax();
        invalidate();
    }

    private void init() {
        setDataPoints(new int[] { 8, 9, 5, 3, 2, 4, 6});
        barPaint = new Paint();
        textPaint = new Paint();
        barPaint.setAntiAlias(true);
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(20);
        barPaint.setColor(Color.parseColor("#FF018786"));
        legend = new Paint();
        legend.setAntiAlias(true);
    }

    private String[] getLastDays(int length) {
        Log.d("lengthLENGHT", String.valueOf(length));
        String[] days = new String[length];
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < length; i++) {
            Calendar calDiaAnterior = (Calendar) calendar.clone();
            calDiaAnterior.add(Calendar.DAY_OF_YEAR, -i - 1);
            days[length-i-1] = sdf.format(calDiaAnterior.getTime());
        }

        return days;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        if (dataPoints == null || dataPoints.length == 0) {
            return;
        }

        float width = getWidth()-200;
        float height = getHeight();

        float barWidth = width / (dataPoints.length * 2);
        float pivot = (barWidth/2);
        float pivotLegend = 40;
        float graphHeight = height - 60;

        for (int i = 0; i < dataPoints.length; i++) {
            float left = i * (barWidth * 2);
            float right = left + barWidth;
            float bottom = height - 60;
            float top = bottom - (graphHeight * dataPoints[i] / 10f);

            canvas.drawRect(left+pivot+pivotLegend, top, right+pivot+pivotLegend, bottom, barPaint);
            canvas.drawText(daysOfWeek[i], left + barWidth + pivotLegend - textPaint.measureText(daysOfWeek[i]) / 2, height - 20, textPaint);
            canvas.drawText(String.valueOf(dataPoints[i]), left + barWidth + pivotLegend - textPaint.measureText(String.valueOf(dataPoints[i])) / 2, top - 10, textPaint);
        }
        float divi = graphHeight/(maximum+1);
        for (int i = 0; i <= maximum; i+=maximum/dataPoints.length){
            canvas.drawText(String.valueOf(i),10,height-(divi*i)-60,textPaint);
        }

        // Dibuja la leyenda
        float legendX = width+pivotLegend;
        float legendY = (height/2f);
        legend.setColor(Color.parseColor("#FF018786"));
        canvas.drawRect(legendX, legendY, legendX + 30, legendY + 30, legend);
        legend.setColor(Color.BLACK);
        legend.setTextSize(24);
        canvas.drawText("Nº de Medic.", legendX + 40, legendY + 20, legend);

        Paint axisPaint = new Paint();
        axisPaint.setColor(Color.BLACK);
        axisPaint.setStrokeWidth(3);

        canvas.drawLine(pivotLegend, height - 60, width+pivotLegend, height - 60, axisPaint);
        canvas.drawLine(pivotLegend, 0, pivotLegend, height - 60, axisPaint);
    }
}