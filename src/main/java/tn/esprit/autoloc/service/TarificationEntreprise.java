package tn.esprit.autoloc.service;

import org.springframework.stereotype.Component;

@Component("entreprise")
public class TarificationEntreprise implements StrategieTarification {

    @Override
    public double calculer(double tarifJournalier, int jours) {
        return tarifJournalier * jours * 0.85d;
    }
}
