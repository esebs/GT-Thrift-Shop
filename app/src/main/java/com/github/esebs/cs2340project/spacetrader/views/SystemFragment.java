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

public class SystemFragment extends Fragment {
    private PlayerViewModel playerViewModel = new PlayerViewModel();

    private TextView name;
    private TextView size;
    private TextView techLevel;
    private TextView government;
    private TextView resources;
    private TextView police;
    private TextView pirates;
    private TextView cargoSpace;
    private TextView credits;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.system_fragment, container, false);
        Player player  = playerViewModel.getPlayer();
        Room room = player.getCurrent();

        name = view.findViewById(R.id.system_current);
        size = view.findViewById(R.id.system_size);
        techLevel = view.findViewById(R.id.system_tech_level);
        government = view.findViewById(R.id.system_government);
        resources = view.findViewById(R.id.system_resource);
        police = view.findViewById(R.id.system_police);
        pirates = view.findViewById(R.id.system_pirate);
        cargoSpace = view.findViewById(R.id.cargo_bays);
        credits = view.findViewById(R.id.player_cr);


        name.setText("Room: " +room.getName());
        size.setText(room.getSize().name());
        techLevel.setText(room.getTechLevel().name());
        government.setText(room.getGovernment().name());
        resources.setText(room.getResource().name());
        police.setText(room.getPolicePresence().name());
        pirates.setText("---");
        cargoSpace.setText("Cargo Space: " + (player.getVehicle().getCargoSize() -player.getVehicle().calculateRemainingCargoSpace()) + "/" + player.getVehicle().getCargoSize());
        credits.setText(player.getCredits() + " cr.");



        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

}
