package com.github.esebs.cs2340project.spacetrader.views;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.esebs.cs2340project.spacetrader.R;

public class VehicleFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.vehicle_fragment, container, false);
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
