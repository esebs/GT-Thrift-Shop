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

public class VehicleFragment extends Fragment {

    private TextView fuelInfo;
    private TextView fuelCost;
    private Button refuel;
    private TravelViewModel viewModel;


    /**
     * OnCreateView is called when the user switches to the 'Vehicle' tab.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
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

        fuelInfo.setText("You have enough range to travel " + viewModel.getCurrentRange() + " meters.");
        if (viewModel.isRangeMax()) {
            fuelCost.setText("You have a full tank!");
            refuel.setEnabled(false);
        } else {
            fuelCost.setText("A full tank cost " + viewModel.getCostToRefuel() + " cr.");
        }

        refuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewModel.getCostToRefuel() > viewModel.getCredits()) {
                    Toast.makeText(getActivity(), "Need more credits to refuel!", Toast.LENGTH_LONG).show();
                } else {
                    viewModel.refillRange();
                    fuelInfo.setText("You have enough range to travel " + viewModel.getCurrentRange() + " meters.");
                    fuelCost.setText("You have a full tank!");
                    refuel.setEnabled(false);
                }
            }
        });


        return view;
    }

    /**
     * This method overrides setUserVisibleHint in Fragment.
     * It will update the tabs next to the current tab
     * as soon as the user switches to a new tab.
     *
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
}
