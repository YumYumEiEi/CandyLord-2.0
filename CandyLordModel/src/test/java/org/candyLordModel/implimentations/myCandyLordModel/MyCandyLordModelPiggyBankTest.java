package org.candyLordModel.implimentations.myCandyLordModel;

import org.candyLordModel.implimentations.exceptions.NotEnoughMoneyException;
import org.candyLordModel.implimentations.exceptions.NotEnoughtMoneyInPiggyBankException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.candyLordModel.implimentations.myCandyLordModel.MyCandyLordModelUtil.CHARACTER_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyCandyLordModelPiggyBankTest {

    MyCandyLordModel testModel;

    @BeforeEach
    void setUp() {
        testModel = new MyCandyLordModel(CHARACTER_NAME);
    }

    @Test
    void playerShouldBeAbleToPutCashIntoThePiggyBank() {
        //Given a player has cash
        int cash = 500;
        testModel.setCash(cash);

        //When the player puts his cash into the piggy bank
        testModel.putCashInPiggyBank(cash);

        //Then the cash should be in the piggy bank
        assertEquals(500, testModel.getPiggyBankContent());
    }

    @Test
    void playerShouldBeAbleToRetrieveMoneyFromThePiggyBank(){
        //Given a player has money in the piggy bank
        long cash = 500;
        testModel.setPiggyBankAmount(cash);

        //When he retrieves it
        testModel.retrieveMoneyFromPiggyBank(cash);

        //The money should be out of the piggy bank
        assertEquals(0, testModel.getPiggyBankContent());
    }

    @Test
    void ifPlayerPutsMoneyInThePiggyBankHeLosesThatCash(){
        //Given a player has cash
        int cash = 500;
        testModel.setCash(cash);

        //When the player puts his cash into the piggy bank
        testModel.putCashInPiggyBank(cash);

        //Then player should loos the cash
        assertEquals(0, testModel.getCash());
    }

    @Test
    void ifPlayerRetrievesMoneyFromPiggyBankHeGainsCash(){
        //Given a player has money in the piggy bank
        long cash = 500;
        testModel.setCash(0);
        testModel.setPiggyBankAmount(cash);

        //When he retrieves it
        testModel.retrieveMoneyFromPiggyBank(cash);

        //The player should receive the cash
        assertEquals(cash, testModel.getCash());
    }

    @Test
    void playerShouldNotBeAbleToPutMoreMoneyIntoPiggyBankThanHeOwns(){
        //Given a player has less money than he wants to put into the piggy bank
        long offer = 500;
        testModel.setCash(0);

        //When he wants to put the money into the piggy bank
        //Then he should get an error message
        assertThrows(NotEnoughMoneyException.class, () -> testModel.putCashInPiggyBank(offer));
    }

    @Test
    void playerShouldNotBeAbleToRetrieveMoreMoneyFromPiggyBankThanItContains(){
        //Given a player wants to retrieve more money than the piggy bank contains
        //When he retrieves the money
        //Than he should get an error message
        assertThrows(NotEnoughtMoneyInPiggyBankException.class, () -> testModel.retrieveMoneyFromPiggyBank(500));
    }
}
