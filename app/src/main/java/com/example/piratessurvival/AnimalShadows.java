package com.example.piratessurvival;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AnimalShadows extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_shadows);
    }

    public void StartGameShadow(View view) {
        Intent intent = new Intent(this, Game4View.class);
        startActivity(intent);
        finish();
    }


    public void StartInfo4(View view) {
        Intent intent = new Intent(this, GAme4Info.class);
        startActivity(intent);
        finish();

    }
}