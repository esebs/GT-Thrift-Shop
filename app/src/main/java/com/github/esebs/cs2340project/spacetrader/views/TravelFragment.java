package com.github.esebs.cs2340project.spacetrader.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TravelFragment extends Fragment {
    /**
     * OnCreateView is called when the user switches to the 'Travel' tab.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return the view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
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
