package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.AgenceRepository;

import java.util.List;

@Service
public class AgenceService {

    private final AgenceRepository repository;

    public AgenceService(AgenceRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Agence> rechercherParVille(String ville) {
        return repository.findByVille(ville);
    }

    @Transactional(readOnly = true)
    public List<Agence> rechercherParNomTerminePar(String nom) {
        return repository.findByNomEndingWith(nom);
    }
}
