package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.VehiculeResponse;
import tn.esprit.autoloc.mapper.VehiculeMapper;
import tn.esprit.autoloc.service.VehiculeService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {

    private final VehiculeService vehiculeService;
    private final VehiculeMapper vehiculeMapper;

    public VehiculeController(VehiculeService vehiculeService, VehiculeMapper vehiculeMapper) {
        this.vehiculeService = vehiculeService;
        this.vehiculeMapper = vehiculeMapper;
    }

    @GetMapping("/marque/{marque}")
    @Operation(summary = "Rechercher des véhicules par marque exacte")
    public ResponseEntity<List<VehiculeResponse>> rechercherParMarque(
            @PathVariable("marque") String marque) {
        return repondre(vehiculeService.rechercherParMarque(marque));
    }

    @GetMapping("/categorie/{categorie}")
    @Operation(summary = "Rechercher des véhicules par catégorie")
    public ResponseEntity<List<VehiculeResponse>> rechercherParCategorie(
            @PathVariable("categorie") CategorieVehicule categorie) {
        return repondre(vehiculeService.rechercherParCategorie(categorie));
    }

    @GetMapping("/disponibles")
    @Operation(summary = "Rechercher les véhicules avec le statut DISPONIBLE")
    public ResponseEntity<List<VehiculeResponse>> rechercherDisponibles() {
        return repondre(vehiculeService.rechercherDisponibles());
    }

    @GetMapping("/recherche/marque/contient")
    @Operation(summary = "Rechercher les marques contenant une valeur")
    public ResponseEntity<List<VehiculeResponse>> rechercherParMarqueContenant(
            @RequestParam("valeur") String valeur) {
        return repondre(vehiculeService.rechercherParMarqueContenant(valeur));
    }

    @GetMapping("/recherche/modele/commence-par")
    @Operation(summary = "Rechercher les modèles commençant par une valeur")
    public ResponseEntity<List<VehiculeResponse>> rechercherParModeleCommencantPar(
            @RequestParam("valeur") String valeur) {
        return repondre(vehiculeService.rechercherParModeleCommencantPar(valeur));
    }

    @GetMapping("/tarif/superieur")
    @Operation(summary = "Rechercher les véhicules dont le tarif journalier est supérieur à une valeur")
    public ResponseEntity<List<VehiculeResponse>> rechercherParTarifSuperieurA(
            @RequestParam("valeur") BigDecimal valeur) {
        return repondre(vehiculeService.rechercherParTarifSuperieurA(valeur));
    }

    @GetMapping("/tarif/inferieur")
    @Operation(summary = "Rechercher les véhicules dont le tarif journalier est inférieur à une valeur")
    public ResponseEntity<List<VehiculeResponse>> rechercherParTarifInferieurA(
            @RequestParam("valeur") BigDecimal valeur) {
        return repondre(vehiculeService.rechercherParTarifInferieurA(valeur));
    }

    @GetMapping("/tarif/superieur-ou-egal")
    @Operation(summary = "Rechercher les véhicules dont le tarif journalier est supérieur ou égal à une valeur")
    public ResponseEntity<List<VehiculeResponse>> rechercherParTarifSuperieurOuEgalA(
            @RequestParam("valeur") BigDecimal valeur) {
        return repondre(vehiculeService.rechercherParTarifSuperieurOuEgalA(valeur));
    }

    @GetMapping("/marque/{marque}/disponibles")
    @Operation(summary = "Rechercher les véhicules DISPONIBLE d'une marque")
    public ResponseEntity<List<VehiculeResponse>> rechercherDisponiblesParMarque(
            @PathVariable("marque") String marque) {
        return repondre(vehiculeService.rechercherDisponiblesParMarque(marque));
    }

    @GetMapping("/categories")
    @Operation(summary = "Rechercher les véhicules appartenant à une ou plusieurs catégories")
    public ResponseEntity<List<VehiculeResponse>> rechercherParCategories(
            @RequestParam("valeurs") List<CategorieVehicule> valeurs) {
        return repondre(vehiculeService.rechercherParCategories(valeurs));
    }

    @GetMapping("/marque/{marque}/tarif/inferieur")
    @Operation(summary = "Rechercher les véhicules d'une marque sous un tarif donné")
    public ResponseEntity<List<VehiculeResponse>> rechercherParMarqueEtTarifInferieurA(
            @PathVariable("marque") String marque,
            @RequestParam("valeur") BigDecimal valeur) {
        return repondre(vehiculeService.rechercherParMarqueEtTarifInferieurA(marque, valeur));
    }

    @GetMapping("/disponibles/tri-tarif-asc")
    @Operation(summary = "Lister les véhicules DISPONIBLE par tarif journalier croissant")
    public ResponseEntity<List<VehiculeResponse>> rechercherDisponiblesParTarifCroissant() {
        return repondre(vehiculeService.rechercherDisponiblesParTarifCroissant());
    }

    @GetMapping("/tri-tarif-desc")
    @Operation(summary = "Lister tous les véhicules par tarif journalier décroissant")
    public ResponseEntity<List<VehiculeResponse>> rechercherTousParTarifDecroissant() {
        return repondre(vehiculeService.rechercherTousParTarifDecroissant());
    }

    private ResponseEntity<List<VehiculeResponse>> repondre(List<Vehicule> vehicules) {
        return ResponseEntity.ok(vehicules.stream()
                .map(vehiculeMapper::toResponse)
                .toList());
    }
}
