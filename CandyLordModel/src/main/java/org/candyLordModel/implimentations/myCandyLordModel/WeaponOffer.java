package org.candyLordModel.implimentations.myCandyLordModel;

import org.candyLordModel.api.EventAnswer;

public class WeaponOffer implements EventAnswer {
    private final MyCandyLordWeapon weapon;

    public WeaponOffer(MyCandyLordWeapon weapon) {
        this.weapon = weapon;
    }

    public long getPrice() {
        return weapon.getPrice();
    }

    public String getName() {
        return weapon.name();
    }
}
