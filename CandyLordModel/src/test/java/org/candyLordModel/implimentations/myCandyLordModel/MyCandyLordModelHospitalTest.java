package org.candyLordModel.implimentations.myCandyLordModel;

import org.candyLordModel.implimentations.exceptions.NotEnoughMoneyException;
import org.candyLordModel.implimentations.settings.DefaultSettings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.candyLordModel.implimentations.myCandyLordModel.MyCandyLordModelUtil.CHARACTER_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyCandyLordModelHospitalTest {

    MyCandyLordModel testModel;

    @BeforeEach
    void setUp(){
        testModel = new MyCandyLordModel(CHARACTER_NAME);
    }

    @Test
    void playerShouldBeAbleToGoToTheHospitalAndHealTo100HP(){
        //Given the player took dmg
        testModel.setHealth(50);
        //When he goes to the Hospital
        testModel.goToHospital();
        //Then he should be healed to full HP
        assertEquals(100, testModel.getHealth());
    }

    @Test
    void playerHasToPayForHospitalVisit(){
        //Given the player lost live
            //And has enough money to pay the hospital
        testModel.setHealth(50);
        testModel.setCash(50* DefaultSettings.CENT_PER_HEALTH_COST);
        //When he goes to the hospital
        testModel.goToHospital();
        //Then he should lose money
        assertEquals(0, testModel.getCash());
    }

    @Test
    void ifPlayerDoesNotHaveEnoughMoneyToVisitTheHospitalHeShouldGetAnError(){
        //Given the player has lost health
            //And he does not have enough Money to pay the cost
        testModel.setHealth(50);
        testModel.setCash(0);
        //When he goes to the hospital
        //Then he should get an error message
        assertThrows(NotEnoughMoneyException.class, () -> testModel.goToHospital());
    }

    @Test
    void playerHasToStayOneDayPer10HealthHeHeals(){
        //Given the player lost health
            //And does have the money to pay for the treatment
        testModel.setHealth(50);
        testModel.setCash(50);
        //When the player visits the hospital
        testModel.goToHospital();
        //Then Days should be passed
        assertEquals(5, testModel.getPassedDays());
    }
}
