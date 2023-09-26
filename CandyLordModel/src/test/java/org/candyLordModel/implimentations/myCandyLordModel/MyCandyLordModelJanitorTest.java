package org.candyLordModel.implimentations.myCandyLordModel;

import org.candyLordModel.implimentations.exceptions.*;
import org.candyLordModel.implimentations.settings.DefaultSettings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.candyLordModel.implimentations.myCandyLordModel.MyCandyLordModelUtil.CHARACTER_NAME;
import static org.junit.jupiter.api.Assertions.*;

class MyCandyLordModelJanitorTest {

    MyCandyLordModel testModel;

    @BeforeEach
    void setUp() {
        testModel = new MyCandyLordModel(CHARACTER_NAME);
        testModel.setUpNextEvent(() -> {});
    }

    @Test
    void playerShouldBeAbleToBorrowMoneyFromTheJanitor() {
        //Given nothing happened before
        testModel.setCash(0);
        long amount = 1000;

        //When the player borrows money from the janitor
        testModel.borrowCashFromJanitor(amount);

        //Then the player should have the cash
        assertEquals(amount, testModel.getCash());
    }

    @Test
    void playerShouldBeAbleToPayBackTheJanitor() {
        //Given the player has debt
        //And has enough money to pay it back
        long amount = 1000;
        testModel.setDebt(amount);
        testModel.setCash(amount);

        //When he pays him back
        testModel.payBackTeJanitor(amount);

        //Then the player should lose the cash
        assertEquals(0, testModel.getCash());
    }

    @Test
    void ifPlayerBorrowsMoneyHeShouldGetDebt() {
        //Given nothing happened before
        long amount = 1000;
        //When the player borrows money from the janitor
        testModel.borrowCashFromJanitor(amount);
        //Then he should get Debt equal to the Settings
        long expectedDebt = amount * DefaultSettings.INTREST_CHARGES / 100;
        assertEquals(expectedDebt, testModel.getDebt());

    }

    @Test
    void ifPlayerPaysBackHeShouldLooseDebt() {
        //Given the player has debt
        long amount = 1000;
        testModel.setDebt(amount);
        testModel.setCash(amount);
        //When he pays back the janitor
        testModel.payBackTeJanitor(amount);
        //Then he should lose debt
        assertEquals(0, testModel.getCash());
    }

    @Test
    void ifPlayerBorrowsMoneyHeShouldGetATimerToPayBackDebt() {
        //Given nothing happened before
        //When the player borrows money
        testModel.borrowCashFromJanitor(1000);
        //Then the timer should have been set
        assertNotEquals(0, testModel.getDebtTimer());
    }

    @Test
    void playerShouldBeAbleToBorrowAtLeast1000Cent() {
        //Given nothing happened before
        //When the player tries to borrow less than 1000 cent
        //Then he should get an error message
        assertThrows(ToLowBorrowAmountException.class, () -> testModel.borrowCashFromJanitor(400));
    }

    @Test
    void playerShouldNotBeAbleToPayBackMoreThanHeOwns() {
        //Given the player has debt
        testModel.setDebt(1000);
        testModel.setCash(0);

        //When he wants to pay back more than he possesses
        //Then he should receive an error message
        assertThrows(NotEnoughMoneyException.class, () -> testModel.payBackTeJanitor(1000));
    }

    @Test
    void playerShouldNotBeAbleToPayBackMoreThanHeHasDebt() {
        //Given the player hat debt
        //And he does have more Money than he has debt
        testModel.setDebt(1000);
        testModel.setCash(2000);

        //When he tries to pay back more than he has debt
        //Then he should receive an error message
        assertThrows(ToMuchPayBackException.class, () -> testModel.payBackTeJanitor(2000));
    }

    @Test
    void playerShouldNotBeAbleToBorrowMoreThanHisStatusAllowsHim() {
        //Given nothing happened before
        testModel.setStatusPoints(1);

        //When he tries to borrow more than 1000*StatusPoints -1
        //Then he should get an error message
        assertThrows(ToLessCredibilityException.class, () -> testModel.borrowCashFromJanitor(10000));
    }

    @Test
    void theDebtTimerShouldBeResetToZeroIfTeDebtIsPayedBackAndZero() {
        //Given the player has debt
        //And has enough money to pay it back
        long amount = 10000;
        testModel.setDebt(amount);
        testModel.setCash(amount);
        testModel.setDebtTimer(10);

        //When he pays back the debt
        testModel.payBackTeJanitor(amount);

        //Then the timer should reset to 0
        assertEquals(0, testModel.getDebtTimer());
    }

    @Test
    void theHigherTheBorrowedAmountTheLowerShouldBeTheTimerLessThan20Percent() {
        checkTimerBorrowCorrelation(0.2, 15);
    }

    @Test
    void theHigherTheBorrowedAmountTheLowerShouldBeTheTimerLessThan40Percent() {
        //Given nothing happened before
        checkTimerBorrowCorrelation(0.4, 12);
    }

    @Test
    void theHigherTheBorrowedAmountTheLowerShouldBeTheTimerLessThan60Percent() {
        //Given nothing happened before
        checkTimerBorrowCorrelation(0.6, 9);
    }

    @Test
    void theHigherTheBorrowedAmountTheLowerShouldBeTheTimerLessThan80Percent() {
        //Given nothing happened before
        checkTimerBorrowCorrelation(0.8, 6);
    }

    @Test
    void theHigherTheBorrowedAmountTheLowerShouldBeTheTimerLessThan100Percent() {
        //Given nothing happened before
        checkTimerBorrowCorrelation(1.0, 3);
    }

    private void checkTimerBorrowCorrelation(double percent, int timer) {
        //Given nothing happened before
        testModel.setStatusPoints(1);
        long amount = (long) (10000 * percent) - 1;
        //When the player borrows money
        testModel.borrowCashFromJanitor(amount);
        //Then the timer should be different at certain thresholds
        assertEquals(timer, testModel.getDebtTimer());
    }

    @Test
    void shouldNotBePossibleToBorrowMoneyIfDebtIsNotZero() {
        //Given the player already has debt
        testModel.setDebt(1000);
        //When he tries to borrow money
        //Then he should get an error message
        assertThrows(HasAlreadyDebtException.class, () -> testModel.borrowCashFromJanitor(1000));
    }

    @Test
    void shouldDecreaseTheDebtTimerAtTheBeginningOfTheDay() {
        //Given the player has a debt timer
        testModel.setDebt(100);
        testModel.setDebtTimer(10);
        //When the next day begins
        testModel.nextDay();
        //Then the timer should be decreased by one
        assertEquals(9, testModel.getDebtTimer());
    }

    @Test
    void shouldIncreaseDebtAtTheBeginningOfDay() {
        //Given the player has Debt
        testModel.setDebt(1000);
        //When the next day begins
        testModel.nextDay();
        //Then the debt should be increased
        long debt = (long) (1000 * ((double) DefaultSettings.INTREST_CHARGES / 100));
        assertEquals(debt, testModel.getDebt());
    }

    @Test
    void timerShouldNotDecreaseIfDebtIsZero() {
        //Given the player has no debt
        //When the next day begins
        testModel.nextDay();
        //Then the timer should stay at 0
        assertEquals(0, testModel.getDebtTimer());
    }

    @Test
    void ifTimerReachesMinusOneThePlayerGetsAtacked() {
        //Given the player has debt
        // And the timer is at 0
        testModel.setDebt(1000);
        testModel.setDebtTimer(0);
        testModel.setHealth(100);
        //When the next Dey begins
        testModel.nextDay();
        //then the player should be attacked
        assertEquals(75, testModel.getHealth());
    }

    @Test
    void ifPlayerIsAttackedTheTimerIsSetTo7() {
        //Given the player has debt
        // And the timer is at 0
        testModel.setDebt(1000);
        testModel.setDebtTimer(0);
        //When the next Dey begins
        testModel.nextDay();
        //Then the timer should be 7
        assertEquals(7, testModel.getDebtTimer());
    }

    @Test
    void ifTimerReachesMinusOneMoreThanOnceThePlayerGetsAttackedThatManyTimes() {
        //Given the player has debt
        // And the timer is at 0
        //And he had missed a deadline already
        testModel.setDebt(1000);
        testModel.setDebtTimer(0);
        testModel.setHealth(100);
        testModel.setBrokenDeadlines(1);
        //When the next Dey begins
        testModel.nextDay();
        //then the player should be attacked multiple times
        assertEquals(50, testModel.getHealth());
    }
}
