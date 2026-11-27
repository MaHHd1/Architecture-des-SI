package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;

import java.math.BigDecimal;
import java.util.List;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {

    List<Vehicule> findByMarque(String marque);

    List<Vehicule> findByCategorie(CategorieVehicule categorie);

    List<Vehicule> findByStatut(StatutVehicule statut);

    List<Vehicule> findByMarqueContaining(String marque);

    List<Vehicule> findByModeleStartingWith(String modele);

    List<Vehicule> findByTarifJournalierGreaterThan(BigDecimal tarifJournalier);

    List<Vehicule> findByTarifJournalierLessThan(BigDecimal tarifJournalier);

    List<Vehicule> findByTarifJournalierGreaterThanEqual(BigDecimal tarifJournalier);

    List<Vehicule> findByMarqueAndStatut(String marque, StatutVehicule statut);

    List<Vehicule> findByCategorieIn(List<CategorieVehicule> categories);

    List<Vehicule> findByMarqueAndTarifJournalierLessThan(String marque, BigDecimal tarifJournalier);

    List<Vehicule> findByStatutOrderByTarifJournalierAsc(StatutVehicule statut);

    List<Vehicule> findAllByOrderByTarifJournalierDesc();
}
