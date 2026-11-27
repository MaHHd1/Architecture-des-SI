package tn.esprit.autoloc.mapper;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.dto.AgenceResponse;

@Mapper(componentModel = "spring")
public interface AgenceMapper {

    AgenceResponse toResponse(Agence agence);
}
