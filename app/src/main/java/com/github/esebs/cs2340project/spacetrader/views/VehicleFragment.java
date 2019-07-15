package com.github.esebs.cs2340project.spacetrader.views;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.esebs.cs2340project.spacetrader.R;
import com.github.esebs.cs2340project.spacetrader.viewmodels.TravelViewModel;

/**
 * This class will generate and display the vehicle fragment
 * when the 'Vehicle' tab is pressed
 *
 * @version 1.0
 * @author Elio Gerges
 */
public class VehicleFragment extends Fragment {

    private TextView fuelInfo;
    private TextView fuelCost;
    private Button refuel;
    private TravelViewModel viewModel;


    /**
     * OnCreateView is called when the user switches to the 'Vehicle' tab.
     * The user has the ability to refuel their vehicle if the
     * vehicle is not already full.
     *
     * @param inflater the LayoutInflater
     * @param container the container holding all View objects
     * @param savedInstanceState previous saved instance
     * @return the view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.vehicle_fragment, container, false);
        viewModel = new TravelViewModel();
        fuelInfo = view.findViewById(R.id.fuel_info);
        fuelCost = view.findViewById(R.id.fuel_cost);
        refuel = view.findViewById(R.id.refuel_button);

        fuelInfo.setText(getString(R.string.tank_range, viewModel.getCurrentRange()));
        if (viewModel.isRangeMax()) {
            fuelCost.setText(getString(R.string.full_tank));
            refuel.setEnabled(false);
        } else {
            fuelCost.setText(getString(R.string.empty_tank, viewModel.getCostToRefuel()));
        }

        refuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewModel.getCostToRefuel() > viewModel.getCredits()) {
                    Toast.makeText(getActivity(), "Need more credits to refuel!", Toast.LENGTH_LONG).show();
                } else {
                    viewModel.refillRange();
                    fuelInfo.setText(getString(R.string.tank_range, viewModel.getCurrentRange()));
                    fuelCost.setText(getString(R.string.full_tank));
                    refuel.setEnabled(false);
                }
            }
        });


        return view;
    }


    /**
     * This method will refresh the tabs each time the users revisits a tab
     *
     * @param isVisibleToUser is a boolean representing if a fragment is visible to the user
     */
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && getFragmentManager() != null) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

}
