package org.candyLordModel.implimentations.myCandyLordModel;

public enum MyCandyLordWeapon implements Weapon {
    FIST{
        @Override
        public int getDMG() {
            return 10;
        }

        @Override
        public int getAccuracy() {
            return 85;
        }

        @Override
        public long getPrice() {
            return 0;
        }
    },
    STICK{
        @Override
        public int getDMG() {
            return 30;
        }

        @Override
        public int getAccuracy() {
            return 75;
        }

        @Override
        public long getPrice() {
            return 100;
        }
    }

}
