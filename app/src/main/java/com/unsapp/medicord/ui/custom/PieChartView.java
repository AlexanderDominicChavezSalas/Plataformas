package com.unsapp.medicord.ui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Arrays;
import java.util.Locale;

public class PieChartView extends View {

    private Paint paint;
    private int[] dataPoints;
    private String[] categories;
    private float total = 0;
    public PieChartView(Context context) {
        super(context);
        init();
    }

    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        dataPoints = new int[]{10, 40, 50};
        total = 100;
        categories = new String[]{"Poco Importante", "Medio Importante", "Crítico"};
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Obtén las dimensiones de la vista
        int width = getWidth()-300;
        int height = getHeight();

        // Calcula el radio del círculo
        float radius = Math.min(width, height) * 0.4f;

        // Calcula el centro del círculo
        float centerX = width / 2f;
        float centerY = height / 2f;

        // Rectángulo que define el área del círculo
        RectF rectF = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);

        float startAngle = 0;
        int colorIndex = 0;

        for (int i = 0; i < dataPoints.length; i++) {
            float sweepAngle = 360 * (dataPoints[i]) / 100f;

            paint.setColor(getColorForIndex(colorIndex));
            // Dibuja una sección del pastel
            canvas.drawArc(rectF, startAngle, sweepAngle, true, paint);

            // Dibuja el porcentaje en el centro de cada sección
            float textAngle = startAngle + sweepAngle / 2;
            float x = (float) (centerX + Math.cos(Math.toRadians(textAngle)) * radius * 0.5);
            float y = (float) (centerY + Math.sin(Math.toRadians(textAngle)) * radius * 0.5);
            paint.setColor(Color.BLACK);
            paint.setTextSize(40);
            canvas.drawText(String.format(Locale.US,"%02d", dataPoints[i])+"%", x, y, paint);

            // Dibuja la leyenda
            float legendX = width;
            float legendY = (height/(dataPoints.length+4f)) * (i+2);
            paint.setColor(getColorForIndex(colorIndex));
            canvas.drawRect(legendX, legendY, legendX + 30, legendY + 30, paint);
            paint.setColor(Color.BLACK);
            paint.setTextSize(24);
            canvas.drawText(categories[i], legendX + 40, legendY + 20, paint);

            // Incrementa el ángulo de inicio para la próxima sección
            startAngle += sweepAngle;
            colorIndex++;
        }
    }

    private int getColorForIndex(int index) {
        // Colores para las categorías poco importante, medio importante y crítico
        int[] colors = {Color.GREEN, Color.YELLOW, Color.RED};
        return colors[index];
    }

    public void updateData(int[] newData, float total) {
        int[] temp = new int[3];
        int acumulado = 0;
        for (int i=0;i<3;i++) {
            temp[i] = (int) (newData[i]/total*100);
            acumulado += temp[i];
        }
        do {
            temp[2]++;
            acumulado++;
        }while (acumulado<total);
        this.dataPoints = temp;
        this.total = total;
        invalidate();
    }
}
