package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.dto.EmployeResponse;

@Component
public class EmployeMapper {

    public EmployeResponse toResponse(Employe employe) {
        Long agenceId = employe.getAgence() == null ? null : employe.getAgence().getIdAgence();

        return new EmployeResponse(
                employe.getIdEmploye(),
                employe.getNom(),
                employe.getPrenom(),
                employe.getRole(),
                agenceId
        );
    }
}
