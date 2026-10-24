package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record MaintenanceRequest(
        @NotNull LocalDate dateDebut,
        LocalDate dateFin,
        @Size(max = 255) String description,
        @NotNull @Positive Long vehiculeId
) {
}
