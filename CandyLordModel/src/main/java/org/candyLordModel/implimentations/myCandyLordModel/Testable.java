package org.candyLordModel.implimentations.myCandyLordModel;

import org.candyLordModel.implimentations.settings.Candies;
import org.candyLordModel.implimentations.settings.Locations;

public interface Testable {
    void setCandyPriceAtCurrendLocation(Candies candies, int cash);

    void setCandyAmountInInventory(Candies candies, int capacity);

    void setTravelCostToLocationFromCurrendLocation(Locations location, long cash);

    void setUpNextEvent(Event event);

    Event getRowdyEvent();

    void setBrokenDeadlines(int amount);

    void setDebtTimer(int timer);

    void setStatusPoints(int statusPoints);

    void setDebt(long amount);

    void setPiggyBankAmount(long cash);

    void setCurrentLocation(Locations location);

    void setCapacity(int capacity);

    void setCash(long cash);
}
