package org.candyLordModel.implimentations.myCandyLordModel;

import org.candyLordModel.implimentations.exceptions.AlreadyAtTheChosenLocation;
import org.candyLordModel.implimentations.exceptions.NoSuchLocationFound;
import org.candyLordModel.implimentations.exceptions.NotEnoughMoneyException;
import org.candyLordModel.implimentations.settings.Locations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.candyLordModel.implimentations.myCandyLordModel.MyCandyLordModelUtil.CHARACTER_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyCandyLordModelChangeLocationTest {

    MyCandyLordModel testModel;

    @BeforeEach
    void setUp(){
        testModel = new MyCandyLordModel(CHARACTER_NAME);
        //Start location is random, so it has to be set here for testability!
        testModel.setCurrentLocation(Locations.LINCOL_SCHOOL_OF_FINE_ARTS);
    }

    @Test
    void playerShouldBeAbleToChangeLocation(){
        //Given the player has enough cash to change location
        long cash = 100;
        testModel.setCash(100);
        Locations location = Locations.EASTWOOD_COLLEGE;
        testModel.setTravelCostToLocationFromCurrendLocation(location, cash);

        //When he travels
        testModel.travelTo(location.name());

        //Then he should be at a new Location
        assertEquals(location.toString(), testModel.getPlayerLocationName());

    }

    @Test
    void ifPlayerChangesLocationHeShouldLoseCash(){
        //Given the player has enough cash to change location
        long cash = 100;
        testModel.setCash(100);
        Locations location = Locations.EASTWOOD_COLLEGE;
        testModel.setTravelCostToLocationFromCurrendLocation(location, cash);

        //When he travels
        testModel.travelTo(location.name());

        //Then he should lose some cash
        assertEquals(0, testModel.getCash());
    }

    @Test
    void ifThePlayerDoesntHaveEnoughMonyToTravelHeShouldGetAnErrorMessage(){
        //Given the player does not have enough cash to change location
        long cost = 100;
        testModel.setCash(0);
        Locations location = Locations.EASTWOOD_COLLEGE;
        testModel.setTravelCostToLocationFromCurrendLocation(location, cost);

        //When he travels
        //Then he should get an Error Message
        assertThrows(NotEnoughMoneyException.class, () -> testModel.travelTo(location.name()));
    }

    @Test
    void shouldShowAnErrorMessageIfThePlayerTriesToChangeLocationToCurrendLocation(){
        //Given the player has enough money to change location
        long cost = 100;
        testModel.setCash(100);
        Locations location = Locations.EASTWOOD_COLLEGE;
        testModel.setTravelCostToLocationFromCurrendLocation(location, cost);
            //And the player is currently at the chosen location
        testModel.setCurrentLocation(location);

        //When he tries to travel to the location
        //Then he should get an error message
        assertThrows(AlreadyAtTheChosenLocation.class, () -> testModel.travelTo(location.name()));
    }

    @Test
    void shouldShowAnErrorMessageIfPlayerTrysToTravelToALocationNotKnown(){
        //Given the chosen location doesn't exist
        //When he tries to travel to the location
        //He should get an error message
        assertThrows(NoSuchLocationFound.class, () -> testModel.travelTo("Non Existing Location"));
    }
}
