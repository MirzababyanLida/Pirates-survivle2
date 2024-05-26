package com.example.piratessurvival;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Game1_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1_info);
    }
    public void GoBackToGame1(View view) {
        Intent intent = new Intent(this, Game1_Start.class);
        startActivity(intent);
        finish();
    }
}