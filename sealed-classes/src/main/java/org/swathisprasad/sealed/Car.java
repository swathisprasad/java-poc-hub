package org.swathisprasad.sealed;

public abstract sealed class Car permits Coupe, HatchBack, Sedan {

    abstract int getSpeed();
}
