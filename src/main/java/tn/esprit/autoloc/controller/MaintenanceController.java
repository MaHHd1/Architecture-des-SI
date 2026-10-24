package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.dto.MaintenanceRequest;
import tn.esprit.autoloc.dto.MaintenanceResponse;
import tn.esprit.autoloc.mapper.MaintenanceMapper;
import tn.esprit.autoloc.service.MaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;
    private final MaintenanceMapper maintenanceMapper;

    public MaintenanceController(
            MaintenanceService maintenanceService,
            MaintenanceMapper maintenanceMapper) {
        this.maintenanceService = maintenanceService;
        this.maintenanceMapper = maintenanceMapper;
    }

    @GetMapping
    @Operation(summary = "Get all maintenances")
    @ApiResponse(
            responseCode = "200",
            description = "Maintenances retrieved successfully",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = MaintenanceResponse.class))))
    public ResponseEntity<List<MaintenanceResponse>> getAllMaintenances() {
        List<MaintenanceResponse> responses = maintenanceService.findAll().stream()
                .map(maintenanceMapper::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a maintenance by ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Maintenance found",
                    content = @Content(schema = @Schema(implementation = MaintenanceResponse.class))),
            @ApiResponse(responseCode = "404", description = "Maintenance not found")
    })
    public ResponseEntity<MaintenanceResponse> getMaintenanceById(@PathVariable("id") Long id) {
        return ResponseEntity.of(maintenanceService.findById(id).map(maintenanceMapper::toResponse));
    }

    @PostMapping
    @Operation(summary = "Create a maintenance")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Maintenance created",
                    content = @Content(schema = @Schema(implementation = MaintenanceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Vehicle not found")
    })
    public ResponseEntity<MaintenanceResponse> createMaintenance(
            @Valid @RequestBody MaintenanceRequest request) {
        return maintenanceService.create(request)
                .map(maintenanceMapper::toResponse)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a maintenance")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Maintenance updated",
                    content = @Content(schema = @Schema(implementation = MaintenanceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Maintenance or vehicle not found")
    })
    public ResponseEntity<MaintenanceResponse> updateMaintenance(
            @PathVariable("id") Long id,
            @Valid @RequestBody MaintenanceRequest request) {
        return ResponseEntity.of(maintenanceService.update(id, request).map(maintenanceMapper::toResponse));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a maintenance")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Maintenance deleted"),
            @ApiResponse(responseCode = "404", description = "Maintenance not found")
    })
    public ResponseEntity<Void> deleteMaintenance(@PathVariable("id") Long id) {
        return maintenanceService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
