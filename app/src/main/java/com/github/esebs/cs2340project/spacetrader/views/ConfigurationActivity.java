package com.github.esebs.cs2340project.spacetrader.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.github.esebs.cs2340project.spacetrader.R;
import com.github.esebs.cs2340project.spacetrader.model.Difficulty;

import java.util.Arrays;

public class ConfigurationActivity extends AppCompatActivity {
    private EditText editPlayerName;
    private SeekBar editPilotPoints;
    private SeekBar editEngineerPoints;
    private SeekBar editFighterPoints;
    private SeekBar editTraderPoints;
    private Spinner difficultySpinner;

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

        ArrayAdapter<Difficulty> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                Arrays.asList(Difficulty.values()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);
    }


}
