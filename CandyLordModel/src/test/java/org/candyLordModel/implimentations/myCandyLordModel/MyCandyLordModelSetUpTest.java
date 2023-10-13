package org.candyLordModel.implimentations.myCandyLordModel;
import org.candyLordModel.implimentations.settings.Candies;
import org.candyLordModel.implimentations.settings.DefaultSettings;
import org.candyLordModel.implimentations.settings.Locations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EnumSet;

import static org.candyLordModel.implimentations.myCandyLordModel.MyCandyLordModelUtil.CHARACTER_NAME;
import static org.junit.jupiter.api.Assertions.*;

class MyCandyLordModelSetUpTest {

    MyCandyLordModel testModel;

    @BeforeEach
    public void setUp() {
        testModel = MyCandyLordModel.getInstance(CHARACTER_NAME);
    }

    @Test
    void shouldBePossibleToSavePlayer() {
        //Given a Model has been created

        //Than the name should be saved
        assertEquals(CHARACTER_NAME, testModel.getCharacterName());
    }

    @Test
    void timerShouldBeZeroAfterSetup() {
        //Given a Model has been created

        //Then the timer should be zero
        assertEquals(0, testModel.getTimer());
    }

    @Test
    void playerShouldStartWithCashEqualToTheAmountSetInTheSettings() {
        //Given a Model has been created

        //When I get the Cash
        long cash = testModel.getCash();

        //Then the player should have cash equal to the Amount in the Settings
        assertEquals(DefaultSettings.START_CASH, testModel.getCash());
    }

    @Test
    void playerShouldHaveHealthEqualToTheAmountInTheSettingsAfterSetUp() {
        //Given a Model has been created

        //Then the player should have 100 health
        assertEquals(DefaultSettings.STARTING_HEALTH, testModel.getHealth());
    }

    @Test
    void playerShouldBeAtAStartLocationAfterSetUp() {
        //Given a Model has been created

        //Then the player should have a current location
        assertNotNull(testModel.getPlayerLocationName());
    }

    @Test
    void thereShouldBeACollectionOfCandiesAfterSetUp(){
        //Given a Model has been created

        //When I get all Candies
        ArrayList<String> candies = testModel.getAllCandieNames();

        //Then there should be all Candies from the Settings
        assertArrayEquals(Candies.getAllCandyNames().toArray(), candies.toArray());
    }

    @Test
    void thereShouldBeACollectionOfLocationsAfterSetUp(){
        //Given a Model has been created

        //When I gat all Locations
        ArrayList<String> locations = (ArrayList<String>) testModel.getAllLocationNames();

        //Then there should be all locations from the settings
        assertArrayEquals(Locations.getAllLocationNames().toArray(), locations.toArray());
    }

    @Test
    void theCharacterShouldHaveACapacityOf10ItemsAfterSetUp(){
        //Given a Model has been created

        //When I ask for the capacity of the character
        int capacity = testModel.getCapacity();

        //Then the Amount should be 10
        assertEquals(10, capacity);
    }

    @Test
    void shouldHaveAPriceForEachCandyPerLocation(){
        //Given a Model has been created

        //When I ask for the Price of a Candy at the Currend Location
        //Then the Price should be not 0
        EnumSet.allOf(Locations.class).forEach((location) ->
                EnumSet.allOf(Candies.class).forEach((candy) ->
                    assertNotEquals(0, location.getCandyPrice(candy), candy.toString() + " in " + location + " has not set it's Price!")));
    }

    @Test
    void characterShouldHaveStatuspointsEqualToTheSettings(){
        //Given a Model has been created

        //When I ask for the Status Points
        int statusPoints = testModel.getStatusPoints();

        //Then they should be equal to the Setting Default
        assertEquals(DefaultSettings.START_STATUS_POINTS, statusPoints);
    }

}