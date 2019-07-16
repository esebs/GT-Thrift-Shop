package com.github.esebs.cs2340project.spacetrader.views;


import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.esebs.cs2340project.spacetrader.R;
import com.github.esebs.cs2340project.spacetrader.viewmodels.CreateUniverseViewModel;


/**
 * @version 1.0
 * @author Sebastian Escobar
 */
public class StartActivity extends AppCompatActivity {
    private Button startButton;
    private final CreateUniverseViewModel viewModel = new CreateUniverseViewModel();

    /**
     * Set up when activity starts
     * @param savedInstanceState current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Intent intent = getIntent();
        if (intent.getBooleanExtra("EXIT", false)) {
            finish();
        }

        startButton = findViewById(R.id.exitGame);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.createUniverse();
                Intent intent = new Intent(StartActivity.this, ConfigurationActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

}
