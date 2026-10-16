package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;
import java.util.Optional;

public interface MaintenanceService {

    List<Maintenance> findAll();

    Optional<Maintenance> findById(Long id);

    Maintenance create(Maintenance maintenance);

    Optional<Maintenance> update(Long id, Maintenance maintenance);

    boolean deleteById(Long id);
}
