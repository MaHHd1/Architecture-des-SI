package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;


@Service
public class TarificationService {

    private static final String JOURS_POSITIFS_MESSAGE = "Le nombre de jours doit être positif";

    private final Map<String, StrategieTarification> strategies;

    public TarificationService(Map<String, StrategieTarification> strategies) {
        this.strategies = Map.copyOf(strategies);
    }

    public double calculerTarif(String typeTarif, double tarifJournalier, int jours) {
        if (jours <= 0) {
            throw new IllegalArgumentException(JOURS_POSITIFS_MESSAGE);
        }

        StrategieTarification strategie = typeTarif == null ? null : strategies.get(typeTarif);
        if (strategie == null) {
            String typesDisponibles = strategies.keySet().stream()
                    .sorted()
                    .collect(Collectors.joining(", "));
            throw new IllegalArgumentException(
                    "Type de tarification inconnu : " + typeTarif
                            + ". Types disponibles : " + typesDisponibles);
        }

        return strategie.calculer(tarifJournalier, jours);
    }

    public double calculerTarif(double tarifJournalier, int jours, String typeTarif) {
        return calculerTarif(typeTarif, tarifJournalier, jours);
    }
}
