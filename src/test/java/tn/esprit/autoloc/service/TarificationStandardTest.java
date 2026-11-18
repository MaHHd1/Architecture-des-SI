package tn.esprit.autoloc.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarificationStandardTest {

    @Test
    void appliqueLeTarifJournalierSansRemise() {
        assertEquals(300.0d, new TarificationStandard().calculer(100.0d, 3));
    }
}
