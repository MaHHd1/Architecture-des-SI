package tn.esprit.autoloc.service;

import org.springframework.stereotype.Component;

@Component("standard")
public class TarificationStandard implements StrategieTarification {

    @Override
    public double calculer(double tarifJournalier, int jours) {
        return tarifJournalier * jours;
    }
}
