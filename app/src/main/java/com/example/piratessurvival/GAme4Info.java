package com.example.piratessurvival;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GAme4Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4_info);
    }
    public void  GoBackToGame4(View view) {
        Intent intent = new Intent(this, AnimalShadows.class);
        startActivity(intent);
        finish();

    }

}