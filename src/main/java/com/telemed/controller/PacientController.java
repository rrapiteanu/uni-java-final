package com.telemed.controller;
import com.telemed.dto.*;
import com.telemed.service.PacientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@Api(value = "/pacienti", tags = "Patients")
@RequestMapping("/pacienti")
public class PacientController {

    @Autowired
    private final PacientService service;

    public PacientController(PacientService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(value = "Get all patients",
            notes = "Get all patients from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The data was retrieved successfully"),
            @ApiResponse(code = 404, message = "No patient were found")
    })
    public ResponseEntity<List<PacientDto>> getAll(){
        return ResponseEntity
                .ok()
                .body(service.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get patient",
            notes = "Get patient based on the id received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "The patient with the entered id does not exist")
    })
    public ResponseEntity<PacientDto> get(@PathVariable Long id) {
        if (service.getOne(id) == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        return ResponseEntity
                .ok()
                .body(service.getOne(id));
    }

    @PostMapping
    @ApiOperation(value = "Add patient",
            notes = "Add new patient based on information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The patient was successfully created"),
            @ApiResponse(code = 400, message = "Validation error on the received request"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<PacientDto> addPacient(@RequestBody AddPacientRequest pacientRequest) {
        return ResponseEntity
                .ok()
                .body(service.add(pacientRequest));
    }
}