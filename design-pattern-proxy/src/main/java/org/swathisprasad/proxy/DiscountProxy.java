package org.swathisprasad.proxy;

public class DiscountProxy implements Discount {

    private static final int FIRST_N_CUSTOMERS = 20;

    private final Discount discount;

    private int numOfCustomers;

    public DiscountProxy(final Discount discount) {
        this.discount = discount;
    }

    @Override
    public void getDiscount(final int count) {
        if (numOfCustomers < FIRST_N_CUSTOMERS) {
            discount.getDiscount(count);
            numOfCustomers++;
        } else {
            System.out.print("Limit exceeded. Not allowed to get discount!");
        }
    }
}
