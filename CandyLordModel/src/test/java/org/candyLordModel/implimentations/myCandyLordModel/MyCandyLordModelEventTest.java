package org.candyLordModel.implimentations.myCandyLordModel;

import org.candyLordModel.api.EventAnswer;
import org.candyLordModel.api.TrenchcoatOffer;
import org.candyLordModel.implimentations.exceptions.NotEnoughMoneyException;
import org.candyLordModel.implimentations.settings.Candies;
import org.candyLordModel.implimentations.settings.Locations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MyCandyLordModelEventTest {

    MyCandyLordModel testModel;

    @BeforeEach
    void setUp() {
        testModel = MyCandyLordModel.getTestModel();
    }

    @Test
    void ifThieveEventIsTriggeredPlayerLoosesAllCash() {
        //Given the player has cash
        testModel.setCash(500);
        testModel.setUpNextEvent(MyCandyLordEvents.POCKET_THIEVE);
        //When the pocket thieve event is triggered
        testModel.nextDay();
        //Then the player should loos all cash
        assertEquals(0, testModel.getCash());
    }

    @Test
    void ifSisterGetChocolateHungryChocolateBarsAreEmpty() {
        //Given the player has chocolate bars
        testModel.setCandyAmountInInventory(Candies.CHOCOLATE_BAR, 10);
        //When the chocolate sister event triggers
        testModel.setUpNextEvent(MyCandyLordEvents.CHOCOLATE_SISTER);
        testModel.nextDay();
        //Then there should be no more chocolate bars in the inventory
        assertEquals(0, testModel.getNumberOfCandyInPossesion(Candies.CHOCOLATE_BAR.name()));
    }

    @Test
    void ifPrizeOfCandyIncreasesEventTriggersCandyShouldGetMoreExpensive() {
        //Given the game is running
        //When the candy prize inflation event is triggered
        Locations locations = Locations.EASTWOOD_COLLEGE;
        Candies candies = Candies.N_AND_N;
        long prize = locations.getCandyPrice(candies);
        testModel.setCandyProvider(() -> candies);
        testModel.setLocationProvider(() -> locations);
        testModel.setUpNextEvent(MyCandyLordEvents.CANDY_PRICE_INFLATION);
        testModel.nextDay();
        //Then the candy should be more expensive

        assertTrue(prize < locations.getCandyPrice(candies));
    }

    @Test
    void ifPrizeOfCandyDecreasesEventTriggersCandyShouldGetLessExpensive() {
        //Given the game is running
        //When the candy prize deflation event is triggered
        Locations locations = Locations.EASTWOOD_COLLEGE;
        Candies candies = Candies.N_AND_N;
        long prize = locations.getCandyPrice(candies);
        testModel.setCandyProvider(() -> candies);
        testModel.setLocationProvider(() -> locations);
        testModel.setUpNextEvent(MyCandyLordEvents.CANDY_PRICE_DEFLATION);
        testModel.nextDay();
        //Then the candy should be less expensive

        assertTrue(prize > locations.getCandyPrice(candies));
    }

    @Test
    void ifCandyShortageEventIsTriggeredAtLocationThePrizeOfEveryCandyShouldBeHigher() {
        //Given the game is running
        Locations locations = Locations.EASTWOOD_COLLEGE;
        Map<Candies, Long> candyPrizeList = new HashMap<>();
        locations.getCandyPriceList().forEach((key, value) -> candyPrizeList.put(key, value.longValue()));
        //When the candy shortage event is triggered at a location
        testModel.setLocationProvider(() -> locations);
        testModel.setUpNextEvent(MyCandyLordEvents.CANDY_SHORTAGE);
        testModel.nextDay();
        //Then the prize of each candy should be increased
        for (Map.Entry<Candies, Long> value :
                locations.getCandyPriceList().entrySet()) {
            long prize = candyPrizeList.get(value.getKey());
            long newPrize = value.getValue();
            assertTrue(prize < newPrize, value.getKey() + " price is not increased!");
        }
    }

    @Test
    void ifCandyDeliveryEventIsTriggeredAllCandiesShouldGetLessExpensive() {
        //Given the game is running
        Locations locations = Locations.EASTWOOD_COLLEGE;
        Map<Candies, Long> candyPrizeList = new HashMap<>();
        locations.getCandyPriceList().forEach((key, value) -> candyPrizeList.put(key, value.longValue()));
        //When the candy delivery event is triggered at a location
        testModel.setLocationProvider(() -> locations);
        testModel.setUpNextEvent(MyCandyLordEvents.CANDY_DELIVERY);
        testModel.nextDay();
        //Then the prize of each candy should be decreased
        for (Map.Entry<Candies, Long> value :
                locations.getCandyPriceList().entrySet()) {
            long prize = candyPrizeList.get(value.getKey());
            long newPrize = value.getValue();
            assertTrue(prize > newPrize, value.getKey() + " price is now decreased!");
        }
    }

    @Test
    void playerShouldBeAbleToReceiveAnOfferForATrenchcoat() {
        //Given the game is running
        //When the trench coat offer event is triggered
        testModel.setUpNextEvent(MyCandyLordEvents.TRENCHCOAT_OFFER);
        EventAnswer offer = testModel.nextDay();
        //Then the player should get a trenchcoat offer
        assertNotNull(offer, "Returned null!");
        assertTrue(offer.getClass() == TrenchcoatOffer.class, "The event Result has the wrong type! (" + offer.getClass() + " instead of " + TrenchcoatOffer.class + ")!");
    }

    @Test
    void trenchcoatOfferShouldHaveValues() {
        //Given the game is running
        //When the trench coat offer event is triggered
        testModel.setUpNextEvent(MyCandyLordEvents.TRENCHCOAT_OFFER);
        TrenchcoatOffer offer = (TrenchcoatOffer) testModel.nextDay();
        //Then the trenchcoat offer should have values
        assertTrue(offer.getPrice() > 0, "Price is not set!");
        assertTrue(offer.getCapacity() > 0, "Capacity is not set!");
    }

    @Test
    void trenchCoatCapacityIsAlwaysDividableOf5() {
        //Given the game is running
        for (int repetitions = 0; repetitions < 50; repetitions++) {
            //When the trench coat offer event is triggered
            testModel.setUpNextEvent(MyCandyLordEvents.TRENCHCOAT_OFFER);
            TrenchcoatOffer offer = (TrenchcoatOffer) testModel.nextDay();
            //Then the trenchcoat capacity should be dividable by 5
            assertTrue(offer.getCapacity() % 5 == 0, "Capacity (" + offer.getCapacity() + ") is not dividable by 5!");
        }
    }

    @Test
    void trenchcoatPriceShouldBeInDependenceOfCapacity() {
        //Given the game is running
        for (int repetitions = 0; repetitions < 50; repetitions++) {
            //When the trench coat offer event is triggered
            testModel.setUpNextEvent(MyCandyLordEvents.TRENCHCOAT_OFFER);
            TrenchcoatOffer offer = (TrenchcoatOffer) testModel.nextDay();
            //Then the price should be equal to 75*5*(cap/5)*10
            long price = (long) Math.pow(5, (offer.getCapacity() / 5)) * 10 * 75;
            assertEquals(price, offer.getPrice());
        }
    }

    @Test
    void trenchcoatCapacityOfferShouldBeHigherThanCapacity() {
        //Given the player has a capacity
        testModel.setCapacity(40);
        for (int repetitions = 0; repetitions < 50; repetitions++) {
            //When the trenchcoat offer event is triggered
            testModel.setUpNextEvent(MyCandyLordEvents.TRENCHCOAT_OFFER);
            TrenchcoatOffer offer = (TrenchcoatOffer) testModel.nextDay();
            //Then the capacity offer should be higher than it already is
            assertTrue(testModel.getCapacity() < offer.getCapacity(), "The capacity is too small! (" + offer.getCapacity() + ")");
        }
    }

    @Test
    void whenTranchcoatOfferIsAcceptedTheCapacityShouldChange() {
        //Given the player got a trenchcoat offer
        TrenchcoatOffer offer = new TrenchcoatOffer(20, 1000);
        testModel.setCapacity(10);
        testModel.setCash(1000);
        //When he accepts it
        testModel.acceptTranchcoatOffer(offer);
        //Then the capacity should change
        assertEquals(offer.getCapacity(), testModel.getCapacity());
    }

    @Test
    void whenTranchcoatOfferIsAcceptedTheCashShouldBeDecreased() {
        //Given the player got a trenchcoat offer
        TrenchcoatOffer offer = new TrenchcoatOffer(20, 1000);
        testModel.setCash(1000);
        //When he accepts it
        testModel.acceptTranchcoatOffer(offer);
        //Then the capacity should change
        assertEquals(0, testModel.getCash());
    }

    @Test
    void playerShouldGetAnErrorMessageIfHeTriesToBuyATrenchcoatHeDoesntHaveTheMoneyFor() {
        //Given the player got a trenchcoat offer
        TrenchcoatOffer offer = new TrenchcoatOffer(20, 1000);
        testModel.setCash(0);
        //When he accepts it
        //Then he should get an error message
        assertThrows(NotEnoughMoneyException.class, () -> testModel.acceptTranchcoatOffer(offer));
    }

    @Test
    void playerShouldBeAbleToReciveWeaponOffer() {
        //Given the game is running
        //When the weapon offer event is triggered
        testModel.setUpNextEvent(MyCandyLordEvents.WEAPON_OFFER);
        EventAnswer offer = testModel.nextDay();
        //Then the offer should net be null
        assertNotNull(offer);
    }

    @Test
    void weaponOfferShouldHaveAPrice() {
        //Given the game is running
        //When the weapon offer event is triggered
        testModel.setUpNextEvent(MyCandyLordEvents.WEAPON_OFFER);
        WeaponOffer offer = (WeaponOffer) testModel.nextDay();
        //Then the price should not be 0
        assertTrue(offer.getPrice() > 0);
    }

    @Test
    void shouldNotGetAnOfferForFists() {
        //Given the game is running
        //When the weapon offer event is triggered
        testModel.setUpNextEvent(MyCandyLordEvents.WEAPON_OFFER);
        WeaponOffer offer = (WeaponOffer) testModel.nextDay();
        //Then the name should not be fist
        for (int i = 0; i < 50; i++) {
            String name = MyCandyLordWeapon.FIST.name();
            assertNotEquals(name, offer.getName());
        }
    }

    @Test
    void playerShouldBeAbleToAcceptAnWeaponOffer() {
        //Given the player gets an weapon offer
        WeaponOffer offer = new WeaponOffer(MyCandyLordWeapon.STICK);
        //When the player accepts the offer
        testModel.acceptWeaponOffer(offer);
        //Then the player should possess the weapon
        assertEquals(MyCandyLordWeapon.STICK.getClass(), testModel.getWeapon().getClass());
    }

    @Test
    void ifPlayerAcceptsAWeaponOfferHeShouldLooseMoney() {
        //Given the player gets a weapon offer
        WeaponOffer offer = new WeaponOffer(MyCandyLordWeapon.STICK);
        testModel.setCash(MyCandyLordWeapon.STICK.getPrice());
        //When the player accepts the offer
        testModel.acceptWeaponOffer(offer);
        //Then the player should lose money
        assertEquals(0, testModel.getCash());
    }

    @Test
    void playerShouldGetAnErrorMessageIfHeTriesToBuyWeaponWithoutTheNeededMoney() {
        //Given the player gets a weapon offer
        //And he does not have enough money
        WeaponOffer offer = new WeaponOffer(MyCandyLordWeapon.STICK);
        testModel.setCash(0);
        //When the player accepts the offer
        //Then he should get an error
        assertThrows(NotEnoughMoneyException.class, () -> testModel.acceptWeaponOffer(offer));
    }
}
