package com.example.piratessurvival;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Game1Activity extends AppCompatActivity {
    Game1View gameView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new Game1View(this);
        setContentView(gameView);
    }
}
