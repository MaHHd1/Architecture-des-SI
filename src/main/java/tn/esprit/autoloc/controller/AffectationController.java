package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.service.AffectationService;

@RestController
@RequestMapping("/api/affectations")
public class AffectationController {

    private final AffectationService affectationService;

    public AffectationController(AffectationService affectationService) {
        this.affectationService = affectationService;
    }

    @DeleteMapping("/employes/{idEmploye}/agence")
    @Operation(summary = "Désaffecter un employé de son agence")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Employé désaffecté"),
            @ApiResponse(responseCode = "404", description = "Employé introuvable")
    })
    public ResponseEntity<Void> desaffecterEmploye(@PathVariable Long idEmploye) {
        affectationService.desaffecterEmploye(idEmploye);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/employes/{idEmploye}/agence/{idAgence}")
    @Operation(summary = "Affecter un employé à une agence")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Employé affecté"),
            @ApiResponse(responseCode = "404", description = "Employé ou agence introuvable")
    })
    public ResponseEntity<Void> affecterEmploye(
            @PathVariable Long idEmploye,
            @PathVariable Long idAgence) {
        affectationService.affecterEmploye(idEmploye, idAgence);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/vehicules/{idVehicule}/agence/{idAgence}")
    @Operation(summary = "Affecter un véhicule à une agence")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Véhicule affecté"),
            @ApiResponse(responseCode = "404", description = "Véhicule ou agence introuvable")
    })
    public ResponseEntity<Void> affecterVehicule(
            @PathVariable Long idVehicule,
            @PathVariable Long idAgence) {
        affectationService.affecterVehicule(idVehicule, idAgence);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/vehicules/{idVehicule}/equipements/{idEquipement}")
    @Operation(summary = "Affecter un équipement à un véhicule")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Équipement affecté"),
            @ApiResponse(responseCode = "404", description = "Véhicule ou équipement introuvable")
    })
    public ResponseEntity<Void> affecterEquipement(
            @PathVariable Long idVehicule,
            @PathVariable Long idEquipement) {
        affectationService.affecterEquipement(idVehicule, idEquipement);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/vehicules/{idVehicule}/equipements/{idEquipement}")
    @Operation(summary = "Désaffecter un équipement d'un véhicule")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Équipement désaffecté"),
            @ApiResponse(responseCode = "404", description = "Véhicule ou équipement introuvable")
    })
    public ResponseEntity<Void> desaffecterEquipement(
            @PathVariable Long idVehicule,
            @PathVariable Long idEquipement) {
        affectationService.desaffecterEquipement(idVehicule, idEquipement);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleEntityNotFound() {
        return ResponseEntity.notFound().build();
    }
}
