package tn.esprit.autoloc.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarificationWeekendTest {

    @Test
    void appliqueUneRemiseDeDixPourCent() {
        assertEquals(270.0d, new TarificationWeekend().calculer(100.0d, 3));
    }
}
