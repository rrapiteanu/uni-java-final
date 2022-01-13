package com.telemed.controller;
import com.telemed.dto.*;
import com.telemed.model.Conversation;
import com.telemed.model.Message;
import com.telemed.model.Prescription;
import com.telemed.service.ConversationService;
import com.telemed.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "/prescriptions", tags = "Prescriptions")
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private final PrescriptionService service;

    public PrescriptionController(PrescriptionService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a prescription",
            notes = "Get prescription based on the id received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "The prescription with the entered id does not exist")
    })
    public ResponseEntity<Prescription> get(@PathVariable Long id) {
        if (service.getOne(id) == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        return ResponseEntity
                .ok()
                .body(service.getOne(id));
    }

    @GetMapping("/pacienti/{pacientId}")
    @ApiOperation(value = "Get prescription by patient id",
            notes = "Get prescription based on the patient id received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "The patient with the entered id does not exist")
    })
    public ResponseEntity<List<Prescription>> getPrescriptionsForPacient(@PathVariable Long pacientId) {
        return ResponseEntity
                .ok()
                .body(service.getPrescriptionsForPacientId(pacientId));
    }

    @PostMapping("/new")
    @ApiOperation(value = "Create new prescription",
            notes = "Create new prescription for patient by medic")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The prescription was successfully created"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Prescription> createPrescription(@Valid @RequestBody NewPrescriptionRequest prescriptionRequest) {
        return ResponseEntity
                .ok()
                .body(service.newPrescription(prescriptionRequest));
    }


    @PostMapping("/{prescriptionId}/new_entry")
    @ApiOperation(value = "Add new entry to prescription",
            notes = "Add new entry to prescription")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The entry was successfully added"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Prescription> addEntryToPrescription(@PathVariable Long prescriptionId, @Valid @RequestBody NewPrescriptionItemRequest itemRequest) {
        return ResponseEntity
                .ok()
                .body(service.addItemToPrescription(itemRequest, prescriptionId));
    }
}