package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.dto.MaintenanceRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MaintenanceService {

    List<Maintenance> findAll();

    Optional<Maintenance> findById(Long id);

    List<Maintenance> findByDateFinBetween(LocalDate debut, LocalDate fin);

    Optional<Maintenance> create(MaintenanceRequest request);

    Optional<Maintenance> update(Long id, MaintenanceRequest request);

    boolean deleteById(Long id);
}
