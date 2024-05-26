package com.example.piratessurvival;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import java.util.Random;

public class Game1View extends View {

    int dWidth, dHeight;
    Bitmap trash, hand, plastic;
    Handler handler;
    Runnable runnable;
    long UPDATE_MILLIS = 30;
    int handX, handY;
    int plasticX, plasticY;
    Random random;
    boolean plasticAnimation = false;
    int points = 0;
    float TEXT_SIZE = 120;
    Paint textPaint;
    Paint healthPaint;
    int life = 3;
    Context context;
    int handSpeed;
    int trashX, trashY;

    public Game1View(Context context) {
        super(context);
        this.context = context;
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;

        plastic = BitmapFactory.decodeResource(getResources(), R.drawable.woodd);
        trash = BitmapFactory.decodeResource(getResources(), R.drawable.sunduk);
        hand = BitmapFactory.decodeResource(getResources(), R.drawable.hand);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        random = new Random();
        handX = dWidth + random.nextInt(300);
        handY = random.nextInt(600);
        plasticX = handX;
        plasticY = handY + hand.getHeight() - 30;
        textPaint = new Paint();
        textPaint.setColor(Color.rgb(255, 0, 0));
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setTextAlign(Paint.Align.LEFT);
        healthPaint = new Paint();
        healthPaint.setColor(Color.GREEN);
        handSpeed = 21 + random.nextInt(30);
        trashX = dWidth / 2 - trash.getWidth() / 2;
        trashY = dHeight - trash.getHeight();
    }

    boolean plasticInTrash = false;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);

        if (!plasticAnimation) {
            handX -= handSpeed;
            plasticX -= handSpeed;
        }

        if (handX <= -hand.getWidth()) {
            if (!plasticInTrash) {
                life--;
                if (life == 0) {
                    gameOver();
                    return; // Exit the method to prevent further drawing
                }
            }
            resetHandAndPlastic();
            plasticInTrash = false;
        }

        if (plasticAnimation) {
            plasticY += 40;
            if (plasticY + plastic.getHeight() >= dHeight) {
                if (plasticX + plastic.getWidth() / 2 >= trashX && plasticX + plastic.getWidth() / 2 <= trashX + trash.getWidth()) {
                    points++;
                    plasticInTrash = true;
                } else {
                    life--;
                    if (life == 0) {
                        gameOver();
                        return; // Exit the method to prevent further drawing
                    }
                }
                resetHandAndPlastic();
                plasticInTrash = false;
            }
        }

        // Draw game elements
        canvas.drawBitmap(trash, trashX, trashY, null);
        canvas.drawBitmap(hand, handX, handY, null);
        canvas.drawBitmap(plastic, plasticX, plasticY, null);
        canvas.drawText("" + points, 20, TEXT_SIZE, textPaint);
        if (life == 2)
            healthPaint.setColor(Color.YELLOW);
        else if (life == 1)
            healthPaint.setColor(Color.RED);
        canvas.drawRect(dWidth - 200, 30, dWidth - 200 * 60 * life, 80, healthPaint);

        if (points == 10) {
            showCongratulationsDialog();
            return;
        }

        if (life != 0)
            handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    private void resetHandAndPlastic() {
        handX = dWidth + random.nextInt(300);
        handY = random.nextInt(600);
        plasticX = handX;
        plasticY = handY + hand.getHeight() - 30;
        plasticAnimation = false;
    }

    private void gameOver() {
        if (points < 10) {
            showTryAgainDialog();
        } else {
            Intent intent = new Intent(context, Game1Over.class);
            intent.putExtra("points", points);
            context.startActivity(intent);
            ((Activity) context).finish();
        }
    }

    private void showCongratulationsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Поздравляем! Вы заработали 10 баллов.")
                .setCancelable(false)
                .create()
                .show();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, Category.class);
                intent.putExtra("fire_checkbox_visible", true); // Передаем параметр
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        }, 5000);
    }

    private void showTryAgainDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Попробуйте снова!")
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, id) -> {
                    Intent intent = new Intent(context, Game1_Start.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                })
                .create()
                .show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN && life > 0) {
            if (!plasticAnimation &&
                    (touchX >= handX && touchX <= (handX + hand.getWidth())
                            && touchY >= handY && touchY <= (handY + hand.getHeight()))) {
                plasticAnimation = true;
                return true; // Touch event handled
            }
        }
        return super.onTouchEvent(event);
    }
}
