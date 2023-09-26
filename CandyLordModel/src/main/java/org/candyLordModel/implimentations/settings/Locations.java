package org.candyLordModel.implimentations.settings;

import org.candyLordModel.implimentations.myCandyLordModel.TradeLocation;
import org.candyLordModel.implimentations.util.EnumUtil;

import java.util.*;

public enum Locations implements TradeLocation {

    LINCOL_SCHOOL_OF_FINE_ARTS {
        @Override
        public void setTravelMap(EnumMap<Locations, Long> travelMap) {
            travelMap.put(LINCOL_SCHOOL_OF_FINE_ARTS, 0L);
            travelMap.put(FOREST_LAKE_SECONDARY_SCHOOL, 85L);
            travelMap.put(EASTWOOD_COLLEGE, 145L);
            travelMap.put(PANORAMA_HIGH_SCHOOL, 214L);
            travelMap.put(FREEDOM_UNIVERSITY, 194L);
            travelMap.put(RIDGEVIEW_SCHOOL_FOR_GIRLS, 145L);
            travelMap.put(MONARCH_HIGH, 214L);
            travelMap.put(HAWKING_CHARTER_SCHOOL, 145L);
            this.travelCosts = travelMap;
        }
        @Override
        public String toString() {
            return "Lincol School of Fine Arts";
        }


    },

    FOREST_LAKE_SECONDARY_SCHOOL {
        @Override
        public void setTravelMap(EnumMap<Locations, Long> travelMap) {
            travelMap.put(LINCOL_SCHOOL_OF_FINE_ARTS, 85L);
            travelMap.put(FOREST_LAKE_SECONDARY_SCHOOL, 0L);
            travelMap.put(EASTWOOD_COLLEGE, 159L);
            travelMap.put(PANORAMA_HIGH_SCHOOL, 241L);
            travelMap.put(FREEDOM_UNIVERSITY, 241L);
            travelMap.put(RIDGEVIEW_SCHOOL_FOR_GIRLS, 159L);
            travelMap.put(MONARCH_HIGH, 241L);
            travelMap.put(HAWKING_CHARTER_SCHOOL, 159L);
            this.travelCosts = travelMap;
        }
        @Override
        public String toString() {
            return "Forest Lake Secondary School";
        }
    },
    EASTWOOD_COLLEGE {
        @Override
        public void setTravelMap(EnumMap<Locations, Long> travelMap) {
            travelMap.put(LINCOL_SCHOOL_OF_FINE_ARTS, 145L);
            travelMap.put(FOREST_LAKE_SECONDARY_SCHOOL, 159L);
            travelMap.put(EASTWOOD_COLLEGE, 0L);
            travelMap.put(PANORAMA_HIGH_SCHOOL, 109L);
            travelMap.put(FREEDOM_UNIVERSITY, 293L);
            travelMap.put(RIDGEVIEW_SCHOOL_FOR_GIRLS, 265L);
            travelMap.put(MONARCH_HIGH, 109L);
            travelMap.put(HAWKING_CHARTER_SCHOOL, 265L);
            this.travelCosts = travelMap;
        }
        @Override
        public String toString() {
            return "Eastwood College";
        }
    },
    PANORAMA_HIGH_SCHOOL {
        @Override
        public void setTravelMap(EnumMap<Locations, Long> travelMap) {
            travelMap.put(LINCOL_SCHOOL_OF_FINE_ARTS, 214L);
            travelMap.put(FOREST_LAKE_SECONDARY_SCHOOL, 241L);
            travelMap.put(EASTWOOD_COLLEGE, 109L);
            travelMap.put(PANORAMA_HIGH_SCHOOL, 0L);
            travelMap.put(FREEDOM_UNIVERSITY, 330L);
            travelMap.put(RIDGEVIEW_SCHOOL_FOR_GIRLS, 330L);
            travelMap.put(MONARCH_HIGH, 25L);
            travelMap.put(HAWKING_CHARTER_SCHOOL, 330L);
            this.travelCosts = travelMap;
        }
        @Override
        public String toString() {
            return "Panorama High School";
        }
    },
    FREEDOM_UNIVERSITY {
        @Override
        public void setTravelMap(EnumMap<Locations, Long> travelMap) {
            travelMap.put(LINCOL_SCHOOL_OF_FINE_ARTS, 194L);
            travelMap.put(FOREST_LAKE_SECONDARY_SCHOOL, 241L);
            travelMap.put(EASTWOOD_COLLEGE, 293L);
            travelMap.put(PANORAMA_HIGH_SCHOOL, 330L);
            travelMap.put(FREEDOM_UNIVERSITY, 0L);
            travelMap.put(RIDGEVIEW_SCHOOL_FOR_GIRLS, 145L);
            travelMap.put(MONARCH_HIGH, 330L);
            travelMap.put(HAWKING_CHARTER_SCHOOL, 145L);
            this.travelCosts = travelMap;
        }
        @Override
        public String toString() {
            return "Freedom University";
        }
    },
    RIDGEVIEW_SCHOOL_FOR_GIRLS {
        @Override
        public void setTravelMap(EnumMap<Locations, Long> travelMap) {
            travelMap.put(LINCOL_SCHOOL_OF_FINE_ARTS, 145L);
            travelMap.put(FOREST_LAKE_SECONDARY_SCHOOL, 159L);
            travelMap.put(EASTWOOD_COLLEGE, 265L);
            travelMap.put(PANORAMA_HIGH_SCHOOL, 330L);
            travelMap.put(FREEDOM_UNIVERSITY, 145L);
            travelMap.put(RIDGEVIEW_SCHOOL_FOR_GIRLS, 0L);
            travelMap.put(MONARCH_HIGH, 330L);
            travelMap.put(HAWKING_CHARTER_SCHOOL, 25L);
            this.travelCosts = travelMap;
        }
        @Override
        public String toString() {
            return "Ridgeview School for Girls";
        }
    },
    MONARCH_HIGH {
        @Override
        public void setTravelMap(EnumMap<Locations, Long> travelMap) {
            travelMap.put(LINCOL_SCHOOL_OF_FINE_ARTS, 214L);
            travelMap.put(FOREST_LAKE_SECONDARY_SCHOOL, 241L);
            travelMap.put(EASTWOOD_COLLEGE, 109L);
            travelMap.put(PANORAMA_HIGH_SCHOOL, 25L);
            travelMap.put(FREEDOM_UNIVERSITY, 330L);
            travelMap.put(RIDGEVIEW_SCHOOL_FOR_GIRLS, 330L);
            travelMap.put(MONARCH_HIGH, 0L);
            travelMap.put(HAWKING_CHARTER_SCHOOL, 330L);
            this.travelCosts = travelMap;
        }
        @Override
        public String toString() {
            return "Monarch High";
        }
    },
    HAWKING_CHARTER_SCHOOL {
        @Override
        public void setTravelMap(EnumMap<Locations, Long> travelMap) {
            travelMap.put(LINCOL_SCHOOL_OF_FINE_ARTS, 145L);
            travelMap.put(FOREST_LAKE_SECONDARY_SCHOOL, 159L);
            travelMap.put(EASTWOOD_COLLEGE, 265L);
            travelMap.put(PANORAMA_HIGH_SCHOOL, 330L);
            travelMap.put(FREEDOM_UNIVERSITY, 145L);
            travelMap.put(RIDGEVIEW_SCHOOL_FOR_GIRLS, 25L);
            travelMap.put(MONARCH_HIGH, 330L);
            travelMap.put(HAWKING_CHARTER_SCHOOL, 0L);
            this.travelCosts = travelMap;

        }
        @Override
        public String toString() {
            return "Hawking Charter School";
        }
    };

    static {
        Locations[] locations = Locations.values();
        for (int i = 0; i < Locations.values().length; i++) {
            locations[i].setTravelMap(new EnumMap<>(Locations.class));
        }
    }

    EnumMap<Candies, Long> candyPrices;
    EnumMap<Locations, Long> travelCosts;

    public abstract void setTravelMap(EnumMap<Locations, Long> travelMap);

    private Locations() {
        candyPrices = Candies.getCandyPriceList();
    }

    public static List<String> getAllLocationNames() {
        return EnumUtil.getAllNames(EnumSet.allOf(Locations.class));
    }

    public long getCandyPrice(Candies candyEnum) {
        return this.candyPrices.get(candyEnum);
    }

    public long getTravelPrice(Locations location){
        return this.travelCosts.get(location);
    }

    public static Locations getRandomLocation() {
        int random = (int) (Math.random() * Locations.values().length);
        for (Locations location : Locations.values()) {
            if (location.ordinal() == random) {
                return location;
            }
        }
        return EASTWOOD_COLLEGE;
    }

    public void setCandyPrice(Candies candy, long cost){
        candyPrices.put(candy, cost);
    }
    
    @Override
    public void setTravelPriceTo(Locations location, long cash){
        travelCosts.put(location, cash);
    }
}
