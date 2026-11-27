package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.domain.Agence;

import java.util.List;

public interface AgenceRepository extends JpaRepository<Agence, Long> {

    List<Agence> findByVille(String ville);

    List<Agence> findByNomEndingWith(String nom);
}
