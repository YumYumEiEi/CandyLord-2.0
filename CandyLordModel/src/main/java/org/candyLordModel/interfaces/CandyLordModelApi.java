package org.candyLordModel.interfaces;

import org.candyLordModel.implimentations.myCandyLordModel.MyCandyLordModel;

import java.util.ArrayList;
import java.util.List;

public interface CandyLordModelApi {

    public static CandyLordModelApi getInstance(String characterName){
        return new MyCandyLordModel(characterName);
    }

    void buyCandy(String candyName, int quantity);

    void sellCandy(String name, int amount);

    void travelTo(String locationName);

    void putCashInPiggyBank(long cash);

    void retrieveMoneyFromPiggyBank(long cash);

    void borrowCashFromJanitor(long amount);

    void payBackTeJanitor(long amount);

    void nextDay();

    void goToHospital();

    int getPassedDays();

    void setHealth(int health);

    String getCharacterName();

    int getTimer();

    long getCash();

    int getHealth();

    String getPlayerLocationName();

    ArrayList<String> getAllCandieNames();

    List<String> getAllLocationNames();

    int getCapacity();

    int getStatusPoints();

    int getNumberOfCandyInPossesion(String candyName);

    long getPiggyBankContent();

    long getDebt();

    int getDebtTimer();
}
