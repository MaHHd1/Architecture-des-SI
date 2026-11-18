package tn.esprit.autoloc.service;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Vehicule;

import java.math.BigDecimal;


@Component
public class VehiculeValidator {

    private static final String TARIF_POSITIF_MESSAGE = "Le tarif doit être positif";

    public void valider(Vehicule vehicule) {
        if (vehicule == null
                || vehicule.getTarifJournalier() == null
                || vehicule.getTarifJournalier().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(TARIF_POSITIF_MESSAGE);
        }
    }
}
