package org.candyLordModel.implimentations.myCandyLordModel;

import java.util.*;

public class RandomWeaponProvider implements WeaponProvider {
    @Override
    public MyCandyLordWeapon getWeapon(){
        do{
            List<MyCandyLordWeapon> list = Arrays.asList(MyCandyLordWeapon.values());
            Collections.shuffle(list);
            MyCandyLordWeapon weapon = list.get(0);
            if(!weapon.name().equals(MyCandyLordWeapon.FIST.name())){
                return list.get(0);
            }
        }while (true);
    }
}
