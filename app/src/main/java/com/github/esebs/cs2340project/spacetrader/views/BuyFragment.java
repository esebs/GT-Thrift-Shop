package com.github.esebs.cs2340project.spacetrader.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.github.esebs.cs2340project.spacetrader.R;

public class BuyFragment extends Fragment {
    private Button waterButton;
    private Button fursButton;
    private Button foodButton;
    private Button oreButton;
    private Button gamesButton;
    private Button firearmsButton;
    private Button medicineButton;
    private Button machinesButton;
    private Button narcoticsButton;
    private Button robotsButton;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buy_fragment, container,false);

        waterButton = view.findViewById(R.id.water_qty);
        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("BUTTON", "Pressed");
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setMessage("You can by up top 15 bayes of Water. \n How many would you like to buy?")
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                alertDialog.show();
            }
       });

        final View dialog = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null );

        fursButton = view.findViewById(R.id.furs_qty);
        fursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("BUTTON", "Pressed");
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setView(dialog)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }

                           })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog temp = alertDialog.create();
                temp.show();
            }
        });
        return view;
    }
}
