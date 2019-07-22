package com.github.esebs.cs2340project.spacetrader.views;


import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.esebs.cs2340project.spacetrader.R;
import com.github.esebs.cs2340project.spacetrader.entities.Building;
import com.github.esebs.cs2340project.spacetrader.entities.Player;
import com.github.esebs.cs2340project.spacetrader.viewmodels.UniverseViewModel;
import com.github.esebs.cs2340project.spacetrader.viewmodels.PlayerViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


/**
 * @version 1.0
 * @author Sebastian Escobar
 */
public class StartActivity extends AppCompatActivity {
    private final UniverseViewModel viewModel = new UniverseViewModel();
    private final PlayerViewModel playerViewModel = new PlayerViewModel();
    private Gson gson;

    /**
     * Set up when activity starts
     * @param savedInstanceState current state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        gson = new Gson();

        Intent intent = getIntent();
        if (intent.getBooleanExtra("EXIT", false)) {
            finish();
        }

        Button startButton = findViewById(R.id.exitGame);
        Button loadGame = findViewById(R.id.load);

        //read file
        File path = getApplicationContext().getFilesDir();
        File Buildingfile = new File(path, "Buildings.json");
        File playerFile = new File(path, "Player.json");

        if (!Buildingfile.exists() || !playerFile.exists()) {
            loadGame.setEnabled(false);
        }

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Building> buildings = viewModel.createUniverse();
                //Make file
                File path = getApplicationContext().getFilesDir();
                String json = gson.toJson(buildings);
                File file = new File(path, "Buildings.json");

                //write to file
                try {
                    FileOutputStream stream = new FileOutputStream(file);
                    stream.write(json.getBytes());
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(StartActivity.this, ConfigurationActivity.class);
                startActivity(intent);
                finish();

            }
        });

        loadGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File path = getApplicationContext().getFilesDir();
                File fileBuildings = new File(path, "Buildings.json");

                try {
                    int length = (int) fileBuildings.length();
                    byte[] bytes = new byte[length];
                    FileInputStream in = new FileInputStream(fileBuildings);
                    in.read(bytes);
                    String contents = new String(bytes);
                    in.close();
                    if (!contents.equals("")) {
                        Type listType = new TypeToken<ArrayList<Building>>(){}.getType();
                        List<Building> previousBuildings = new Gson().fromJson(contents, listType);
                        viewModel.setUniverse(previousBuildings);
                        Log.d("Loading Universe", contents);
                    }

                } catch (Exception e) {
                    Log.d("Error", "Start Activity Buildings Load");
                    e.printStackTrace();
                }

                File filePlayer = new File(path, "Player.json");
                try {
                    int length = (int) filePlayer.length();
                    byte[] bytes = new byte[length];
                    FileInputStream in = new FileInputStream(filePlayer);
                    in.read(bytes);
                    String contents = new String(bytes);
                    in.close();
                    if (!contents.equals("")) {
                        Player previousPlayer = gson.fromJson(contents, Player.class);
                        for (int l : previousPlayer.getCargoHold()) {
                            System.out.println(l);
                        }
                        playerViewModel.setPlayer(previousPlayer);
                        Log.d("Loading Player", contents);
                        playerViewModel.setLoaded(true);
                    }

                } catch (Exception e) {
                    Log.d("Error", "Start Activity Player Load");
                    e.printStackTrace();
                }

                Intent intent = new Intent(StartActivity.this, ConfigurationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
