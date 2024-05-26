package com.example.piratessurvival;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class Arkanoid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public void startGame3(View view){
        Game3View gameView = new Game3View(this);
        setContentView(gameView);
    }

    public void info2(View view) {
        Intent intent = new Intent(this, Game3Info.class);
        startActivity(intent);
        finish();

    }
}