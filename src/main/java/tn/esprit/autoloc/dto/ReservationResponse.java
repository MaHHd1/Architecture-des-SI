package tn.esprit.autoloc.dto;

import tn.esprit.autoloc.domain.StatutReservation;

import java.time.LocalDate;

public record ReservationResponse(
        Long idReservation,
        LocalDate dateDebut,
        LocalDate dateFin,
        StatutReservation statut
) {
}
