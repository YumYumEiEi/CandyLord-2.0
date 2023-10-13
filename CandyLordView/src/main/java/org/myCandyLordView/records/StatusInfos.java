package org.myCandyLordView.records;

import java.util.HashMap;
import java.util.TreeMap;

public record StatusInfos(
        HashMap<String, Integer> candyInventory,
        TreeMap<String, Long> candyPrices,
        String name,
        String locationName,
        String weaponType,
        int anger,
        int attention,
        int fame,
        int timer,
        int health,
        int protection,
        int weaponAmount,
        long cash,
        long bankMoney,
        long debt

) {
}
