package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.EmployeRepository;
import tn.esprit.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AffectationServiceImpl implements AffectationService {

    private final EmployeRepository employeRepository;
    private final AgenceRepository agenceRepository;
    private final VehiculeRepository vehiculeRepository;
    private final EquipementRepository equipementRepository;

    @Override
    @Transactional
    public Employe desaffecterEmploye(Long idEmploye) {
        Employe employe = getEmploye(idEmploye);
        detachEmployeFromAgence(employe);
        return employeRepository.save(employe);
    }

    @Override
    @Transactional
    public Employe affecterEmploye(Long idEmploye, Long idAgence) {
        Employe employe = getEmploye(idEmploye);
        Agence agence = getAgence(idAgence);

        // Un transfert passe par la désaffectation de l'ancienne agence.
        detachEmployeFromAgence(employe);
        employe.setAgence(agence);
        addIfAbsent(agence.getEmployes(), employe, Employe::getIdEmploye);

        return employeRepository.save(employe);
    }

    @Override
    @Transactional
    public Vehicule affecterVehicule(Long idVehicule, Long idAgence) {
        Vehicule vehicule = getVehicule(idVehicule);
        Agence agence = getAgence(idAgence);

        detachVehiculeFromAgence(vehicule);
        vehicule.setAgence(agence);
        addIfAbsent(agence.getVehicules(), vehicule, Vehicule::getIdVehicule);

        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = getVehicule(idVehicule);
        Equipement equipement = getEquipement(idEquipement);

        addIfAbsent(vehicule.getEquipements(), equipement, Equipement::getIdEquipement);
        addIfAbsent(equipement.getVehicules(), vehicule, Vehicule::getIdVehicule);

        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = getVehicule(idVehicule);
        Equipement equipement = getEquipement(idEquipement);

        removeById(vehicule.getEquipements(), equipement, Equipement::getIdEquipement);
        removeById(equipement.getVehicules(), vehicule, Vehicule::getIdVehicule);

        return vehiculeRepository.save(vehicule);
    }

    private Employe getEmploye(Long idEmploye) {
        return employeRepository.findById(idEmploye)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Employé introuvable : " + idEmploye));
    }

    private Agence getAgence(Long idAgence) {
        return agenceRepository.findById(idAgence)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Agence introuvable : " + idAgence));
    }

    private Vehicule getVehicule(Long idVehicule) {
        return vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Véhicule introuvable : " + idVehicule));
    }

    private Equipement getEquipement(Long idEquipement) {
        return equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Équipement introuvable : " + idEquipement));
    }

    private void detachEmployeFromAgence(Employe employe) {
        Agence ancienneAgence = employe.getAgence();
        if (ancienneAgence == null) {
            return;
        }

        removeById(
                ancienneAgence.getEmployes(),
                employe,
                Employe::getIdEmploye);
        employe.setAgence(null);
    }

    private void detachVehiculeFromAgence(Vehicule vehicule) {
        Agence ancienneAgence = vehicule.getAgence();
        if (ancienneAgence == null) {
            return;
        }

        removeById(
                ancienneAgence.getVehicules(),
                vehicule,
                Vehicule::getIdVehicule);
        vehicule.setAgence(null);
    }

    private <T, I> void addIfAbsent(
            List<T> target,
            T value,
            java.util.function.Function<T, I> idGetter) {
        boolean alreadyPresent = target.stream()
                .anyMatch(item -> item == value
                        || Objects.equals(idGetter.apply(item), idGetter.apply(value)));
        if (!alreadyPresent) {
            target.add(value);
        }
    }

    private <T, I> void removeById(
            List<T> target,
            T value,
            java.util.function.Function<T, I> idGetter) {
        target.removeIf(item -> item == value
                || Objects.equals(idGetter.apply(item), idGetter.apply(value)));
    }
}
