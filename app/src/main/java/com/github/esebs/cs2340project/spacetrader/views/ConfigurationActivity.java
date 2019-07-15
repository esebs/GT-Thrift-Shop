package com.github.esebs.cs2340project.spacetrader.views;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.esebs.cs2340project.spacetrader.R;
import com.github.esebs.cs2340project.spacetrader.entities.Difficulty;
import com.github.esebs.cs2340project.spacetrader.viewmodels.PlayerViewModel;


import java.util.Arrays;

/**
 * @version 1.0
 * @author Sebastian Escobar
 */
public class ConfigurationActivity extends AppCompatActivity {

    private PlayerViewModel viewModel = new PlayerViewModel();

    // Player Name
    private EditText editPlayerName;

    //Point Labels
    private TextView pilotPointsLabel;
    private TextView engineerPointsLabel;
    private TextView fighterPointsLabel;
    private TextView traderPointsLabel;
    private TextView totalSkillPoints;


    // Seek bars
    private SeekBar editPilotPoints;
    private SeekBar editEngineerPoints;
    private SeekBar editFighterPoints;
    private SeekBar editTraderPoints;

    private int pilotPoints;
    private int fighterPoints;
    private int traderPoints;
    private int engineerPoints;

    // Difficulty Spinner
    private Spinner difficultySpinner;

    // Buttons
    private Button startGame;
    private Button cancel;

    /**
     * Set up when activity starts
     * @param savedInstanceState current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        // Finds name text edit
        editPlayerName = findViewById(R.id.playerName);

        // Finds all point Labels
        pilotPointsLabel = findViewById(R.id.pilotLabel);
        engineerPointsLabel = findViewById(R.id.engineerLabel);
        fighterPointsLabel = findViewById(R.id.fighterLabel);
        traderPointsLabel = findViewById(R.id.traderLabel);
        totalSkillPoints = findViewById(R.id.skillPoints);

        // Finds all point seekbars
        editPilotPoints = findViewById(R.id.pilotBar);
        editEngineerPoints = findViewById(R.id.engineerBar);
        editFighterPoints = findViewById(R.id.fighterBar);
        editTraderPoints = findViewById(R.id.traderBar);

        // Finds Spinners
        difficultySpinner = findViewById(R.id.difficultySpinner);

        // Finds Buttons
        startGame = findViewById(R.id.exitGame);
        cancel = findViewById(R.id.goBack);

        ArrayAdapter<Difficulty> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                Arrays.asList(Difficulty.values()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

        // Individual category points
        pilotPoints = editPilotPoints.getProgress();
        fighterPoints = editFighterPoints.getProgress();
        traderPoints = editTraderPoints.getProgress();
        engineerPoints = editEngineerPoints.getProgress();


        // Changes values when SeekBar is moved
        editPilotPoints.setOnSeekBarChangeListener(pilotListener);
        editEngineerPoints.setOnSeekBarChangeListener(engineerListener);
        editFighterPoints.setOnSeekBarChangeListener(fighterListener);
        editTraderPoints.setOnSeekBarChangeListener(traderListener);

        // Creates player when button is selected
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // added Points
                int sumPoints = pilotPoints + fighterPoints + traderPoints
                        + engineerPoints;

                // Creates player when total points equals 20
                if (sumPoints != 20) {
                    Toast.makeText(ConfigurationActivity.this,
                            "Points must sum to 20", Toast.LENGTH_LONG).show();
                } else {
                    viewModel.setPlayer(editPlayerName.getText().toString(),
                            (Difficulty) difficultySpinner.getSelectedItem(),
                            pilotPoints,
                            fighterPoints,
                            traderPoints,
                            engineerPoints);
                    Intent intent = new Intent(ConfigurationActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ConfigurationActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
            }

        });
    }

    SeekBar.OnSeekBarChangeListener pilotListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes pilot points to new value and updates Points Remaining
         * @param seekBar editPilotPoints
         * @param progress current progress of pilot bar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            pilotPoints = progress;
            pilotPointsLabel.setText("Pilot: " + pilotPoints);
            totalSkillPoints.setText("Points Remaining: " + (20 - (pilotPoints
                    + fighterPoints + traderPoints + engineerPoints)));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // called after the user finishes moving the SeekBar
        }
    };

    SeekBar.OnSeekBarChangeListener engineerListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes engineer points to new value and updates Points Remaining
         * @param seekBar editEngineerPoints
         * @param progress current progress of engineer bar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            engineerPoints = progress;
            engineerPointsLabel.setText("Engineer: " + engineerPoints);
            totalSkillPoints.setText("Points Remaining: " + (20 - (pilotPoints
                    + fighterPoints + traderPoints + engineerPoints)));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // called after the user finishes moving the SeekBar
        }
    };

    SeekBar.OnSeekBarChangeListener fighterListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes fighter points to new value and updates Points Remaining
         * @param seekBar editFighterPoints
         * @param progress current progress of fighter bar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            fighterPoints = progress;
            fighterPointsLabel.setText("Fighter " + fighterPoints);
            totalSkillPoints.setText("Points Remaining: " + (20 - (pilotPoints
                    + fighterPoints + traderPoints + engineerPoints)));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // called after the user finishes moving the SeekBar
        }
    };

    SeekBar.OnSeekBarChangeListener traderListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes Trader points to new value and updates Points Remaining
         * @param seekBar editTraderPoints
         * @param progress current progress of trader bar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            traderPoints = progress;
            traderPointsLabel.setText("Trader: " + traderPoints);
            totalSkillPoints.setText("Points Remaining: " + (20 - (pilotPoints
                    + fighterPoints + traderPoints + engineerPoints)));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // called after the user finishes moving the SeekBar
        }
    };


}
