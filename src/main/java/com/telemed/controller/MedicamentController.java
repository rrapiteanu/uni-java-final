package com.telemed.controller;
import com.telemed.dto.MedicamentDto;
import com.telemed.service.MedicamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@Api(value = "/medicamente", tags = "Drugs")
@RequestMapping("/medicamente")
public class MedicamentController {

    @Autowired
    private final MedicamentService service;

    public MedicamentController(MedicamentService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(value = "Get all drugs",
            notes = "Get all drugs from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The data was retrieved successfully"),
            @ApiResponse(code = 404, message = "No drugs were found")
    })
    public ResponseEntity<List<MedicamentDto>> getAll(){
        return ResponseEntity
                .ok()
                .body(service.getAllMedicamente());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a drug",
            notes = "Get drug based on the id received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "The drug with the entered id does not exist")
    })
    public ResponseEntity<MedicamentDto> get(@PathVariable Long id) {
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
    @ApiOperation(value = "Add drug",
            notes = "Add new drug based on information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The drug was successfully added"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<MedicamentDto> addMedicament(@RequestBody MedicamentDto medicamentDto) {
        return ResponseEntity
                .ok()
                .body(service.add(medicamentDto));
    }
}