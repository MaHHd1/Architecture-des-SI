package tn.esprit.autoloc.dto;

import java.time.LocalDate;

public record MaintenanceResponse(
        Long idMaintenance,
        LocalDate dateDebut,
        LocalDate dateFin,
        String description,
        Long vehiculeId
) {
}
