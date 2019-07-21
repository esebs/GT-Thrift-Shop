package com.github.esebs.cs2340project.spacetrader.views;

import androidx.appcompat.app.AppCompatActivity;
import com.github.esebs.cs2340project.spacetrader.R;
import com.github.esebs.cs2340project.spacetrader.entities.Vehicle;
import com.github.esebs.cs2340project.spacetrader.model.Model;
import com.github.esebs.cs2340project.spacetrader.viewmodels.VehicleViewModel;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


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

        setAllText();
        backButton.setOnClickListener((e) ->{
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

        buyUnicycle.setOnClickListener((e) ->{
            if(model.getPlayer().getVehicle().equals(Vehicle.UNICYCLE)){
                Toast.makeText(getBaseContext(),"This is your current vehicle.", Toast.LENGTH_LONG).show();
            } else if (vehicleViewModel.canBuyVehicle(Vehicle.UNICYCLE)) {
                vehicleViewModel.buyVehicle(Vehicle.UNICYCLE);
                setAllText();
            } else {
                Toast.makeText(getBaseContext(),"Sorry, you don't have enough money.", Toast.LENGTH_LONG).show();
            }
        });

        buyScooter.setOnClickListener((e) ->{
            if(model.getPlayer().getVehicle().equals(Vehicle.SCOOTER)){
                Toast.makeText(getBaseContext(),"This is your current vehicle.", Toast.LENGTH_LONG).show();
            } else if (vehicleViewModel.canBuyVehicle(Vehicle.SCOOTER)) {
                vehicleViewModel.buyVehicle(Vehicle.SCOOTER);
                setAllText();

            } else {
                Toast.makeText(getBaseContext(),"Sorry, you don't have enough money.", Toast.LENGTH_LONG).show();
            }
        });

        buybike.setOnClickListener((e) ->{
            if(model.getPlayer().getVehicle().equals(Vehicle.BIKE)){
                Toast.makeText(getBaseContext(),"This is your current vehicle.", Toast.LENGTH_LONG).show();
            } else if (vehicleViewModel.canBuyVehicle(Vehicle.BIKE)) {
                vehicleViewModel.buyVehicle(Vehicle.BIKE);
                setAllText();
            } else {
                Toast.makeText(getBaseContext(),"Sorry, you don't have enough money.", Toast.LENGTH_LONG).show();
            }
        });

        buyGolfCart.setOnClickListener((e) ->{
            if(model.getPlayer().getVehicle().equals(Vehicle.GOLF_CART)){
                Toast.makeText(getBaseContext(),"This is your current vehicle.", Toast.LENGTH_LONG).show();
            } else if (vehicleViewModel.canBuyVehicle(Vehicle.GOLF_CART)) {
                vehicleViewModel.buyVehicle(Vehicle.GOLF_CART);
                setAllText();
            } else {
                Toast.makeText(getBaseContext(),"Sorry, you don't have enough money.", Toast.LENGTH_SHORT).show();
            }
        });

        buyMoped.setOnClickListener((e) ->{
            if(model.getPlayer().getVehicle().equals(Vehicle.MOPED)){
                Toast.makeText(getBaseContext(),"This is your current vehicle.", Toast.LENGTH_LONG).show();
            } else if (vehicleViewModel.canBuyVehicle(Vehicle.MOPED)) {
                vehicleViewModel.buyVehicle(Vehicle.MOPED);
                setAllText();
            } else {
                Toast.makeText(getBaseContext(),"Sorry, you don't have enough money.", Toast.LENGTH_LONG).show();
            }
        });

        infoUnicycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"Fuel Range: 10, Cargo Size: 10, Max Health: 25", Toast.LENGTH_SHORT).show();
            }
        });

        infoScooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"Fuel Range: 14, Cargo Size: 15, Max Health: 100", Toast.LENGTH_SHORT).show();
            }
        });

        infobike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"Fuel Range: 20, Cargo Size: 20, Max Health: 200", Toast.LENGTH_SHORT).show();
            }
        });

        infoGolfCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"Fuel Range: 25, Cargo Size: 60, Max Health: 400", Toast.LENGTH_SHORT).show();
            }
        });

        infoMoped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"Fuel Range: 50, Cargo Size: 35, Max Health: 350", Toast.LENGTH_SHORT).show();
            }
        });

        }
        public void setAllText(){
            int UCost = vehicleViewModel.getVehiclePrice(Vehicle.UNICYCLE);
            String U = "$" + UCost;
            unicyclePrice.setText(U);
            int SCost = vehicleViewModel.getVehiclePrice(Vehicle.SCOOTER);
            String S = "$" + SCost;
            scooterPrice.setText(S);
            int BCost = vehicleViewModel.getVehiclePrice(Vehicle.BIKE);
            String B = "$" + BCost;
            bikePrice.setText(B);
            int GCost = vehicleViewModel.getVehiclePrice(Vehicle.GOLF_CART);
            String G = "$" + GCost;
            golfCartPrice.setText(G);
            int MCost = vehicleViewModel.getVehiclePrice(Vehicle.MOPED);
            String M = "$" + MCost;
            mopedPrice.setText(M);
        }


}
