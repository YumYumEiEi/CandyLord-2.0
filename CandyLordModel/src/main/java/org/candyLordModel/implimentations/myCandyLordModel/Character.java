package org.candyLordModel.implimentations.myCandyLordModel;

import org.candyLordModel.api.TrenchcoatOffer;
import org.candyLordModel.implimentations.exceptions.*;
import org.candyLordModel.implimentations.settings.Candies;
import org.candyLordModel.implimentations.settings.DefaultSettings;
import org.candyLordModel.implimentations.settings.Locations;

import java.util.*;

class Character {
    private final CandyInventory candyInventory;
    private long cash;
    private int health;
    private Locations currentLocation;
    private final String characterName;
    private int statusPoints;
    private final Janitor janitor;
    private Weapon myCandyLordWeapon;

    Character(String characterName) {
        this.characterName = characterName;
        this.health = DefaultSettings.STARTING_HEALTH;
        this.currentLocation = Locations.getRandomLocation();
        this.cash = DefaultSettings.START_CASH;
        this.statusPoints = DefaultSettings.START_STATUS_POINTS;
        this.candyInventory = new CandyInventory();
        this.janitor = new Janitor(this);
        this.myCandyLordWeapon = MyCandyLordWeapon.FIST;
    }


    /**
     *
     * @param candie
     * @param quantity
     *
     * @throws NotEnoughMoneyException Is thrown when the Player does not have enough cash to buy Candies
     * @throws NotEnoughCapacityException Is thrown, when the Player does not have enough capacity in his Inventory to buy the Candy
     */
    public void buyCandy(Candies candie, int quantity) {
        long price = currentLocation.getCandyPrice(candie) * quantity;
        if (cash < price){
            throw new NotEnoughMoneyException("You don't have enough cash to buy these!");
        }
        changeCash(-price);

        if(!hasEnoughPlaceInInventory(quantity)){
            throw new NotEnoughCapacityException("You don't have enough space in your inventory to buy these!");
        }
        int newAmount = candyInventory.get(candie) + quantity;
        candyInventory.put(candie, newAmount);
    }

    /**
     *
     * @param candy
     * @param quantity
     *
     * @throws NotEnoughCandyException Is thrown when a Player does not have enough candy in the inventory
     */
    public void sellCandy(Candies candy, int quantity) {
        if(candyInventory.get(candy) < quantity){
            throw new NotEnoughCandyException("You don't have enough " + candy);
        }
        int newAmount = candyInventory.get(candy) - quantity;
        candyInventory.put(candy, newAmount);

        long candyPrice = currentLocation.getCandyPrice(candy);
        cash += candyPrice*quantity;
    }

    private boolean hasEnoughPlaceInInventory(int quantity) {
        return candyInventory.hasEnoughPlaceInInventory(quantity);
    }

    public void changeCash(long amount) {
        cash += amount;
    }

    public void setCandyAmountInInventory(Candies candies, int amount) {
        candyInventory.put(candies, amount);
    }

    /**
     *
     * @param location
     *
     * @throws NotEnoughMoneyException Is thrown, when the player does not have enough money to pay the travel cost
     * @throws AlreadyAtTheChosenLocation Is thrown, when the player is already at the location he should travel to
     */
    public void travelTo(Locations location) {
        if(location.equals(currentLocation)){
            throw new AlreadyAtTheChosenLocation("You are already here!");
        }
        long payment = currentLocation.getTravelPrice(location);
        if(payment > cash){
            throw new NotEnoughMoneyException("You don't have enough money to travel to " + location  + "!");
        }
        changeCash(-payment);
        this.currentLocation = location;
    }

    /**
     *
     * @param amount
     *
     * @throws ToLowBorrowAmountException Is thrown, when the player tries borrows less than 1000 cent
     * @throws ToLessCredibilityException Is thrown, when the player wants to borrow more than his status points allow
     * @throws HasAlreadyDebtException Is thrown, when the player has already debt
     */
    public void borrowCashFromJanitor(long amount) {
        janitor.borrowCash(amount);
    }

    /**
     *
     * @param amount
     *
     * @throws NotEnoughMoneyException Is thrown, when the player wants to pay back more than he possesses
     * @throws ToMuchPayBackException Is thrown, when the player tries to pay back more than he has debt
     */
    public void payBackJanitor(long amount) {
        janitor.payBackDebt(amount);
    }

    public void nextDay() {
        janitor.nextDay();
    }

    public void decreaseHealth(int amount) {
        health -= amount;
    }

    public int goToHospital() {
        int daysTillHealthy = (int)((100 - (double)health)/10);
        long cost = (100-health)*DefaultSettings.CENT_PER_HEALTH_COST;
        if(cash < cost){
            throw new NotEnoughMoneyException("You don't have the money to pay for the treatment!");
        }
        changeCash(-cost);
        setHealth(100);

        return daysTillHealthy;
    }

    /**
     *
     * @param offer
     *
     * @throws NotEnoughMoneyException Is thrown, when the player does not have enough money to buy the trenchcoat
     */
    public void acceptTranchcoatOffer(TrenchcoatOffer offer) {
        if(cash < offer.getPrice()){
            throw new NotEnoughMoneyException("You don't have enough cash to buy this fashionable trenchcoat!");
        }
        changeCash(-offer.getPrice());
        setCapacity(offer.getCapacity());
    }

    /**
     *
     * @param weapon
     * @param price
     *
     * @throws NotEnoughMoneyException Is thrown, when the player does not have enough money to buy weapon
     */
    public void acceptWeaponOffer(MyCandyLordWeapon weapon, long price) {
        if(cash < price){
            throw new NotEnoughMoneyException("You don't have the money for this weapon");
        }
        setWeapon(weapon);
        changeCash(-price);

    }

    public void fleeFromBattle() {
        statusPoints--;
    }

    public void copyInventoryWithoutEnum(HashMap<String, Integer> inventory) {
        candyInventory.copyInventoryWithoutEnum(inventory);
    }

    public int getWeaponDMG() {
        return getWeapon().getDMG();
    }

    public int getWeaponAccuracy() {
        return getWeapon().getAccuracy();
    }

    public Integer getJanitorAnger() {
        return janitor.getAnger();
    }

    public void setWeapon(MyCandyLordWeapon myCandyLordWeapon) {
        this.myCandyLordWeapon = myCandyLordWeapon;
    }

    public Weapon getWeapon() {
        return this.myCandyLordWeapon;
    }

    public void setBrokenDeadlines(int amount) {
        janitor.setBrokenDeadlines(amount);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDebtTimer(int timer) {
        janitor.setDebtTimer(timer);
    }

    public void setStatusPoints(int statusPoints) {
        this.statusPoints = statusPoints;
    }

    public void setDebt(long amount) {
        janitor.setDebt(amount);
    }

    public long getDebt() {
        return janitor.getDebt();
    }

    public int getDebtTimer() {
        return janitor.getDebtTimer();
    }

    public void setCapacity(int capacity) {
        candyInventory.setCapacity(capacity);
    }

    public long getCash() {
        return cash;
    }

    public int getCapacity() {
        return candyInventory.getCapacity();
    }

    public int getStatusPoints() {
        return this.statusPoints;
    }

    public int getAmountOfCandy(Candies candy) {
        return candyInventory.get(candy);
    }

    public void setCash(long cash) {
        this.cash = cash;
    }

    public int getHealth() {
        return health;
    }

    public Locations getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Locations location) {
        this.currentLocation = location;
    }

    public String getCharacterName() {
        return characterName;
    }
}
