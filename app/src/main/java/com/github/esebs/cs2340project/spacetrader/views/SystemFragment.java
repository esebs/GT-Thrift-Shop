package com.github.esebs.cs2340project.spacetrader.views;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.esebs.cs2340project.spacetrader.R;
import com.github.esebs.cs2340project.spacetrader.entities.Player;
import com.github.esebs.cs2340project.spacetrader.entities.Room;
import com.github.esebs.cs2340project.spacetrader.viewmodels.PlayerViewModel;

/**
 * This class will generate and display the system fragment
 * when the 'System' tab is pressed
 *
 * @version 1.0
 * @author Elio Gerges
 */
public class SystemFragment extends Fragment {
    private final PlayerViewModel playerViewModel = new PlayerViewModel();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        Room room = player.getCurrent();

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

        buildingName.setText(getString(R.string.current_building, room.getBuilding().getName()));
        roomName.setText(getString(R.string.current_room, room.getName()));
        size.setText(room.getSize().name());
        techLevel.setText(room.getTechLevel().name());
        government.setText(room.getGovernment().name());
        resources.setText(room.getResource().name());
        police.setText(room.getPolicePresence().name());
        pirates.setText("---");
        cargoSpace.setText(getString(R.string.current_space, player.getVehicle().getCargoSize() - player.getVehicle().calculateRemainingCargoSpace(), player.getVehicle().getCargoSize()));
        credits.setText(getString(R.string.current_credits, player.getCredits()));

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
