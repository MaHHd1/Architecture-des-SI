package tn.esprit.autoloc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.dto.PaiementResponse;

@Mapper(componentModel = "spring")
public interface PaiementMapper {

    @Mapping(target = "contratId", source = "contrat.idContrat")
    PaiementResponse toResponse(Paiement paiement);
}
