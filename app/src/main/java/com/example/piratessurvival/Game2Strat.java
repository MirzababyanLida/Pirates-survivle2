package com.example.piratessurvival;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class Game2Strat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game2_start);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public void StartGame2(View view) {
        Intent intent = new Intent(this, Candycrush.class);
        startActivity(intent);
        finish();

    }
    public void StartInfo2(View view) {
        Intent intent = new Intent(this, Game2Info.class);
        startActivity(intent);
        finish();

    }
}
