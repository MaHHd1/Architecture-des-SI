package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.dto.AgenceResponse;
import tn.esprit.autoloc.mapper.AgenceMapper;
import tn.esprit.autoloc.service.AgenceService;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
public class AgenceController {

    private final AgenceService agenceService;
    private final AgenceMapper agenceMapper;

    public AgenceController(AgenceService agenceService, AgenceMapper agenceMapper) {
        this.agenceService = agenceService;
        this.agenceMapper = agenceMapper;
    }

    @GetMapping("/ville/{ville}")
    @Operation(summary = "Rechercher les agences d'une ville")
    public ResponseEntity<List<AgenceResponse>> rechercherParVille(
            @PathVariable("ville") String ville) {
        return repondre(agenceService.rechercherParVille(ville));
    }

    @GetMapping("/recherche/nom/termine-par")
    @Operation(summary = "Rechercher les agences dont le nom se termine par une valeur")
    public ResponseEntity<List<AgenceResponse>> rechercherParNomTerminePar(
            @RequestParam("valeur") String valeur) {
        return repondre(agenceService.rechercherParNomTerminePar(valeur));
    }

    private ResponseEntity<List<AgenceResponse>> repondre(List<Agence> agences) {
        return ResponseEntity.ok(agences.stream()
                .map(agenceMapper::toResponse)
                .toList());
    }
}
