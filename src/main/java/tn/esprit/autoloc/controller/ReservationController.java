package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.dto.ReservationResponse;
import tn.esprit.autoloc.mapper.ReservationMapper;
import tn.esprit.autoloc.service.ReservationService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    public ReservationController(ReservationService reservationService, ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @GetMapping("/date-debut/apres")
    @Operation(summary = "Rechercher les réservations commençant après une date")
    public ResponseEntity<List<ReservationResponse>> rechercherParDateDebutApres(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return repondre(reservationService.rechercherParDateDebutApres(date));
    }

    @GetMapping("/date-fin/avant")
    @Operation(summary = "Rechercher les réservations se terminant avant une date")
    public ResponseEntity<List<ReservationResponse>> rechercherParDateFinAvant(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return repondre(reservationService.rechercherParDateFinAvant(date));
    }

    private ResponseEntity<List<ReservationResponse>> repondre(List<Reservation> reservations) {
        return ResponseEntity.ok(reservations.stream()
                .map(reservationMapper::toResponse)
                .toList());
    }
}
