package com.example.piratessurvival;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Game2Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2_info);
    }
    public void GoBackToGame2(View view) {
        Intent intent = new Intent(this, Game2Strat.class);
        startActivity(intent);
        finish();

    }
}