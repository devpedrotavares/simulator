package com.payment.simulator.services.util;

public class ValidatorUtil {

    // Returns true if the object is null reference or if it's empty/blank string
    public static boolean isNull(Object o) {
        if(o == null) {
            return true;
        }

        if(o instanceof String) {
            String s = (String) o;

            return s.isBlank() || s.isEmpty();
        }

        return false;
    }
}
