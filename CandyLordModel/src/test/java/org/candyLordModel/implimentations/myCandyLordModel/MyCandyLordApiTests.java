package org.candyLordModel.implimentations.myCandyLordModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.logging.LogManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyCandyLordApiTests {

    MyCandyLordModel testModel;

    @BeforeEach
    void setUp(){
        testModel = MyCandyLordModel.getTestModel();
    }

    @Test
    void shouldBeAbleToGiveCandyInventory(){
        //Given the game has been started
        //When the game asks for the candy inventory
        HashMap<String, Integer> candyInventory = testModel.getCandyInventory();
        //Then it should get an inventory
        HashMap<String, Integer> expected = prepareEmptyCandyInventoryForOutsiders();
        assertEquals(expected, candyInventory);
    }

    @Test
    void shouldBeAbleToGivePriceList(){
        //Given the player is at a location
        //When the game asks for a price list
        TreeMap priceList = testModel.getCandyPriceList();
        //Then the list should be filled
        HashMap<String, Long> expected = preparePriceList();
        assertEquals(expected, priceList);
    }

    private HashMap<String, Long> preparePriceList() {
        HashMap<String, Long> priceList = new HashMap<>();
        testModel.getPlayerLocation().getCandyPriceList().forEach((candy, price) -> priceList.put(candy.toString(), price));
        return priceList;
    }

    private HashMap<String, Integer> prepareEmptyCandyInventoryForOutsiders() {
        HashMap<String, Integer> inventory = new HashMap<>();
        testModel.getAllCandieNames().forEach((candy) -> inventory.put(candy, 0));
        return inventory;
    }

}
