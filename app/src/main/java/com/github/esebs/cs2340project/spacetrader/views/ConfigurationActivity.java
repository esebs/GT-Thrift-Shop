package com.github.esebs.cs2340project.spacetrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.esebs.cs2340project.spacetrader.R;
import com.github.esebs.cs2340project.spacetrader.model.Difficulty;
import com.github.esebs.cs2340project.spacetrader.model.Player;


import java.util.Arrays;

public class ConfigurationActivity extends AppCompatActivity {


    private EditText editPlayerName;
    private SeekBar editPilotPoints;
    private SeekBar editEngineerPoints;
    private SeekBar editFighterPoints;
    private SeekBar editTraderPoints;
    private Spinner difficultySpinner;
    private Button startGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        editPlayerName = findViewById(R.id.playerName);
        editPilotPoints = findViewById(R.id.pilotBar);
        editEngineerPoints = findViewById(R.id.engineerBar);
        editFighterPoints = findViewById(R.id.fighterBar);
        editTraderPoints = findViewById(R.id.traderBar);
        difficultySpinner = findViewById(R.id.difficultySpinner);
        startGame = findViewById(R.id.exitGame);

        ArrayAdapter<Difficulty> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                Arrays.asList(Difficulty.values()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pilotPoints = editPilotPoints.getProgress();
                int fighterPoints = editFighterPoints.getProgress();
                int traderPoints = editTraderPoints.getProgress();
                int engineerPoints = editEngineerPoints.getProgress();
                int sumPoints = pilotPoints + fighterPoints + traderPoints
                        + engineerPoints;

                if (sumPoints != 20) {
                    Toast.makeText(ConfigurationActivity.this,
                            "Points must sum to 20", Toast.LENGTH_LONG).show();
                } else {
                    Player player = new Player(editPlayerName.getText().toString(),
                            (Difficulty) difficultySpinner.getSelectedItem(),
                            pilotPoints,
                            fighterPoints,
                            traderPoints,
                            engineerPoints);
                    System.out.println(player);
                    Intent intent = new Intent(ConfigurationActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }


}
