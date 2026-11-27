package tn.esprit.autoloc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.dto.ReservationResponse;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mappings({
            @Mapping(target = "clientId", source = "client.idClient"),
            @Mapping(target = "vehiculeId", source = "vehicule.idVehicule"),
            @Mapping(target = "contratId", source = "contrat.idContrat")
    })
    ReservationResponse toResponse(Reservation reservation);
}
