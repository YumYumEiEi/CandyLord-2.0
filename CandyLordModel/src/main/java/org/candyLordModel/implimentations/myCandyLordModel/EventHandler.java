package org.candyLordModel.implimentations.myCandyLordModel;

import org.candyLordModel.api.EmptyEventAwnser;
import org.candyLordModel.api.EventAnswer;
import org.candyLordModel.api.TrenchcoatOffer;
import org.candyLordModel.implimentations.settings.Candies;
import org.candyLordModel.implimentations.settings.Locations;

import java.util.*;

public class EventHandler {
    MyCandyLordModel model;
    private ArrayList<MyCandyLordEvents> eventList;
    private final EnumMap<MyCandyLordEvents, Event> eventMap;
    private LocationProvider locationProvider;
    private CandyProvider candyProvider;
    private WeaponProvider weaponProvider;

    public EventHandler(MyCandyLordModel model) {
        this.model = model;
        this.eventList = new ArrayList<>();
        this.eventMap = new EnumMap<>(MyCandyLordEvents.class);
        this.locationProvider = new RandomLocationProvider();
        this.candyProvider = new RandomCandyProvider();
        this.weaponProvider = new RandomWeaponProvider();
        setUpEvents();
    }

    private void setUpEvents() {
        for (MyCandyLordEvents events : MyCandyLordEvents.values()) {
            eventList.add(events);
            switch (events) {
                case POCKET_THIEVE -> {
                    eventMap.put(events, () -> {
                        model.setCash(0);
                        return new EmptyEventAwnser();
                    });
                }
                case CHOCOLATE_SISTER -> {
                    eventMap.put(events, () -> {
                        model.setCandyAmountInInventory(Candies.CHOCOLATE_BAR, 0);
                        return new EmptyEventAwnser();
                    });
                }
                case CANDY_SHORTAGE -> {
                    eventMap.put(events, () -> {
                        Locations locations = locationProvider.getLocation();
                        for (Candies candie : Candies.values()) {
                            if (candie == Candies.CIRCLE_SHOCK){
                                locations.setCandyPrice(candie, locations.getCandyPrice(candie) + 1);
                            }
                            locations.setCandyPrice(candie, (long) ((double) locations.getCandyPrice(candie) * 1.1));
                        }
                        return new EmptyEventAwnser();
                    });
                }
                case CANDY_DELIVERY -> {
                    eventMap.put(events, () -> {
                        Locations locations = locationProvider.getLocation();
                        for (Candies candies : Candies.values()) {

                            locations.setCandyPrice(candies, (long) ((double) locations.getCandyPrice(candies) / 1.1));
                        }
                        return new EmptyEventAwnser();
                    });
                }
                case CANDY_PRICE_DEFLATION -> {
                    eventMap.put(events, () -> {
                        Candies candies = candyProvider.getCandy();
                        Locations locations = locationProvider.getLocation();
                        long price = (long) ((double) locations.getCandyPrice(candies) / 1.3);
                        locations.setCandyPrice(candies, price);
                        return new EmptyEventAwnser();
                    });
                }
                case TRENCHCOAT_OFFER -> {
                    eventMap.put(events, () -> {
                        Random random = new Random();
                        int capacity = -1;
                        while (capacity % 5 != 0 || capacity <= model.getCapacity()) {
                            capacity = random.nextInt(10, 50);
                        }
                        long price = (long)Math.pow(5, (capacity / 5)) * 10 * 75;
                        return new TrenchcoatOffer(capacity, price);
                    });
                }
                case WEAPON_OFFER -> {
                    eventMap.put(events, () ->{
                        MyCandyLordWeapon weapon = weaponProvider.getWeapon();
                       return new WeaponOffer(weapon);
                    });
                }
                case CANDY_PRICE_INFLATION -> {
                    eventMap.put(events, () -> {
                        Candies candies = candyProvider.getCandy();
                        Locations locations = locationProvider.getLocation();
                        long price = (long) ((double) locations.getCandyPrice(candies) * 1.3);
                        locations.setCandyPrice(candies, price);
                        return new EmptyEventAwnser();
                    });
                }
                default -> throw new IllegalStateException("Unexpected value: " + events);
            }
        }
    }

    public EventAnswer nextDay() {
        Collections.shuffle(eventList);
        return eventMap.get(eventList.get(0)).execute();
    }

    public void setWeaponProvider(WeaponProvider provider) {
        this.weaponProvider = provider;
    }

    public void setLocationProvider(LocationProvider provider) {
        this.locationProvider = provider;
    }

    public void setCandyProvider(CandyProvider provider) {
        this.candyProvider = provider;
    }

    public void setUpNextEvent(MyCandyLordEvents event) {
        eventList = new ArrayList<>();
        eventList.add(event);
    }
}
