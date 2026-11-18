package tn.esprit.autoloc.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarificationLongueDureeTest {

    @Test
    void appliqueUneRemiseDeVingtPourCent() {
        assertEquals(240.0d, new TarificationLongueDuree().calculer(100.0d, 3));
    }
}
