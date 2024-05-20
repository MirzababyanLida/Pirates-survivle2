package com.example.piratessurvival;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Game1_Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game1_start);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, Game1Activity.class);
        startActivity(intent);
        finish();
    }

    public void info2(View view) {
        Intent intent = new Intent(this, Game2_info.class);
        startActivity(intent);
        finish();
    }



}