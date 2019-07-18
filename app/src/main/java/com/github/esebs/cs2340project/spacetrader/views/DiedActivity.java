package com.github.esebs.cs2340project.spacetrader.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.github.esebs.cs2340project.spacetrader.R;
import com.github.esebs.cs2340project.spacetrader.views.WelcomeActivity;

public class DiedActivity extends AppCompatActivity {
    private Button exit;
    private Button restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_died);

        exit = findViewById(R.id.button_exit);
        restart = findViewById(R.id.button_reset);

        @Override
        exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.exit(0);
                    }
                }
        );

        @Override
<<<<<<< HEAD
        restart.setOnClickListener(e -> {
            Intent intent = new Intent(DiedActivity.this, WelcomeActivity.class);
            startActivity(intent);
=======
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiedActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
>>>>>>> b0e4da03179ebd87e3baafbcce49fa4a8c72fc1d
        });

    }
}
