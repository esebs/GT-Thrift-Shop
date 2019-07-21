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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.github.esebs.cs2340project.spacetrader.R;
import com.github.esebs.cs2340project.spacetrader.entities.Building;
import com.github.esebs.cs2340project.spacetrader.entities.Room;
import com.github.esebs.cs2340project.spacetrader.model.Model;
import com.github.esebs.cs2340project.spacetrader.viewmodels.UniverseViewModel;
import com.github.esebs.cs2340project.spacetrader.viewmodels.PlayerViewModel;
import com.github.esebs.cs2340project.spacetrader.viewmodels.TravelViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will generate and display the travel fragment
 * when the 'Travel' tab is pressed
 *
 * @version 1.0
 * @author Elio Gerges
 */
class TravelFragment extends Fragment implements OnMapReadyCallback, OnMarkerClickListener {

    private TravelViewModel viewModel;
    private UniverseViewModel universeViewModel = new UniverseViewModel();
    private final Model model = Model.getModelInstance();
    private final PlayerViewModel playerViewModel = new PlayerViewModel();
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
     * @param view the current view
     * @param savedInstanceState previous saved instance
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    /**
     * OnCreateView is called when the user switches to the 'Vehicle' tab.
     *
     * @param inflater the LayoutInflater
     * @param container the container holding all View objects
     * @param savedInstanceState previous saved instance
     * @return the view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.travel_fragment, container, false);
        viewModel = new TravelViewModel();

        SupportMapFragment mapView =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if ((mapView == null) && (getFragmentManager() != null)) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapView = new SupportMapFragment();
            ft.replace(R.id.map, mapView).commit();
        }

        if (mapView != null) {
            mapView.getMapAsync(this);
        }

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
        if (getContext()!= null) {
            MapsInitializer.initialize(getContext());
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            List<Building> buildings = model.getBuildings();

            for (Building b : buildings) {
                LatLng currentBuilding = new LatLng(b.getLatitude(), b.getLongitude());
                Marker m;

                if (b.equals(playerViewModel.getPlayer().getCurrentBuilding())) {
                    m = googleMap.addMarker(new MarkerOptions().position(currentBuilding).title(
                            b.getName()).icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_BLUE)));
                    m.setTag(0);
                } else {
                    m = googleMap.addMarker(new MarkerOptions().position(currentBuilding).title(
                            b.getName()));
                    m.setTag(b);
                }


                googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentBuilding));
            }

            //Set map zoom
            final float zoomVal = 15.0f;
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(zoomVal));

            //Set a listener for marker click.
            googleMap.setOnMarkerClickListener(this);
        }
    }


    /**
     * Called when the user clicks a marker.
     *
     * @param marker is the marker pressed on the map
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {
        if ((marker.getTag() != null) && (marker.getTag().equals(0))) {
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

            final String[] roomNamesArray = roomNamesList.toArray(new String[2]);

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

                            //select the room object according to the name of the radio option
                            // selected
                            if ((selectedRoomName == null) || (selectedRoomName.equals(
                                    rooms.get(0).getName()))) {
                                selectedRoom = rooms.get(0);
                            } else if (selectedRoomName.equals(rooms.get(1).getName())) {
                                selectedRoom = rooms.get(1);
                            }

                            if (viewModel.getCurrentRange() < 5) {
                                Toast.makeText(getActivity(),
                                        "Not enough fuel left to Travel", Toast.LENGTH_LONG).show();
                            } else {
                                viewModel.travelTo(selectedRoom, building);
                                Gson gson = new Gson();

                                File path = getActivity().getFilesDir();
                                String json = gson.toJson(playerViewModel.getPlayer());
                                File file = new File(path, "Player.json");
                                try {
                                    FileOutputStream stream = new FileOutputStream(file);
                                    stream.write(json.getBytes());
                                    stream.close();
                                    Log.d("Saved", "Player");
                                } catch (Exception e) {
                                    Log.d("Error", "Travel Fragment Saving Player");
                                    e.printStackTrace();
                                }

                                List<Building> buildings = universeViewModel.getUniverse();

                                String jsonBuildings = gson.toJson(buildings);
                                File fileBuildings = new File(path, "Buildings.json");

                                //write to file
                                try {
                                    FileOutputStream stream = new FileOutputStream(fileBuildings);
                                    stream.write(jsonBuildings.getBytes());
                                    stream.close();
                                    Log.d("Saved", "Buildings");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.d("Error", "Travel Fragment Saving Buildings");
                                }
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

