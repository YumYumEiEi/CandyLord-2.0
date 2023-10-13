package org.candyLordModel.implimentations.myCandyLordModel;

import org.candyLordModel.implimentations.settings.DefaultSettings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyCandyLordModelCombatTest {

    MyCandyLordModel testModel;
    @BeforeEach
    void setUp(){
        testModel = MyCandyLordModel.getTestModel();
    }

    @Test
    void playerShouldHaveAWeapon(){
        //Given the game is set up
        //When i want to get the player weapon
        MyCandyLordWeapon myCandyLordWeapon = testModel.getWeapon();
        //Then the weapon should net be null
        assertNotNull(myCandyLordWeapon);
    }

    @Test
    void playerShouldStartWithDefaultWeapon(){
        //Given the game is set up
        //When i want to get the player weapon
        MyCandyLordWeapon myCandyLordWeapon = testModel.getWeapon();
        //Then the weapon should be the weapon from the settings
        assertEquals(DefaultSettings.STRAT_MY_CANDY_LORD_WEAPON.getClass(), myCandyLordWeapon.getClass());
    }

    @Test
    void aWeaponShouldHaveAnDMGAmount(){
        //Given the player has a weapon
        testModel.setWeapon(MyCandyLordWeapon.FIST);
        //When I want to know the DMG value
        int dmg = testModel.getWeaponDMG();
        //Then the value should be higher than 0
        assertTrue(dmg > 0);
    }

    @Test
    void aWeaponShouldHaveAnAccuracy(){
        //Given the player has a weapon
        testModel.setWeapon(MyCandyLordWeapon.FIST);
        //When I want to know the accuracy
        int accuracy = testModel.getWeaponAccuracy();
        //Then the value should be higher than 0
        assertTrue(accuracy > 0);
    }

    @Test
    void ifThePlayerIsHitWithAWeaponHeShouldLooseHitpoints(){
        //Given a player is at full health
        testModel.setHealth(100);
        //When he is hit with an attack
        int dmgValue = MyCandyLordWeapon.FIST.getDMG();
        testModel.getHit(dmgValue);
        //Then the player should loos that much hp
        int expectedHealth = 100 - dmgValue;
        assertEquals(expectedHealth, testModel.getHealth());
    }

    @Test
    void ifThePlayerWasRunningAwayFromAFightHeLooses1StatusPoint(){
        //Given the player was in a battle
        testModel.setStatusPoints(1);
        //When he flees
        testModel.fleeFromBattle();
        //Then he should have lost a status point
        assertEquals(0, testModel.getStatusPoints());
    }
}
