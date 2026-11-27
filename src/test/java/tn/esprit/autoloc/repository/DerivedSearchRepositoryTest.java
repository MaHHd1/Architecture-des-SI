package tn.esprit.autoloc.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.AgenceService;
import tn.esprit.autoloc.service.JournalisationService;
import tn.esprit.autoloc.service.MaintenanceServiceImpl;
import tn.esprit.autoloc.service.ReservationService;
import tn.esprit.autoloc.service.VehiculeService;
import tn.esprit.autoloc.service.VehiculeValidator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import({
        AgenceService.class,
        JournalisationService.class,
        MaintenanceServiceImpl.class,
        ReservationService.class,
        VehiculeService.class,
        VehiculeValidator.class
})
class DerivedSearchRepositoryTest {

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private AgenceRepository agenceRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private VehiculeService vehiculeService;

    @Autowired
    private AgenceService agenceService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private MaintenanceServiceImpl maintenanceService;

    @BeforeEach
    void setUp() {
        Agence tunis = agenceRepository.save(agence("Auto Rent", "Tunis"));
        agenceRepository.save(agence("Nord Location", "Bizerte"));

        Vehicule renault = vehiculeRepository.save(vehicule(
                "111TUN111", "Renault", "Clio", CategorieVehicule.CITADINE, "45", StatutVehicule.DISPONIBLE, tunis));
        Vehicule peugeot = vehiculeRepository.save(vehicule(
                "222TUN222", "Peugeot", "3008", CategorieVehicule.SUV, "100", StatutVehicule.DISPONIBLE, tunis));
        vehiculeRepository.save(vehicule(
                "333TUN333", "BMW", "X5", CategorieVehicule.SUV, "150", StatutVehicule.LOUE, tunis));
        vehiculeRepository.save(vehicule(
                "444TUN444", "Mercedes", "Classe C", CategorieVehicule.BERLINE, "80", StatutVehicule.DISPONIBLE, tunis));
        vehiculeRepository.save(vehicule(
                "555TUN555", "Toyota", "Corolla", CategorieVehicule.BERLINE, "75", StatutVehicule.MAINTENANCE, tunis));

        reservationRepository.save(reservation(LocalDate.of(2026, 1, 5), LocalDate.of(2026, 1, 10), renault));
        reservationRepository.save(reservation(LocalDate.of(2026, 1, 15), LocalDate.of(2026, 1, 22), peugeot));

        maintenanceRepository.save(maintenance(LocalDate.of(2026, 1, 5), LocalDate.of(2026, 1, 15), renault));
        maintenanceRepository.save(maintenance(LocalDate.of(2026, 2, 1), LocalDate.of(2026, 2, 10), peugeot));
    }

    @Test
    void findByMarque() {
        assertEquals(List.of("Renault"), marques(vehiculeService.rechercherParMarque("Renault")));
    }

    @Test
    void findByCategorie() {
        assertEquals(List.of("BMW", "Peugeot"), marquesTriees(vehiculeService.rechercherParCategorie(CategorieVehicule.SUV)));
    }

    @Test
    void findByStatut() {
        assertEquals(List.of("Mercedes", "Peugeot", "Renault"),
                marquesTriees(vehiculeService.rechercherDisponibles()));
    }

    @Test
    void findByVille() {
        assertEquals(List.of("Auto Rent"), noms(agenceService.rechercherParVille("Tunis")));
    }

    @Test
    void findByMarqueContaining() {
        assertEquals(List.of("Toyota"), marques(vehiculeService.rechercherParMarqueContenant("yo")));
    }

    @Test
    void findByModeleStartingWith() {
        assertEquals(List.of("Mercedes"), marques(vehiculeService.rechercherParModeleCommencantPar("Clas")));
    }

    @Test
    void findByNomEndingWith() {
        assertEquals(List.of("Auto Rent"), noms(agenceService.rechercherParNomTerminePar("Rent")));
    }

    @Test
    void findByTarifJournalierGreaterThan() {
        assertEquals(List.of("BMW"),
                marques(vehiculeService.rechercherParTarifSuperieurA(new BigDecimal("100"))));
    }

    @Test
    void findByTarifJournalierLessThan() {
        assertEquals(List.of("Renault"),
                marques(vehiculeService.rechercherParTarifInferieurA(new BigDecimal("50"))));
    }

    @Test
    void findByTarifJournalierGreaterThanEqual() {
        assertEquals(List.of("BMW", "Mercedes", "Peugeot"),
                marquesTriees(vehiculeService.rechercherParTarifSuperieurOuEgalA(new BigDecimal("80"))));
    }

    @Test
    void findByMarqueAndStatut() {
        assertEquals(List.of("Renault"),
                marques(vehiculeService.rechercherDisponiblesParMarque("Renault")));
    }

    @Test
    void findByCategorieIn() {
        assertEquals(List.of("BMW", "Mercedes", "Peugeot", "Toyota"),
                marquesTriees(vehiculeService.rechercherParCategories(
                        List.of(CategorieVehicule.SUV, CategorieVehicule.BERLINE))));
    }

    @Test
    void findByMarqueAndTarifJournalierLessThan() {
        assertEquals(List.of("Toyota"),
                marques(vehiculeService.rechercherParMarqueEtTarifInferieurA("Toyota", new BigDecimal("100"))));
    }

    @Test
    void findByDateDebutAfter() {
        assertEquals(List.of(LocalDate.of(2026, 1, 15)),
                reservationService.rechercherParDateDebutApres(LocalDate.of(2026, 1, 10)).stream()
                        .map(Reservation::getDateDebut)
                        .toList());
    }

    @Test
    void findByDateFinBefore() {
        assertEquals(List.of(LocalDate.of(2026, 1, 10)),
                reservationService.rechercherParDateFinAvant(LocalDate.of(2026, 1, 20)).stream()
                        .map(Reservation::getDateFin)
                        .toList());
    }

    @Test
    void findByDateFinBetween() {
        assertEquals(List.of(LocalDate.of(2026, 1, 15)),
                maintenanceService.findByDateFinBetween(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 31)).stream()
                        .map(Maintenance::getDateFin)
                        .toList());
    }

    @Test
    void findByStatutOrderByTarifJournalierAsc() {
        assertEquals(List.of("Renault", "Mercedes", "Peugeot"),
                marques(vehiculeService.rechercherDisponiblesParTarifCroissant()));
    }

    @Test
    void findAllByOrderByTarifJournalierDesc() {
        assertEquals(List.of("BMW", "Peugeot", "Mercedes", "Toyota", "Renault"),
                marques(vehiculeService.rechercherTousParTarifDecroissant()));
    }

    private Agence agence(String nom, String ville) {
        Agence agence = new Agence();
        agence.setNom(nom);
        agence.setVille(ville);
        agence.setAdresse("1 rue de la République");
        agence.setTelephone("70123456");
        return agence;
    }

    private Vehicule vehicule(
            String immatriculation,
            String marque,
            String modele,
            CategorieVehicule categorie,
            String tarifJournalier,
            StatutVehicule statut,
            Agence agence) {
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation(immatriculation);
        vehicule.setMarque(marque);
        vehicule.setModele(modele);
        vehicule.setCategorie(categorie);
        vehicule.setTarifJournalier(new BigDecimal(tarifJournalier));
        vehicule.setStatut(statut);
        vehicule.setAgence(agence);
        return vehicule;
    }

    private Reservation reservation(LocalDate dateDebut, LocalDate dateFin, Vehicule vehicule) {
        Reservation reservation = new Reservation();
        reservation.setDateDebut(dateDebut);
        reservation.setDateFin(dateFin);
        reservation.setStatut(StatutReservation.CONFIRMEE);
        reservation.setVehicule(vehicule);
        return reservation;
    }

    private Maintenance maintenance(LocalDate dateDebut, LocalDate dateFin, Vehicule vehicule) {
        Maintenance maintenance = new Maintenance();
        maintenance.setDateDebut(dateDebut);
        maintenance.setDateFin(dateFin);
        maintenance.setDescription("Révision");
        maintenance.setVehicule(vehicule);
        return maintenance;
    }

    private List<String> marques(List<Vehicule> vehicules) {
        return vehicules.stream().map(Vehicule::getMarque).toList();
    }

    private List<String> marquesTriees(List<Vehicule> vehicules) {
        return marques(vehicules).stream().sorted().toList();
    }

    private List<String> noms(List<Agence> agences) {
        return agences.stream().map(Agence::getNom).toList();
    }
}
