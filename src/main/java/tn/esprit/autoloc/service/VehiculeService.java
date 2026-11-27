package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.math.BigDecimal;
import java.util.List;

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

    @Transactional(readOnly = true)
    public List<Vehicule> rechercherParMarque(String marque) {
        return repository.findByMarque(marque);
    }

    @Transactional(readOnly = true)
    public List<Vehicule> rechercherParCategorie(CategorieVehicule categorie) {
        return repository.findByCategorie(categorie);
    }

    @Transactional(readOnly = true)
    public List<Vehicule> rechercherDisponibles() {
        return repository.findByStatut(StatutVehicule.DISPONIBLE);
    }

    @Transactional(readOnly = true)
    public List<Vehicule> rechercherParMarqueContenant(String marque) {
        return repository.findByMarqueContaining(marque);
    }

    @Transactional(readOnly = true)
    public List<Vehicule> rechercherParModeleCommencantPar(String modele) {
        return repository.findByModeleStartingWith(modele);
    }

    @Transactional(readOnly = true)
    public List<Vehicule> rechercherParTarifSuperieurA(BigDecimal tarifJournalier) {
        return repository.findByTarifJournalierGreaterThan(tarifJournalier);
    }

    @Transactional(readOnly = true)
    public List<Vehicule> rechercherParTarifInferieurA(BigDecimal tarifJournalier) {
        return repository.findByTarifJournalierLessThan(tarifJournalier);
    }

    @Transactional(readOnly = true)
    public List<Vehicule> rechercherParTarifSuperieurOuEgalA(BigDecimal tarifJournalier) {
        return repository.findByTarifJournalierGreaterThanEqual(tarifJournalier);
    }

    @Transactional(readOnly = true)
    public List<Vehicule> rechercherDisponiblesParMarque(String marque) {
        return repository.findByMarqueAndStatut(marque, StatutVehicule.DISPONIBLE);
    }

    @Transactional(readOnly = true)
    public List<Vehicule> rechercherParCategories(List<CategorieVehicule> categories) {
        return repository.findByCategorieIn(categories);
    }

    @Transactional(readOnly = true)
    public List<Vehicule> rechercherParMarqueEtTarifInferieurA(
            String marque,
            BigDecimal tarifJournalier) {
        return repository.findByMarqueAndTarifJournalierLessThan(marque, tarifJournalier);
    }

    @Transactional(readOnly = true)
    public List<Vehicule> rechercherDisponiblesParTarifCroissant() {
        return repository.findByStatutOrderByTarifJournalierAsc(StatutVehicule.DISPONIBLE);
    }

    @Transactional(readOnly = true)
    public List<Vehicule> rechercherTousParTarifDecroissant() {
        return repository.findAllByOrderByTarifJournalierDesc();
    }
}
