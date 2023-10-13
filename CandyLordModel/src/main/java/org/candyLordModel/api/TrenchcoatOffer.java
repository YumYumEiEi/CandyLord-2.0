package org.candyLordModel.api;

public class TrenchcoatOffer implements EventAnswer {
    private final int capacity;
    private final long price;

    public TrenchcoatOffer(int capacity, long price) {
        this.capacity = capacity;
        this.price = price;
    }

    public long getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }
}
