package org.swathisprasad.proxy;

public class Main {

    public static void main(String[] args) {
        final DiscountProxy discountProxy = new DiscountProxy(new BlackFridayDiscount());
        for (int i = 0; i <= 20; i++) {
            discountProxy.getDiscount(i);
        }
    }
}
