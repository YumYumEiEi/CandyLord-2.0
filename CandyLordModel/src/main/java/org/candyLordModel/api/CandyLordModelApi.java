package org.candyLordModel.api;

import org.candyLordModel.implimentations.myCandyLordModel.MyCandyLordModel;
import org.candyLordModel.implimentations.myCandyLordModel.WeaponOffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public interface CandyLordModelApi {

    public static CandyLordModelApi getInstance(String characterName){
        return MyCandyLordModel.getInstance(characterName);
    }

    void buyCandy(String candyName, int quantity);

    void sellCandy(String name, int amount);

    void changeLocation(String locationName);

    void putCashInPiggyBank(long cash);

    void retrieveMoneyFromPiggyBank(long cash);

    void borrowCashFromJanitor(long amount);

    void payBackTeJanitor(long amount);

    EventAnswer nextDay();

    void goToHospital();

    void acceptTranchcoatOffer(TrenchcoatOffer offer);

    void acceptWeaponOffer(WeaponOffer offer);

    void fleeFromBattle();

    int getPassedDays();

    void getHit(int dmgValue);

    HashMap<String, Integer> getCandyInventory();

    TreeMap getCandyPriceList();

    HashMap<String, Long> getLocationPriceList();

    Integer getAttention();

    Integer getJanitorAnger();

    int getProtection();

    int getWeaponAmount();

    String getWeaponType();

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
