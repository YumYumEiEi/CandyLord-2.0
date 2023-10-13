package org.candyLordModel.implimentations.myCandyLordModel;

import org.candyLordModel.implimentations.exceptions.NotEnoughCandyException;
import org.candyLordModel.implimentations.settings.Candies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.candyLordModel.implimentations.myCandyLordModel.MyCandyLordModelUtil.CHARACTER_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyCandyLordModelSellTest {

    MyCandyLordModel testModel;

    @BeforeEach
    void setUp(){
        testModel = MyCandyLordModel.getTestModel();
    }

    @Test
    void ifPlayerSellsCandyHeLoosesTheCandy(){
        //Given the player has enough Candy to sell
        Candies candie = Candies.BUBBA_CHUPS;
        int amount = 1;
        testModel.setCandyAmountInInventory(candie, amount);

        //When the Player sells the candy
        testModel.sellCandy(candie.name(), amount);

        //Then his amount of candy should be reduced
        assertEquals(0, testModel.getNumberOfCandyInPossesion(candie.name()));
    }

    @Test
    void ifPlayerSellsCandyHisCashShouldBeIncreased(){
        //Given the player has enough candy to sell
        int amount = 1;
        Candies candy = Candies.BUBBA_CHUPS;
        testModel.setCash(0);
        testModel.setCandyPriceAtCurrendLocation(candy, 100);
        testModel.setCandyAmountInInventory(candy, amount);

        //When he sells candy
        testModel.sellCandy(candy.name(), amount);

        //Then he shouldGetMoney
        assertEquals(100, testModel.getCash());
    }

    @Test
    void playerShouldNotBeAbleToSellMoreCandyThanHeHas(){
        //Given the player does not have enough candy to sell
        Candies candy = Candies.BUBBA_CHUPS;
        testModel.setCandyAmountInInventory(candy, 0);

        //When he tries to sell the candy
        //Then he should get an Error-message
        assertThrows(NotEnoughCandyException.class, () -> testModel.sellCandy(candy.name(), 1));
    }


}
