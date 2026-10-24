package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.MaintenanceRequest;
import tn.esprit.autoloc.repository.MaintenanceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final VehiculeRepository vehiculeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Maintenance> findById(Long id) {
        return maintenanceRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Maintenance> create(MaintenanceRequest request) {
        return vehiculeRepository.findById(request.vehiculeId())
                .map(vehicule -> maintenanceRepository.save(toMaintenance(request, vehicule)));
    }

    @Override
    @Transactional
    public Optional<Maintenance> update(Long id, MaintenanceRequest request) {
        return maintenanceRepository.findById(id)
                .flatMap(existingMaintenance -> vehiculeRepository.findById(request.vehiculeId())
                        .map(vehicule -> updateMaintenance(existingMaintenance, request, vehicule)));
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        if (!maintenanceRepository.existsById(id)) {
            return false;
        }

        maintenanceRepository.deleteById(id);
        return true;
    }

    private Maintenance toMaintenance(MaintenanceRequest request, Vehicule vehicule) {
        Maintenance maintenance = new Maintenance();
        applyRequest(maintenance, request, vehicule);
        return maintenanceRepository.save(maintenance);
    }

    private Maintenance updateMaintenance(
            Maintenance maintenance,
            MaintenanceRequest request,
            Vehicule vehicule) {
        applyRequest(maintenance, request, vehicule);
        return maintenanceRepository.save(maintenance);
    }

    private void applyRequest(
            Maintenance maintenance,
            MaintenanceRequest request,
            Vehicule vehicule) {
        maintenance.setDateDebut(request.dateDebut());
        maintenance.setDateFin(request.dateFin());
        maintenance.setDescription(request.description());
        maintenance.setVehicule(vehicule);
    }
}
