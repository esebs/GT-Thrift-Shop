package com.github.esebs.cs2340project.spacetrader.views;

import androidx.appcompat.app.AppCompatActivity;
import com.github.esebs.cs2340project.spacetrader.R;
import com.github.esebs.cs2340project.spacetrader.model.Model;
import com.github.esebs.cs2340project.spacetrader.viewmodels.VehicleViewModel;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;


public class VehicleActivity extends AppCompatActivity {
    private Model model = Model.getModelInstance();

    private VehicleViewModel vehicleViewModel = new VehicleViewModel();

    private Button backButton;

    private Button buyUnicycle;
    private Button buyScooter;
    private Button buybike;
    private Button buyGolfCart;
    private Button buyMoped;

    private Button infoUnicycle;
    private Button infoScooter;
    private Button infobike;
    private Button infoGolfCart;
    private Button infoMoped;

    private TextView unicyclePrice;
    private TextView scooterPrice;
    private TextView bikePrice;
    private TextView golfCartPrice;
    private TextView mopedPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        backButton = findViewById(R.id.BackB);

        buyUnicycle = findViewById(R.id.buyU);
        buyScooter = findViewById(R.id.buyS);
        buybike = findViewById(R.id.buyB);
        buyGolfCart = findViewById(R.id.buyGF);
        buyMoped = findViewById(R.id.buyM);

        infoUnicycle = findViewById(R.id.infoU);
        infoScooter = findViewById(R.id.infoS);
        infobike = findViewById(R.id.infoB);
        infoGolfCart = findViewById(R.id.infoGF);
        infoMoped = findViewById(R.id.infoM);

        unicyclePrice = findViewById(R.id.costU);
        scooterPrice = findViewById(R.id.costS);
        bikePrice = findViewById(R.id.costB);
        golfCartPrice = findViewById(R.id.costGF);
        mopedPrice = findViewById(R.id.costM);

        backButton.setOnClickListener((e) ->{
            Intent i = new Intent(i.this, MainActivity.class);
            startActivity(i);
        });


    }
}
