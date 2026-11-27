package tn.esprit.autoloc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.VehiculeResponse;

@Mapper(componentModel = "spring")
public interface VehiculeMapper {

    @Mapping(target = "agenceId", source = "agence.idAgence")
    VehiculeResponse toResponse(Vehicule vehicule);
}
