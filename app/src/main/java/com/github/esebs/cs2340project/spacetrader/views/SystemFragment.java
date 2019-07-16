package com.github.esebs.cs2340project.spacetrader.views;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.esebs.cs2340project.spacetrader.R;
import com.github.esebs.cs2340project.spacetrader.entities.Government;
import com.github.esebs.cs2340project.spacetrader.entities.Player;
import com.github.esebs.cs2340project.spacetrader.entities.PolicePresence;
import com.github.esebs.cs2340project.spacetrader.entities.ResourceLevel;
import com.github.esebs.cs2340project.spacetrader.entities.Room;
import com.github.esebs.cs2340project.spacetrader.entities.Size;
import com.github.esebs.cs2340project.spacetrader.entities.TechLevel;
import com.github.esebs.cs2340project.spacetrader.entities.Vehicle;
import com.github.esebs.cs2340project.spacetrader.viewmodels.PlayerViewModel;

/**
 * This class will generate and display the system fragment
 * when the 'System' tab is pressed
 *
 * @version 1.0
 * @author Elio Gerges
 */
class SystemFragment extends Fragment {
    private final PlayerViewModel playerViewModel = new PlayerViewModel();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.system_fragment, container, false);
        Player player  = playerViewModel.getPlayer();
        TextView buildingName;
        TextView roomName;
        TextView size;
        TextView techLevel;
        TextView government;
        TextView resources;
        TextView police;
        TextView pirates;
        TextView cargoSpace;
        TextView credits;
        Room room = playerViewModel.getPlayerCurrentRoom();

        buildingName = view.findViewById(R.id.current_building);
        roomName = view.findViewById(R.id.current_room);
        size = view.findViewById(R.id.system_size);
        techLevel = view.findViewById(R.id.system_tech_level);
        government = view.findViewById(R.id.system_government);
        resources = view.findViewById(R.id.system_resource);
        police = view.findViewById(R.id.system_police);
        pirates = view.findViewById(R.id.system_pirate);
        cargoSpace = view.findViewById(R.id.cargo_bays);
        credits = view.findViewById(R.id.player_cr);

        buildingName.setText(getString(R.string.current_building, room.getName()));
        roomName.setText(getString(R.string.current_room, room.getName()));

        Size roomSize = room.getSize();
        size.setText(roomSize.name());

        TechLevel roomTechLevel = room.getTechLevel();
        techLevel.setText(roomTechLevel.name());

        Government roomGov = room.getGovernment();
        government.setText(roomGov.name());

        ResourceLevel roomResource = room.getResource();
        resources.setText(roomResource.name());

        PolicePresence roomPolice = room.getPolicePresence();
        police.setText(roomPolice.name());

        pirates.setText("---");
        Vehicle vehicle = player.getVehicle();
        cargoSpace.setText(getString(R.string.current_space,
                vehicle.getCargoSize()
                        - vehicle.calculateRemainingCargoSpace(),
                vehicle.getCargoSize()));
        credits.setText(getString(R.string.current_credits, player.getCredits()));

        return view;
    }

    /**
     * This method will refresh the tabs each time the users revisits a tab
     *
     * @param isVisibleToUser is a boolean representing if a fragment is visible to the user
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && (getFragmentManager() != null)) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.detach(this);
            transaction.attach(this);
            transaction.commit();
        }
    }

}
