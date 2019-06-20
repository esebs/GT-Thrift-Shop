package com.github.esebs.cs2340project.spacetrader.views;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.esebs.cs2340project.spacetrader.R;

/**
 * @version 1.0
 * @author Sebastian Escobar
 */
public class MainActivity extends AppCompatActivity {
    private Button startButton;


    /**
     * Set up when activity starts
     * @param savedInstanceState current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        startButton = findViewById(R.id.exitGame);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
