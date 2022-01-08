package org.swathisprasad.sealed;

public class SealedClassMain {

    public static void main(String[] args){
        Sedan sedan =  new Sedan();
        System.out.println("Sedan speed  ----> " + sedan.getSpeed());

        HatchBack hatchBack =  new HatchBack();
        System.out.println("HatchBack speed  ----> " + hatchBack.getSpeed());

        Coupe coupe =  new Coupe();
        System.out.println("Coupe speed  ----> " + coupe.getSpeed());

        Van van =  new Van();
        System.out.println("Van speed  ----> " + van.getSpeed());
    }
}
