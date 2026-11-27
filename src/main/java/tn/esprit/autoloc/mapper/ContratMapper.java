package tn.esprit.autoloc.mapper;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.dto.ContratResponse;

@Mapper(componentModel = "spring")
public interface ContratMapper {

    ContratResponse toResponse(Contrat contrat);
}
