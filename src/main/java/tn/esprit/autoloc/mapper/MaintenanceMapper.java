package tn.esprit.autoloc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.dto.MaintenanceResponse;

@Mapper(componentModel = "spring")
public interface MaintenanceMapper {

    @Mapping(target = "vehiculeId", source = "vehicule.idVehicule")
    MaintenanceResponse toResponse(Maintenance maintenance);
}
