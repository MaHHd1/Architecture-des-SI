package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContratRequest(
        @NotNull LocalDate dateSignature,
        @NotNull @PositiveOrZero BigDecimal montantTotal,
        boolean valide
) {
}
