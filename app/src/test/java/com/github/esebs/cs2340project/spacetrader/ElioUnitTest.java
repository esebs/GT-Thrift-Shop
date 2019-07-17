package com.github.esebs.cs2340project.spacetrader;

import com.github.esebs.cs2340project.spacetrader.entities.Building;
import com.github.esebs.cs2340project.spacetrader.entities.Difficulty;
import com.github.esebs.cs2340project.spacetrader.entities.Player;
import com.github.esebs.cs2340project.spacetrader.entities.Vehicle;
import com.github.esebs.cs2340project.spacetrader.model.Model;
import com.github.esebs.cs2340project.spacetrader.viewmodels.VehicleViewModel;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * The purpose of this class is to test getVehiclePrice
 * and canBuyVehicle for each vehicle in the
 * VehicleViewModel class.
 *
 * @author Elio Gerges
 */
public class ElioUnitTest {
    private Model model;
    private VehicleViewModel vehicleViewModel;
    private Player player;

    @Before
    public void setUp() {
        Building building = new Building("Building", 1.0, 1.0, Arrays.asList("Room1", "Room2"));
        player = new Player("Player", Difficulty.BABY, building.getRooms().get(0), 1, 1, 1, 17);
        model = Model.getModelInstance();
        model.setPlayer(player);
        vehicleViewModel = new VehicleViewModel();
    }


    /**
     * Test for getting the price of the UNICYCLE given
     * This accounts for the current vehicle
     *
     */
    @Test
    public void testGetVehicleUnicyclePrice() {
        int expectedCost = Vehicle.UNICYCLE.getPrice() - player.getVehicle().getPrice();
        assertEquals(expectedCost, vehicleViewModel.getVehiclePrice(Vehicle.UNICYCLE));
    }

    /**
     * Test to check if the player can by a UNICYCLE
     * given that they player has 500000 cr.
     * and current vehicle
     *
     * NOTE: The player can always afford the cheapest
     * vehicle.
     *
     * Expected: true
     */
    @Test
    public void testCanBuyVehicleUnicycleTrue() {
        model.getPlayer().setCredits(500000);
        assertTrue(vehicleViewModel.canBuyVehicle(Vehicle.UNICYCLE));
    }

    /**
     * Test for getting the price of the BIKE given
     * This accounts for the current vehicle
     *
     */
    @Test
    public void testGetVehicleBikePrice() {
        int expectedCost = Vehicle.BIKE.getPrice() - player.getVehicle().getPrice();
        assertEquals(expectedCost, vehicleViewModel.getVehiclePrice(Vehicle.BIKE));
    }

    /**
     * Test to check if the player can by a BIKE
     * given that they player has 500000 cr.
     * and current vehicle
     *
     * Expected: true
     */
    @Test
    public void testCanBuyVehicleBikeTrue() {
        model.getPlayer().setCredits(500000);
        assertTrue(vehicleViewModel.canBuyVehicle(Vehicle.BIKE));
    }

    /**
     * Test to check if the player can by a BIKE
     * given that they player has 10000 cr.
     * and current vehicle
     *
     * Expected: false
     */
    @Test
    public void testCanBuyVehicleBikeFalse() {
        assertFalse(vehicleViewModel.canBuyVehicle(Vehicle.BIKE));
    }

    /**
     * Test for getting the price of the GOLF_CART given
     * This accounts for the current vehicle
     *
     */
    @Test
    public void testGetVehicleGolfCartPrice() {
        int expectedCost = Vehicle.GOLF_CART.getPrice() - player.getVehicle().getPrice();
        assertEquals(expectedCost, vehicleViewModel.getVehiclePrice(Vehicle.GOLF_CART));
    }

    /**
     * Test to check if the player can by a GOLF_CART
     * given that they player has 500000 cr.
     * and current vehicle
     *
     * Expected: true
     */
    @Test
    public void testCanBuyVehicleGolfCartTrue() {
        model.getPlayer().setCredits(500000);
        assertTrue(vehicleViewModel.canBuyVehicle(Vehicle.GOLF_CART));
    }

    /**
     * Test to check if the player can by a GOLF_CART
     * given that they player has 10000 cr.
     * and current vehicle
     *
     * Expected: false
     */
    @Test
    public void testCanBuyVehicleGolfCartFalse() {
        assertFalse(vehicleViewModel.canBuyVehicle(Vehicle.GOLF_CART));
    }

    /**
     * Test for getting the price of the MOPED given
     * This accounts for the current vehicle
     *
     */
    @Test
    public void testGetVehicleMopedPrice() {
        int expectedCost = Vehicle.MOPED.getPrice() - player.getVehicle().getPrice();
        assertEquals(expectedCost, vehicleViewModel.getVehiclePrice(Vehicle.MOPED));
    }

    /**
     * Test to check if the player can by a MOPED
     * given that they player has 500000 cr.
     * and current vehicle
     *
     * Expected: true
     */
    @Test
    public void testCanBuyVehicleMopedTrue() {
        model.getPlayer().setCredits(500000);
        assertTrue(vehicleViewModel.canBuyVehicle(Vehicle.MOPED));
    }

    /**
     * Test to check if the player can by a MOPED
     * given that they player has 10000 cr.
     * and current vehicle
     *
     * Expected: false
     */
    @Test
    public void testCanBuyVehicleMopedFalse() {
        assertFalse(vehicleViewModel.canBuyVehicle(Vehicle.MOPED));
    }
}
