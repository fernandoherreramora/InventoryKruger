package com.kruger.app.utilitys;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidGenericTest {

    @Test
    void validStrings() {
        ValidGeneric validGeneric= new ValidGeneric();
        boolean result= validGeneric.validStrings("Brando Tomala");
        assertTrue(result);
    }

    @Test
    void validStringsFalse() {
        ValidGeneric validGeneric= new ValidGeneric();
        boolean result= validGeneric.validStrings("Brando_Tomala");
        assertFalse(result);
    }

    @Test
    void validNumber() {
        ValidGeneric validGeneric= new ValidGeneric();
        boolean result= validGeneric.validNumber("0999999999");
        assertTrue(result);
    }

    @Test
    void validNumberFalse() {
        ValidGeneric validGeneric= new ValidGeneric();
        boolean result= validGeneric.validNumber("099999999*");
        assertFalse(result);
    }
}