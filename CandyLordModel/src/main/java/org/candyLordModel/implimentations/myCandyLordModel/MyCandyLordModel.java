package org.candyLordModel.implimentations.myCandyLordModel;

import org.candyLordModel.api.EventAnswer;
import org.candyLordModel.api.TrenchcoatOffer;
import org.candyLordModel.implimentations.exceptions.*;
import org.candyLordModel.implimentations.settings.Candies;
import org.candyLordModel.implimentations.settings.Locations;
import org.candyLordModel.implimentations.util.EnumUtil;
import org.candyLordModel.api.CandyLordModelApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public
class MyCandyLordModel implements CandyLordModelApi, Testable {
    private static MyCandyLordModel instance;

    private final Character character;
    private int timer;
    private long piggyBank;
    private EventHandler eventHandler;

    public static MyCandyLordModel getInstance(String characterName) {
        if (instance == null) {
            instance = new MyCandyLordModel(characterName);
        }
        return instance;
    }

    public static MyCandyLordModel getTestModel() {
        return new MyCandyLordModel("Test Character");
    }

    private MyCandyLordModel(String characterName) {
        character = new Character(characterName);
        this.timer = 0;
        this.eventHandler = new EventHandler(this);
    }

    @Override
    /**
     *
     * @param candie
     * @param quantity
     *
     * @throws NotEnoughMoneyException Is thrown when the Player does not have enough cash to buy Candies
     * @throws NotEnoughCapacityException Is thrown, when the Player does not have enough capacity in his Inventory to buy the Candy
     */
    public void buyCandy(String candyName, int quantity) {
        Candies candy = EnumUtil.stingToEnum(Candies.class, candyName);
        character.buyCandy(candy, quantity);
    }

    /**
     * @param candyName
     * @param quantity
     * @throws NotEnoughCandyException Is thrown when a Player does not have enough candy in the inventory
     */
    @Override
    public void sellCandy(String candyName, int quantity) {
        Candies candy = EnumUtil.stingToEnum(Candies.class, candyName);
        character.sellCandy(candy, quantity);
    }

    /**
     * @param locationName
     * @throws NotEnoughMoneyException    Is thrown, when the player does not have enough money to pay the travel cost
     * @throws AlreadyAtTheChosenLocation Is thrown, when the player is already at the location he should travel to
     * @throws NoSuchLocationFound        Is thrown, when the player wants to travel to a not defined location
     */
    @Override
    public void
    changeLocation(String locationName) {
        try {
            Locations location = EnumUtil.stingToEnum(Locations.class, locationName);
            character.travelTo(location);
        } catch (IllegalArgumentException e) {
            throw new NoSuchLocationFound(e, "This location doesn't excist!");
        }
    }

    @Override
    public void putCashInPiggyBank(long cash) {
        if (character.getCash() < cash) {
            throw new NotEnoughMoneyException("You don't have so much money for the piggy bank!");
        }
        piggyBank += cash;
        character.changeCash(-cash);
    }

    @Override
    public void retrieveMoneyFromPiggyBank(long cash) {
        if (piggyBank < cash) {
            throw new NotEnoughtMoneyInPiggyBankException("You don't have that much money in the piggy bank!");
        }
        putCashInPiggyBank(-cash);
    }

    /**
     * @param amount
     * @throws ToLowBorrowAmountException Is thrown, when the player tries borrows less than 1000 cent
     * @throws ToLessCredibilityException Is thrown, when the player wants to borrow more than his status points allow
     * @throws HasAlreadyDebtException    Is thrown, when the player has already debt
     */
    @Override
    public void borrowCashFromJanitor(long amount) {
        character.borrowCashFromJanitor(amount);
    }

    /**
     * @param amount
     * @throws NotEnoughMoneyException Is thrown, when the player wants to pay back more than he possesses
     * @throws ToMuchPayBackException  Is thrown, when the player tries to pay back more than he has debt
     */
    @Override
    public void payBackTeJanitor(long amount) {
        character.payBackJanitor(amount);
    }

    @Override
    public EventAnswer nextDay() {
        timer++;
        character.nextDay();
        return eventHandler.nextDay();
    }

    @Override
    public void goToHospital() {
        int passedDays = character.goToHospital();
        for (int day = 0; day < passedDays; day++) {
            nextDay();
        }
    }

    /**
     * @param offer
     * @throws NotEnoughMoneyException Is thrown, when the player does not have enough money to buy the trenchcoat
     */
    @Override
    public void acceptTranchcoatOffer(TrenchcoatOffer offer) {
        character.acceptTranchcoatOffer(offer);
    }

    /**
     * @param offer
     * @throws NotEnoughMoneyException Is thrown, when the player does not have enough money to buy weapon
     */
    @Override
    public void acceptWeaponOffer(WeaponOffer offer) {
        MyCandyLordWeapon weapon = EnumUtil.stingToEnum(MyCandyLordWeapon.class, offer.getName());
        character.acceptWeaponOffer(weapon, offer.getPrice());
    }

    @Override
    public void fleeFromBattle() {
        character.fleeFromBattle();
    }

    @Override
    public int getPassedDays() {
        return timer;
    }

    @Override
    public void getHit(int dmgValue) {
        character.decreaseHealth(dmgValue);
    }

    @Override
    public HashMap<String, Integer> getCandyInventory() {
        HashMap<String, Integer> inventory = new HashMap<>();
        character.copyInventoryWithoutEnum(inventory);
        return inventory;
    }

    @Override
    public TreeMap getCandyPriceList() {
        TreeMap<String, Long> priceList = new TreeMap<>();
        character.getCurrentLocation().getCandyPriceList().forEach(((candies, price) -> priceList.put(candies.toString(), price)));
        return priceList;
    }

    @Override
    public HashMap<String, Long> getLocationPriceList() {
       return Locations.getTravelCostList(character.getCurrentLocation());
    }

    @Override
    public Integer getAttention() {
        return 0;
    }

    @Override
    public Integer getJanitorAnger() {
        return character.getJanitorAnger();
    }

    @Override
    public int getProtection() {
        return 0;
    }

    @Override
    public int getWeaponAmount() {
        return 0;
    }

    @Override
    public String getWeaponType() {
        return null;
    }

    @Override
    public void setHealth(int health) {
        character.setHealth(health);
    }

    @Override
    public String getCharacterName() {
        return this.character.getCharacterName();
    }

    @Override
    public int getTimer() {
        return this.timer;
    }

    @Override
    public long getCash() {
        return this.character.getCash();
    }

    @Override
    public int getHealth() {
        return this.character.getHealth();
    }

    @Override
    public String getPlayerLocationName() {
        return this.character.getCurrentLocation().toString();
    }

    @Override
    public ArrayList<String> getAllCandieNames() {
        return Candies.getAllCandyNames();
    }

    @Override
    public List<String> getAllLocationNames() {
        return Locations.getAllLocationNames();
    }

    @Override
    public int getCapacity() {
        return character.getCapacity();
    }

    @Override
    public int getStatusPoints() {
        return character.getStatusPoints();
    }

    @Override
    public int getNumberOfCandyInPossesion(String candyName) {
        Candies candy = EnumUtil.stingToEnum(Candies.class, candyName);
        return character.getAmountOfCandy(candy);
    }

    @Override
    public long getPiggyBankContent() {
        return piggyBank;
    }

    @Override
    public long getDebt() {
        return character.getDebt();
    }

    @Override
    public int getDebtTimer() {
        return character.getDebtTimer();
    }

    @Override
    public void setCandyPriceAtCurrendLocation(Candies candies, int cost) {
        Locations location = character.getCurrentLocation();
        location.setCandyPrice(candies, cost);
    }

    @Override
    public void setCandyAmountInInventory(Candies candies, int amount) {
        character.setCandyAmountInInventory(candies, amount);
    }

    @Override
    public void setTravelCostToLocationFromCurrendLocation(Locations location, long cash) {
        character.getCurrentLocation().setTravelPriceTo(location, cash);
    }

    @Override
    public void setUpNextEvent(MyCandyLordEvents event, Object... objects) {
        eventHandler.setUpNextEvent(event);
    }

    @Override
    public Locations getPlayerLocation() {
        return character.getCurrentLocation();
    }

    @Override
    public void setWeaponProvider(WeaponProvider provider) {
        eventHandler.setWeaponProvider(provider);
    }

    @Override
    public int getWeaponDMG() {
        return character.getWeaponDMG();
    }

    @Override
    public int getWeaponAccuracy() {
        return character.getWeaponAccuracy();
    }

    @Override
    public void setWeapon(MyCandyLordWeapon myCandyLordWeapon) {
        character.setWeapon(myCandyLordWeapon);
    }

    @Override
    public MyCandyLordWeapon getWeapon() {
        return (MyCandyLordWeapon) character.getWeapon();
    }

    @Override
    public void setLocationProvider(LocationProvider provider) {
        eventHandler.setLocationProvider(provider);
    }

    @Override
    public void setCandyProvider(CandyProvider provider) {
        eventHandler.setCandyProvider(provider);
    }

    @Override
    public void setBrokenDeadlines(int amount) {
        character.setBrokenDeadlines(amount);
    }

    @Override
    public void setDebtTimer(int timer) {
        character.setDebtTimer(timer);
    }

    @Override
    public void setStatusPoints(int statusPoints) {
        character.setStatusPoints(statusPoints);
    }

    @Override
    public void setDebt(long amount) {
        character.setDebt(amount);
    }

    @Override
    public void setPiggyBankAmount(long cash) {
        piggyBank = cash;
    }

    @Override
    public void setCurrentLocation(Locations location) {
        character.setCurrentLocation(location);
    }

    @Override
    public void setCapacity(int capacity) {
        character.setCapacity(capacity);
    }

    @Override
    public void setCash(long cash) {
        character.setCash(cash);
    }
}
