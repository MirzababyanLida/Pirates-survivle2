package com.example.piratessurvival;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Game3Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3_info);
    }
    public void GoBackToGame3(View view) {
        Intent intent = new Intent(this, Arkanoid.class);
        startActivity(intent);
        finish();

    }
}