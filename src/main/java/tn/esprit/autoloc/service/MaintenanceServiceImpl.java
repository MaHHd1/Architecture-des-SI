package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.MaintenanceRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    @Override
    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Optional<Maintenance> findById(Long id) {
        return maintenanceRepository.findById(id);
    }

    @Override
    public Maintenance create(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Optional<Maintenance> update(Long id, Maintenance maintenance) {
        return maintenanceRepository.findById(id)
                .map(existingMaintenance -> {
                    existingMaintenance.setDateDebut(maintenance.getDateDebut());
                    existingMaintenance.setDateFin(maintenance.getDateFin());
                    existingMaintenance.setDescription(maintenance.getDescription());
                    existingMaintenance.setVehicule(maintenance.getVehicule());
                    return maintenanceRepository.save(existingMaintenance);
                });
    }

    @Override
    public boolean deleteById(Long id) {
        if (!maintenanceRepository.existsById(id)) {
            return false;
        }

        maintenanceRepository.deleteById(id);
        return true;
    }
}
