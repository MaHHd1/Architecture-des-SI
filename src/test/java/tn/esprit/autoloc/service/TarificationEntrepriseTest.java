package tn.esprit.autoloc.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarificationEntrepriseTest {

    @Test
    void appliqueUneRemiseDeQuinzePourCent() {
        assertEquals(255.0d, new TarificationEntreprise().calculer(100.0d, 3));
    }
}
