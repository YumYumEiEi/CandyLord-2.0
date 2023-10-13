package org.candyLordModel.implimentations.myCandyLordModel;

import org.candyLordModel.implimentations.settings.Candies;
import org.candyLordModel.implimentations.settings.DefaultSettings;

import java.util.*;

public class CandyInventory {

    private final EnumMap<Candies, Integer> candyInventory;
    private int capacity;

    public CandyInventory() {
        this.candyInventory = Candies.getEmptyCandyInventory();
        this.capacity = DefaultSettings.START_CAPACITY;
    }

    public int get(Candies candie) {
        return candyInventory.get(candie);
    }

    public void put(Candies candie, int newAmount) {
        candyInventory.put(candie, newAmount);
    }

    public boolean hasEnoughPlaceInInventory(int quantity) {
        int[] sum = new int[]{quantity};
        candyInventory.forEach((candy, amount) -> sum[0] += amount);
        return capacity >= sum[0];
    }

    public void copyInventoryWithoutEnum(HashMap<String, Integer> inventory) {
        candyInventory.forEach((candy, amount) -> inventory.put(candy.toString(), amount));
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
