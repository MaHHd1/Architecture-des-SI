package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;
import java.util.Optional;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    @Override
    @EntityGraph(attributePaths = "vehicule")
    List<Maintenance> findAll();

    @Override
    @EntityGraph(attributePaths = "vehicule")
    Optional<Maintenance> findById(Long id);
}
