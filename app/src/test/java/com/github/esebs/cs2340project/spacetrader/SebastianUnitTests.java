package com.github.esebs.cs2340project.spacetrader;

import com.github.esebs.cs2340project.spacetrader.entities.Building;
import com.github.esebs.cs2340project.spacetrader.entities.Difficulty;
import com.github.esebs.cs2340project.spacetrader.entities.Player;
import com.github.esebs.cs2340project.spacetrader.entities.Room;
import com.github.esebs.cs2340project.spacetrader.entities.Vehicle;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class SebastianUnitTests {
    /**
     * JUNIT Test 1
     *
     * @author Sebastian Escobar
     */
    @Test
    public void player_toString() {
        Building building = new Building("building",
                1.0, -1.0, Arrays.asList("Room", "Room2"));

        String testing = "Player {" +
                "\n\tname: '" + "Test" + '\'' +
                "\n\tdifficulty:" + Difficulty.MEDIUM +
                "\n\tvehicle: " + Vehicle.SCOOTER.getVehicleType() +
                "\n\tRoom: " + "Room2" +
                "\n\tpilotPoints: " + 1 +
                "\n\tfighterPoints: " + 1 +
                "\n\ttraderPoints: " + 1 +
                "\n\tengineerPoints: " + 1 +
                "\n\tcredits: " + 1000 +
                "\n }";

        Player p1 = new Player("Test", Difficulty.MEDIUM, building.getRooms().get(1), 1, 1,
                1, 1 );

        assertEquals(p1.toString(), testing);
    }

    /**
     * JUNIT Test 2
     *
     * @author Sebastian Escobar
     */
    @Test
    public void building_toString() {
        Building building = new Building("building",
                1, -1, Arrays.asList("Room", "Room1", "Room3"));

        StringBuilder temp1 = new StringBuilder();
        for (Room room : building.getRooms()) {
            temp1.append("\n\t");
            temp1.append(room.toString());
        }
        String temp = "\nBuilding{"
                + "name='" + "building"
                + '\'' + ", latitude="
                + 1.0 + ", longitude="
                + -1.0 + ", rooms=" + temp1
                + "}\n";

        assertEquals(temp, building.toString());

    }
}

