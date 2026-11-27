package tn.esprit.autoloc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.dto.EmployeResponse;

@Mapper(componentModel = "spring")
public interface EmployeMapper {

    @Mapping(target = "agenceId", source = "agence.idAgence")
    EmployeResponse toResponse(Employe employe);
}
