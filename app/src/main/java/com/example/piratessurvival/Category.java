package com.example.piratessurvival;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        // Check the intent for the "water_checkbox_visible" extra
        boolean isWaterCheckboxVisible = intent.getBooleanExtra("water_checkbox_visible", false);

        // Get the water checkbox ImageView
        ImageView waterCheckbox = findViewById(R.id.water_checkbox);

        // Set visibility based on the intent extra
        if (isWaterCheckboxVisible) {
            waterCheckbox.setVisibility(View.VISIBLE);
        } else {
            waterCheckbox.setVisibility(View.GONE);
        }

        boolean isFoodCheckboxVisible = intent.getBooleanExtra("food_checkbox_visible", false);

        // Get the water checkbox ImageView
        ImageView foodCheckbox = findViewById(R.id.food_checkbox);

        // Set visibility based on the intent extra
        if (isFoodCheckboxVisible) {
            foodCheckbox.setVisibility(View.VISIBLE);
        } else {
            foodCheckbox.setVisibility(View.GONE);
        }


        // Check the intent for the "tent_checkbox_visible" extra
        boolean isTentCheckboxVisible = intent.getBooleanExtra("tent_checkbox_visible", false);

        // Get the tent checkbox ImageView
        ImageView tentCheckbox = findViewById(R.id.tent_checkbox);

        // Set visibility based on the intent extra
        if (isTentCheckboxVisible) {
            tentCheckbox.setVisibility(View.VISIBLE);
        } else {
            tentCheckbox.setVisibility(View.GONE);
        }

        // Get the OK button
        Button okButton = findViewById(R.id.button_ok);

        // Set visibility based on the tent checkbox visibility
        if (isTentCheckboxVisible) {
            okButton.setVisibility(View.VISIBLE);
        } else {
            okButton.setVisibility(View.GONE);
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

    public void GoEnd(View view) {
        Intent intent = new Intent(this, GameEnd.class);
        startActivity(intent);
        finish();
    }
}
