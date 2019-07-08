package com.github.esebs.cs2340project.spacetrader.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.github.esebs.cs2340project.spacetrader.R;
import com.github.esebs.cs2340project.spacetrader.entities.Building;
import com.github.esebs.cs2340project.spacetrader.entities.Room;
import com.github.esebs.cs2340project.spacetrader.model.Model;
import com.github.esebs.cs2340project.spacetrader.viewmodels.PlayerViewModel;
import com.github.esebs.cs2340project.spacetrader.viewmodels.TravelViewModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

public class TravelFragment extends Fragment implements OnMapReadyCallback, OnMarkerClickListener {

    private GoogleMap googleMap;
    private SupportMapFragment mapView;
    private View view;
    private TravelViewModel viewModel;

    private Model model = Model.getModelInstance();
    private PlayerViewModel playerViewModel = new PlayerViewModel();
    private String selectedRoomName;
    private Room selectedRoom;


    /**
     * Set up when activity starts
     *
     * @param savedInstanceState current state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * Sets up the view when the activity starts
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


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
        view = inflater.inflate(R.layout.travel_fragment, container, false);
        viewModel = new TravelViewModel();

        mapView = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (mapView == null) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapView = new SupportMapFragment();
            ft.replace(R.id.map, mapView).commit();
        }
        mapView.getMapAsync(this);
        return view;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        List<Building> buildings = model.getBuildings();

        for (Building b : buildings) {
            LatLng currentBuilding = new LatLng(b.getLatitude(), b.getLongitude());
            Marker m;

            if (b.equals(playerViewModel.getPlayer().getCurrent().getBuilding())) {
                m = googleMap.addMarker(new MarkerOptions().position(currentBuilding).title(b.getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                m.setTag(0);
            } else {
                m = googleMap.addMarker(new MarkerOptions().position(currentBuilding).title(b.getName()));
                m.setTag(b);
            }


            googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentBuilding));
        }

        //Set map zoom
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));

        //Set a listener for marker click.
        googleMap.setOnMarkerClickListener(this);
    }


    /**
     * Called when the user clicks a marker.
     *
     * @param marker is the marker pressed on the map
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {
        if (marker.getTag().equals(0)) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());

            alertDialog.setTitle("This is your current location!")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .create()
                    .show();
        } else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            final Building building = (Building) marker.getTag();
            final List<Room> rooms = building.getRooms();
            final List<String> roomNamesList = new ArrayList<>();

            for (Room r : rooms) {
                roomNamesList.add(r.getName());
            }

            final String[] roomNamesArray = roomNamesList.toArray(new String[roomNamesList.size()]);

            alertDialog.setTitle("Select a room in the " + marker.getTitle() + ".")
                    //Display the radio buttons to select a room
                    .setSingleChoiceItems(roomNamesArray, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            selectedRoomName = roomNamesArray[i];
                        }
                    })
                    .setPositiveButton("Travel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //set the new location of the player

                            //select the room object according to the name of the radio option selected
                            if (selectedRoomName == null || selectedRoomName.equals(rooms.get(0).getName())) {
                                selectedRoom = rooms.get(0);
                            } else if (selectedRoomName.equals(rooms.get(1).getName())) {
                                selectedRoom = rooms.get(1);
                            }

                            if (viewModel.getCurrentRange() < 5) {
                                Toast.makeText(getActivity(),
                                        "Not enough fuel left to Travel", Toast.LENGTH_LONG).show();
                            } else {
                                viewModel.travelTo(selectedRoom);
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                            }

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .create()
                    .show();

        }
        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
}

