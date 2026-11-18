package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;


@Service
public class VehiculeService {

    private final VehiculeRepository repository;
    private final VehiculeValidator validator;
    private final JournalisationService journalisationService;

    public VehiculeService(
            VehiculeRepository repository,
            VehiculeValidator validator,
            JournalisationService journalisationService) {
        this.repository = repository;
        this.validator = validator;
        this.journalisationService = journalisationService;
    }

    public Vehicule ajouter(Vehicule vehicule) {
        validator.valider(vehicule);
        journalisationService.journaliserAjout();
        return repository.save(vehicule);
    }
}
