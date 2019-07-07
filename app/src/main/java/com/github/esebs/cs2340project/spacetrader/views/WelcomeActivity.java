package com.github.esebs.cs2340project.spacetrader.views;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.github.esebs.cs2340project.spacetrader.R;

/**
 * @version 1.0
 * @author Sebastian Escobar
 */
public class WelcomeActivity extends AppCompatActivity {
    private Button exit;

    /**
     * Set up when activity starts
     * @param savedInstanceState current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        exit = findViewById(R.id.exitGame);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }});
    }

}
