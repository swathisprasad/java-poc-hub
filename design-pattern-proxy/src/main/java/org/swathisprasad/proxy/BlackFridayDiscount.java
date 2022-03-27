package org.swathisprasad.proxy;

public class BlackFridayDiscount implements Discount {

    @Override
    public void getDiscount(final int count) {
        System.out.print("Give 30% discount to customer " + count + "\n");
    }
}
