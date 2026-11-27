package tn.esprit.autoloc.mapper;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.dto.EquipementResponse;

@Mapper(componentModel = "spring")
public interface EquipementMapper {

    EquipementResponse toResponse(Equipement equipement);
}
