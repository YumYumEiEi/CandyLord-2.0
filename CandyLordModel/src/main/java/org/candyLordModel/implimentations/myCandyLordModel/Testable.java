package org.candyLordModel.implimentations.myCandyLordModel;

import org.candyLordModel.implimentations.settings.Candies;
import org.candyLordModel.implimentations.settings.Locations;

import java.util.Map;

public interface Testable {
    void setCandyPriceAtCurrendLocation(Candies candies, int cash);

    void setCandyAmountInInventory(Candies candies, int capacity);

    void setTravelCostToLocationFromCurrendLocation(Locations location, long cash);

    void setUpNextEvent(MyCandyLordEvents event, Object...objects);

    Locations getPlayerLocation();

    void setWeaponProvider(WeaponProvider provider);

    int getWeaponDMG();

    int getWeaponAccuracy();

    void setWeapon(MyCandyLordWeapon myCandyLordWeapon);

    MyCandyLordWeapon getWeapon();

    void setLocationProvider(LocationProvider provider);

    void setCandyProvider(CandyProvider provider);

    void setBrokenDeadlines(int amount);

    void setDebtTimer(int timer);

    void setStatusPoints(int statusPoints);

    void setDebt(long amount);

    void setPiggyBankAmount(long cash);

    void setCurrentLocation(Locations location);

    void setCapacity(int capacity);

    void setCash(long cash);
}
