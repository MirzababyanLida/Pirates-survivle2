package com.example.piratessurvival;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Game1Over extends AppCompatActivity {
    TextView tvPoints;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game1_over);
        tvPoints = findViewById(R.id.tvPoints);
        int points = getIntent().getExtras().getInt("points");
        tvPoints.setText("" + points);

    }
    public void GoBack2(View v){
        Intent intent = new Intent(Game1Over.this, Category.class);
        startActivity(intent);


    }
    public void restart(View view){
        Intent intent = new Intent(this, Game1Activity.class);
        startActivity(intent);
        finish();

    }
    public void exit(View view){
        finish();

    }

}
