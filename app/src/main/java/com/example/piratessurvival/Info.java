package com.example.piratessurvival;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);
    }
    public void GoBack(View v){
        Intent intent = new Intent(this, Start.class);
        startActivity(intent);
    }
}