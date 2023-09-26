package org.candyLordModel.implimentations.myCandyLordModel;

import org.candyLordModel.implimentations.exceptions.NotEnoughCapacityException;
import org.candyLordModel.implimentations.exceptions.NotEnoughMoneyException;
import org.candyLordModel.implimentations.settings.Candies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.candyLordModel.implimentations.myCandyLordModel.MyCandyLordModelUtil.CHARACTER_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyCandyLordModelBuyTest {

    MyCandyLordModel testModel;

    @BeforeEach
    void setUp() {
        testModel = new MyCandyLordModel(CHARACTER_NAME);
    }

    @Test
    void playerShouldBeAbleToBuyCandy() {
        //Given the Character has enough cash to buy a Candy
        int cash = 1000;
        testModel.setCash(cash);
        testModel.setCandyPriceAtCurrendLocation(Candies.BUBBA_CHUPS, cash);

        //When he buys the Candy
        int quantity = 1;
        testModel.buyCandy(Candies.BUBBA_CHUPS.name(), quantity);

        //Then he should have the Candy
        assertEquals(quantity, testModel.getNumberOfCandyInPossesion(Candies.BUBBA_CHUPS.name()));
    }

    @Test
    void playerLoosesCashIfHeBuysCandies(){
        //Given the Character has enough cash to buy a Candy
        int cash = 1000;
        testModel.setCash(cash);
        testModel.setCandyPriceAtCurrendLocation(Candies.BUBBA_CHUPS, cash);

        //When he buys the Candy
        int quantity = 1;
        testModel.buyCandy(Candies.BUBBA_CHUPS.name(), quantity);

        //His cash should be reduced
        assertEquals(0, testModel.getCash());
    }

    @Test
    void playerCantBuyCandyIfHeHastoLessMoney(){
        //Given the Character does not have enough cash to buy a Candy
        int cash = 0;
        Candies candie  = Candies.BUBBA_CHUPS;
        testModel.setCash(cash);
        testModel.setCandyPriceAtCurrendLocation(candie, 100);

        //When he buys the Candy;
        //Then he should get an NotEnoughMoneyException
        assertThrows(NotEnoughMoneyException.class, () -> testModel.buyCandy(candie.name() ,1));
    }

    @Test
    void playerCantBuyCandyIfHeHasNotEnoughCapacity(){
        //Given the Character does have enough cash to buy a Candy
            //And his inventory is full
        int capacity = 10;
        testModel.setCash(1000);
        testModel.setCandyPriceAtCurrendLocation(Candies.BUBBA_CHUPS, 1000);
        testModel.setCandyAmountInInventory(Candies.SURPRISE_OVAL, capacity);
        testModel.setCapacity(capacity);

        //When he buys a Candy
        //Then he should get an Error Message
        assertThrows(NotEnoughCapacityException.class, () -> testModel.buyCandy(Candies.BUBBA_CHUPS.name(), 1));
    }
}
