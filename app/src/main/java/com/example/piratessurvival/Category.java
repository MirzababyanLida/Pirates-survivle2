package com.example.piratessurvival;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, Candycrush.class);
        startActivity(intent);
        finish();
    }
    public void StartActivity2(View view) {
        Intent intent = new Intent(this, Candycrush.class);
        startActivity(intent);
        finish();
    }


}