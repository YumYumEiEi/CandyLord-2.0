package org.candyLordModel.implimentations.myCandyLordModel;

import org.candyLordModel.implimentations.settings.Candies;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RandomCandyProvider implements CandyProvider {
    @Override
    public Candies getCandy() {
        List<Candies> candyList = Arrays.asList(Candies.values());
        Collections.shuffle(candyList);
        return candyList.get(0);
    }
}
