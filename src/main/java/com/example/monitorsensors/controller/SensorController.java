package com.example.monitorsensors.controller;

import com.example.monitorsensors.model.SensorEntity;
import com.example.monitorsensors.service.SensorService;
import com.example.monitorsensors.validation.CustomExceptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@SecurityRequirement(name = "monitorsensors")
@Tag(name = "sensor", description = "Monitor Sensors API")
public class SensorController {

    @Autowired
    SensorService sensorService;

    @Operation(summary = "Get a list of sensors", description = "List of sensors in the system", tags = { "sensor" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The response for the user request",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SensorEntity.class)))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = { @Content(mediaType = "error") })})
    @GetMapping(value = "/sensors")
    public ResponseEntity<List<SensorEntity>> list() {
        try {
            final List<SensorEntity> list = sensorService.list();
            return !list.isEmpty()
                    ? ResponseEntity.ok(list)
                    : ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Add a new sensor", description = "Returns created sensor", tags = { "sensor" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sensor created",
                    content = @Content(schema = @Schema(implementation = SensorEntity.class))),
            @ApiResponse(responseCode = "405", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = { @Content(mediaType = "error") })})
    @PostMapping(value = "/sensors/create", consumes = { "application/json" })
    public ResponseEntity<?> create(@Valid @RequestBody SensorEntity sensorEntity) {
        try {
            final SensorEntity entity = sensorService.create(sensorEntity);
            return new ResponseEntity<>(entity, HttpStatus.CREATED);
        } catch (TransactionSystemException e) {
            return CustomExceptionHandler.getConstraintViolationException(e);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Find sensor by ID", description = "Returns a single sensor", tags = { "sensor" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = SensorEntity.class))),
            @ApiResponse(responseCode = "404", description = "Sensor not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = { @Content(mediaType = "error") })})
    @GetMapping(value = "/sensors/{id}", produces = { "application/json" })
    public ResponseEntity<SensorEntity> read(@PathVariable(name = "id") int id) {
        try {
            final SensorEntity entity = sensorService.read(id);
            return entity != null
                    ? ResponseEntity.ok(entity)
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Update an existing sensor", description = "", tags = { "sensor" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Update failed"),
            @ApiResponse(responseCode = "405", description = "Validation exception"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = { @Content(mediaType = "error") })})
    @PutMapping(value = "/sensors/{id}/update", produces = { "application/json" })
    public ResponseEntity<?> update(@Valid @PathVariable(name = "id") int id, @RequestBody SensorEntity sensorEntity) {
        try {
            return sensorService.update(id, sensorEntity)
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (TransactionSystemException e) {
            return CustomExceptionHandler.getConstraintViolationException(e);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Deletes a sensor", description = "", tags = { "sensor" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Failed to delete"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = { @Content(mediaType = "error") })})
    @DeleteMapping(value = "/sensors/{id}/delete")
    public ResponseEntity<SensorEntity> delete(@PathVariable(name = "id") int id) {
        try {
            return sensorService.delete(id)
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Get a list of sensors", description = "Founded sensors", tags = { "sensor" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The response for the user request",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SensorEntity.class)))}),
            @ApiResponse(responseCode = "204", description = "Sensor not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = { @Content(mediaType = "error") })})
    @GetMapping(value = "/search", produces = { "application/json" })
    public ResponseEntity<List<SensorEntity>> search(@RequestParam(required = true) String search) {
        try {
            final List<SensorEntity> list = sensorService.search(search);
            return !list.isEmpty()
                    ? ResponseEntity.ok(list)
                    : ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/403")
    public ResponseEntity<String> accessDenied() {
        return new ResponseEntity<>("Access Denied. Please LogIn as Admin", HttpStatus.FORBIDDEN);
    }
}
