package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.dto.PaiementResponse;

@Component
public class PaiementMapper {

    public PaiementResponse toResponse(Paiement paiement) {
        Long contratId = paiement.getContrat() == null ? null : paiement.getContrat().getIdContrat();

        return new PaiementResponse(
                paiement.getIdPaiement(),
                paiement.getMontant(),
                paiement.getDatePaiement(),
                paiement.getModePaiement(),
                contratId
        );
    }
}
