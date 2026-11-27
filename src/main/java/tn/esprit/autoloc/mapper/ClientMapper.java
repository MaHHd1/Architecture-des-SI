package tn.esprit.autoloc.mapper;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.dto.ClientResponse;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientResponse toResponse(Client client);
}
