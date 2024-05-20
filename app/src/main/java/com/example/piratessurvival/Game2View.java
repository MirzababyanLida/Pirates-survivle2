package com.example.piratessurvival;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class Game2View extends View {
    Context context;
    float ballX, ballY;
    Velocity velocity = new Velocity(25, 32);
    Handler handler;

    final long UPDATE_MILLIS = 30;
    Runnable runnable;
    Draw draw;
    float TEXT_SIZE = 120;
    float paddleX, paddleY;
    int points = 0;
    int life = 3;
    Bitmap ball, paddle;
    int dWidth, dHeight;
    int ballWidth, ballHeight;

    Random random;
    Brick[] bricks = new Brick[30];
    int numBricks = 0;
    int brokenBricks = 0;
    boolean gameOver = false;

    public Game2View(Context context) {
        super(context);
        this.context = context;
        ball = BitmapFactory.decodeResource(getResources(), R.drawable.paddle4);
        paddle = BitmapFactory.decodeResource(getResources(), R.drawable.paddle2);
        draw = new Draw(TEXT_SIZE);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };

        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;

        random = new Random();
        ballX = random.nextInt(dWidth - 50);
        ballY = dHeight / 3;
        paddleY = (dHeight * 4) / 5;
        paddleX = (dWidth / 2) - (paddle.getWidth() / 2);
        ballWidth = ball.getWidth();
        ballHeight = ball.getHeight();

        createBricks();
        startGameLoop();
    }

    private void createBricks() {
        int brickWidth = dWidth / 8;
        int brickHeight = dHeight / 16;
        for (int column = 0; column < 8; column++) {
            for (int row = 0; row < 3; row++) {
                bricks[numBricks] = new Brick(row, column, brickWidth, brickHeight);
                numBricks++;
            }
        }
    }

    private void startGameLoop() {
        handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        ballX += velocity.getX();
        ballY += velocity.getY();

        if (ballX >= dWidth - ballWidth || ballX <= 0) {
            velocity.setX(velocity.getX() * -1);
        }

        if (ballY <= 0) {
            velocity.setY(velocity.getY() * -1);
        }

        if (ballY > paddleY && ballY < paddleY + paddle.getHeight() &&
                ballX > paddleX && ballX < paddleX + paddle.getWidth()) {
            velocity.setY(velocity.getY() * -1);
        }

        if (ballY > dHeight) {
            life--;
            if (life == 0) {
                gameOver = true;
                launchGameOver();
            } else {
                resetBall();
            }
        }

        draw.drawBall(canvas, ball, ballX, ballY);
        draw.drawPaddle(canvas, paddle, paddleX, paddleY);
        draw.drawBricks(canvas, bricks, numBricks);
        draw.drawText(canvas, points);
        draw.drawHealth(canvas, dWidth, life);

        for (int k = 0; k < numBricks; k++) {
            if (bricks[k].getVisibility() &&
                    ballX + ballWidth > bricks[k].column * bricks[k].width &&
                    ballX < bricks[k].column * bricks[k].width + bricks[k].width &&
                    ballY + ballHeight > bricks[k].row * bricks[k].height &&
                    ballY < bricks[k].row * bricks[k].height + bricks[k].height) {
                velocity.setY(velocity.getY() * -1);
                bricks[k].setInvisible();
                points += 10;
                brokenBricks++;
                if (brokenBricks == numBricks) {
                    gameOver = true;
                    launchGameOver();
                }
                break;
            }
        }

        if (!gameOver) {
            handler.postDelayed(runnable, UPDATE_MILLIS);
        }
    }

    private void resetBall() {
        ballX = random.nextInt(dWidth - ballWidth);
        ballY = dHeight / 3;
        velocity.setY(Math.abs(velocity.getY()));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            paddleX = event.getX() - paddle.getWidth() / 2;
            if (paddleX < 0) {
                paddleX = 0;
            } else if (paddleX > dWidth - paddle.getWidth()) {
                paddleX = dWidth - paddle.getWidth();
            }
        }
        return true;
    }

    private void launchGameOver() {
        handler.removeCallbacks(runnable);
        Intent intent = new Intent(context, Game2Over.class);
        intent.putExtra("points", points);
        context.startActivity(intent);
        ((Activity) context).finish();
    }
}
