package org.candyLordModel.implimentations.myCandyLordModel;

import org.candyLordModel.implimentations.exceptions.*;
import org.candyLordModel.implimentations.settings.DefaultSettings;

public class Janitor {

    private final Character customer;
    private long debt;
    private int timer;
    private int brokenDeadlines;

    public Janitor(Character character){
        this.customer = character;
        this.debt = 0;
        this.timer = 0;
        this.brokenDeadlines = 0;
    }

    /**
     *
     * @param amount
     *
     * @throws ToLowBorrowAmountException Is thrown, when the player tries borrows less than 1000 cent
     * @throws ToLessCredibilityException Is thrown, when the player wants to borrow more than his status points allow
     * @throws HasAlreadyDebtException Is thrown, when the player has already debt
     */
    public void borrowCash(long amount) {
        if(debt > 0){
            throw new HasAlreadyDebtException("First pay back your debt!");
        }
        if(amount < 1000){
            throw new ToLowBorrowAmountException("This is to less money! I wont make any effort for that!");
        }
        if(amount >= customer.getStatusPoints()* 10000L){
            throw new ToLessCredibilityException("You are not trustworthy enough!");
        }
        debt = amount*DefaultSettings.INTREST_CHARGES/100;
        customer.changeCash(amount);
        setTimer(amount);
    }

    public void nextDay() {
        if(debt != 0){
            timer--;
            debt = (long) (debt * ((double)DefaultSettings.INTREST_CHARGES / 100));
        }
        if(timer < 0){
            brokenDeadlines++;
            for (int amount = 0; amount < brokenDeadlines; amount++) {
                intimidate();
            }
        }
    }

    private void intimidate() {
        customer.decreaseHealth(25);
        timer = 7;
    }

    public Integer getAnger() {
        return brokenDeadlines;
    }

    public void setBrokenDeadlines(int amount) {
        this.brokenDeadlines = amount;
    }

    private void setTimer(long amount) {
        double percent = (double) amount / getMaximumBorrowAmount();
        if(percent < 0.20){
            timer = 15;
        } else if (percent < 0.40) {
            timer = 12;
        }else if (percent < 0.60){
            timer = 9;
        } else if (percent < 0.80) {
            timer = 6;
        }else {
            timer = 3;
        }
    }

    private long getMaximumBorrowAmount() {
        return customer.getStatusPoints()*10000;
    }

    /**
     *
     * @param amount
     *
     * @throws NotEnoughMoneyException Is thrown, when the player wants to pay back more than he possesses
     * @throws ToMuchPayBackException Is thrown, when the player tries to pay back more than he has debt
     */
    public void payBackDebt(long amount) {
        if(customer.getCash() < amount){
            throw new NotEnoughMoneyException("You don't have that much money!");
        }
        if(debt < amount){
            throw new ToMuchPayBackException("You don't owe me that much!");
        }
        debt -= amount;
        customer.changeCash(-amount);
        if (debt == 0){
            timer = 0;
        }
    }

    public void setDebtTimer(int timer) {
        this.timer = timer;
    }

    public void setDebt(long amount) {
        debt = amount;
    }

    public long getDebt() {
        return this.debt;
    }

    public int getDebtTimer() {
        return this.timer;
    }
}
