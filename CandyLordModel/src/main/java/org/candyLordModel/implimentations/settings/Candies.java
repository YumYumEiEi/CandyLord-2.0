package org.candyLordModel.implimentations.settings;

import org.candyLordModel.implimentations.util.EnumUtil;
import org.candyLordModel.implimentations.myCandyLordModel.TradeGood;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;

public enum Candies implements TradeGood {
    CIRCLE_SHOCK(){
        @Override
        public String toString() {
            return "Circle Schock";
        }
        public long getPrice(){
           return 15 + (long) (((((Math.random() * 60) - 30)) / 100) * 15);
        }
    },
    CHOCOLATE_BAR() {
        @Override
        public String toString() {
            return "Chockolate Bar";
        }
        public long getPrice(){
            return 75 + (long) (((((Math.random() * 60) - 30)) / 100) * 75);

        }
    },
    BUBBA_CHUPS(){
        @Override
        public String toString() {
            return "Bubba Chups";
        }
        public long getPrice(){
            return 375 + (long) (((((Math.random() * 60) - 30)) / 100) * 375);
        }
    },
    SUGAR_NECKLACE(){
        @Override
        public String toString() {
            return  "Sugar Necklace";
        }
        public long getPrice(){
            return 1875 + (long) (((((Math.random() * 60) - 30)) / 100) * 1875);
        }
    },
    N_AND_N(){
        @Override
        public String toString() {
            return "N&N";
        }
        public long getPrice(){
            return 9375 + (long) (((((Math.random() * 60) - 30)) / 100) * 9375);
        }
    },
    JELLY_PEA(){
        @Override
        public String toString() {
            return "Jelly Pea";
        }
        public long getPrice(){
            return 46875 + (long) (((((Math.random() * 60) - 30)) / 100) * 46875);
        }
    },
    SURPRISE_OVAL(){
        @Override
        public String toString() {
            return "Surprise Oval";
        }
        public long getPrice(){
            return 234375 + (long) (((((Math.random() * 60) - 30)) / 100) * 234375);
        }
    },
    INFINITY_LOLLIPOP() {
        @Override
        public String toString() {
            return "Infinity Lollipop";
        }
        public long getPrice(){
            return 1171875 + (long) (((((Math.random() * 60) - 30)) / 100) * 1171875);
        }
    };

    public static ArrayList<String> getAllCandyNames(){
        return EnumUtil.getAllNames(EnumSet.allOf(Candies.class));
    }

    public static EnumMap<Candies, Long> getCandyPriceList() {
        EnumMap<Candies, Long> priceList = new EnumMap<>(Candies.class);
        EnumSet.allOf(Candies.class).forEach((candy) -> priceList.put(candy, candy.getPrice()));
        return priceList;
    }


    public static EnumMap<Candies, Integer> getEmptyCandyInventory() {
        EnumMap<Candies, Integer> candyInventory= new EnumMap<>(Candies.class);
        EnumSet.allOf(Candies.class).forEach((candy) -> candyInventory.put(candy, 0));
        return candyInventory;
    }
}
