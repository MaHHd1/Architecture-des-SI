package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.dto.MaintenanceResponse;

@Component
public class MaintenanceMapper {

    public MaintenanceResponse toResponse(Maintenance maintenance) {
        Long vehiculeId = maintenance.getVehicule() == null
                ? null
                : maintenance.getVehicule().getIdVehicule();

        return new MaintenanceResponse(
                maintenance.getIdMaintenance(),
                maintenance.getDateDebut(),
                maintenance.getDateFin(),
                maintenance.getDescription(),
                vehiculeId
        );
    }
}
