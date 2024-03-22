package com.example.piratessurvival;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start.this,Hello.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void GoInfo(View v){
        Intent intent = new Intent(this, Info.class);
        startActivity(intent);
    }


}
