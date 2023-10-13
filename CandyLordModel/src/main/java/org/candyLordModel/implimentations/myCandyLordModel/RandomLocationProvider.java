package org.candyLordModel.implimentations.myCandyLordModel;

import org.candyLordModel.implimentations.settings.Locations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RandomLocationProvider implements LocationProvider {
    @Override
    public Locations getLocation() {
        List<Locations> locationList = Arrays.asList(Locations.values());
        Collections.shuffle(locationList);
        return locationList.get(0);
    }
}
