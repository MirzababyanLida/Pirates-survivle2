package com.example.piratessurvival;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);

        // Check the intent for the "fire_checkbox_visible" extra
        Intent intent = getIntent();
        boolean isFireCheckboxVisible = intent.getBooleanExtra("fire_checkbox_visible", false);

        // Get the fire checkbox ImageView
        ImageView fireCheckbox = findViewById(R.id.fire_checkbox);

        // Set visibility based on the intent extra
        if (isFireCheckboxVisible) {
            fireCheckbox.setVisibility(View.VISIBLE);
        } else {
            fireCheckbox.setVisibility(View.GONE);
        }
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, Game1_Start.class);
        startActivity(intent);
        finish();
    }

    public void StartActivity2(View view) {
        Intent intent = new Intent(this, Game2Strat.class);
        startActivity(intent);
        finish();
    }

    public void ArkanoidStart(View view) {
        Intent intent = new Intent(this, AnimalShadows.class);
        startActivity(intent);
        finish();
    }

    public void StartGame4(View view) {
        Intent intent = new Intent(this, Arkanoid.class);
        startActivity(intent);
        finish();
    }
}
