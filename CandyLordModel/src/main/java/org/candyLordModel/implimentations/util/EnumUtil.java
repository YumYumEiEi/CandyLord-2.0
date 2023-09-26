package org.candyLordModel.implimentations.util;

import org.candyLordModel.implimentations.settings.Candies;
import org.candyLordModel.implimentations.settings.Locations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;

public class EnumUtil {

    public static  ArrayList<String> getAllNames(EnumSet enumeration){
        ArrayList<String> allNames = new ArrayList<>();
        enumeration.forEach((t) -> allNames.add(t.toString()));
        return allNames;
    }

    public static <T extends Enum<T>> T stingToEnum(Class<T> enumClass, String name) {
        name = name.toUpperCase().replace(' ', '_');
        return Enum.valueOf(enumClass, name);
    }
}
