package tn.esprit.autoloc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TarificationServiceTest {

    private TarificationService service;

    @BeforeEach
    void setUp() {
        service = new TarificationService(Map.of(
                "standard", new TarificationStandard(),
                "weekend", new TarificationWeekend(),
                "longueDuree", new TarificationLongueDuree(),
                "entreprise", new TarificationEntreprise()));
    }

    @Test
    void selectionneLaStrategieDemandee() {
        assertEquals(300.0d, service.calculerTarif("standard", 100.0d, 3));
        assertEquals(270.0d, service.calculerTarif("weekend", 100.0d, 3));
        assertEquals(240.0d, service.calculerTarif("longueDuree", 100.0d, 3));
        assertEquals(255.0d, service.calculerTarif("entreprise", 100.0d, 3));
    }

    @Test
    void refuseUnNombreDeJoursInvalide() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.calculerTarif("standard", 100.0d, 0));

        assertEquals("Le nombre de jours doit être positif", exception.getMessage());
    }

    @Test
    void refuseUnNombreDeJoursNegatif() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.calculerTarif("standard", 100.0d, -1));

        assertEquals("Le nombre de jours doit être positif", exception.getMessage());
    }

    @Test
    void signaleUnTypeDeTarificationInconnuAvecLesTypesDisponibles() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.calculerTarif("premium", 100.0d, 3));

        assertTrue(exception.getMessage().contains("premium"));
        assertTrue(exception.getMessage().contains("standard"));
        assertTrue(exception.getMessage().contains("weekend"));
    }
}
