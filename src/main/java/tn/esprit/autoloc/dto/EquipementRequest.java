package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EquipementRequest(
        @NotBlank @Size(max = 50) String libelle
) {
}
