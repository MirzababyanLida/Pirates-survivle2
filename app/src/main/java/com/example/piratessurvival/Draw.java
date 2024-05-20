package com.example.piratessurvival;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Draw {
    private Paint textPaint = new Paint();
    private Paint healthPaint = new Paint();
    private Paint brickPaint = new Paint();
    private float textSize;

    public Draw(float textSize) {
        this.textSize = textSize;
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.LEFT);

        healthPaint.setColor(Color.GREEN);
        brickPaint.setColor(Color.argb(255, 249, 129, 0));
    }

    public void drawBall(Canvas canvas, Bitmap ball, float ballX, float ballY) {
        canvas.drawBitmap(ball, ballX, ballY, null);
    }

    public void drawPaddle(Canvas canvas, Bitmap paddle, float paddleX, float paddleY) {
        canvas.drawBitmap(paddle, paddleX, paddleY, null);
    }

    public void drawBricks(Canvas canvas, Brick[] bricks, int numBricks) {
        for (int i = 0; i < numBricks; i++) {
            if (bricks[i].getVisibility()) {
                canvas.drawRect(bricks[i].column * bricks[i].width, bricks[i].row * bricks[i].height,
                        bricks[i].column * bricks[i].width + bricks[i].width, bricks[i].row * bricks[i].height + bricks[i].height, brickPaint);
            }
        }
    }

    public void drawText(Canvas canvas, int points) {
        canvas.drawText("" + points, 20, textSize, textPaint);
    }

    public void drawHealth(Canvas canvas, int dWidth, int life) {
        if (life == 2) {
            healthPaint.setColor(Color.YELLOW);
        } else if (life == 1) {
            healthPaint.setColor(Color.RED);
        }
        canvas.drawRect(dWidth - 200, 30, dWidth - 200 + 60 * life, 80, healthPaint);
    }
}
