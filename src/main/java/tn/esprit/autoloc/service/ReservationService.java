package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Reservation> rechercherParDateDebutApres(LocalDate date) {
        return repository.findByDateDebutAfter(date);
    }

    @Transactional(readOnly = true)
    public List<Reservation> rechercherParDateFinAvant(LocalDate date) {
        return repository.findByDateFinBefore(date);
    }
}
