package tn.esprit.autoloc.service;

import org.junit.jupiter.api.Test;
import tn.esprit.autoloc.domain.Vehicule;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VehiculeValidatorTest {

    private final VehiculeValidator validator = new VehiculeValidator();

    @Test
    void accepteUnTarifPositif() {
        Vehicule vehicule = vehiculeAvecTarif("45.50");

        assertDoesNotThrow(() -> validator.valider(vehicule));
    }

    @Test
    void refuseUnTarifNul() {
        assertTarifInvalide("0");
    }

    @Test
    void refuseUnTarifNegatif() {
        assertTarifInvalide("-1");
    }

    @Test
    void refuseUnTarifAbsent() {
        assertTarifInvalide(null);
    }

    private void assertTarifInvalide(String tarif) {
        Vehicule vehicule = vehiculeAvecTarif(tarif);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.valider(vehicule));

        assertEquals("Le tarif doit être positif", exception.getMessage());
    }

    private Vehicule vehiculeAvecTarif(String tarif) {
        Vehicule vehicule = new Vehicule();
        vehicule.setTarifJournalier(tarif == null ? null : new BigDecimal(tarif));
        return vehicule;
    }
}
