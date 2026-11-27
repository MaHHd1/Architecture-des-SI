package tn.esprit.autoloc.dto;

import tn.esprit.autoloc.domain.RoleEmployee;

public record EmployeResponse(
        Long idEmploye,
        String nom,
        String prenom,
        RoleEmployee role,
        Long agenceId
) {
}
