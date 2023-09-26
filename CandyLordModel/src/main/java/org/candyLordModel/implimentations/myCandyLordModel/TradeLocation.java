package org.candyLordModel.implimentations.myCandyLordModel;

import org.candyLordModel.implimentations.settings.Candies;
import org.candyLordModel.implimentations.settings.Locations;

public interface TradeLocation {
    public long getCandyPrice(Candies candyEnum);

    void setCandyPrice(Candies candies, long cost);

    void setTravelPriceTo(Locations location, long cash);

    long getTravelPrice(Locations location);
}
