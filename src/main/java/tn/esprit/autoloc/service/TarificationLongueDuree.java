package tn.esprit.autoloc.service;

import org.springframework.stereotype.Component;

@Component("longueDuree")
public class TarificationLongueDuree implements StrategieTarification {

    @Override
    public double calculer(double tarifJournalier, int jours) {
        return tarifJournalier * jours * 0.80d;
    }
}
