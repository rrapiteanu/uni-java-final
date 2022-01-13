package com.telemed.controller;
import com.telemed.dto.MedicDto;
import com.telemed.dto.MedicamentDto;
import com.telemed.dto.SpecialtyDto;
import com.telemed.model.Specialty;
import com.telemed.service.MedicService;
import com.telemed.service.MedicamentService;
import com.telemed.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@Api(value = "/specialties", tags = "Specialties")
@RequestMapping("/specialties")
public class SpecialtyController {

    @Autowired
    private final SpecialtyService service;

    @Autowired
    private final MedicService medicService;

    public SpecialtyController(SpecialtyService service, MedicService medicService) {
        this.service = service;
        this.medicService = medicService;
    }

    @GetMapping
    @ApiOperation(value = "Get all specialties",
            notes = "Get all specialties from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The data was retrieved successfully"),
            @ApiResponse(code = 404, message = "No specialties were found")
    })
    public ResponseEntity<List<SpecialtyDto>> getAll(){
        return ResponseEntity
                .ok()
                .body(service.getAllSpecialties());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a specialty",
            notes = "Get specialty based on the id received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "The specialty with the entered id does not exist")
    })
    public ResponseEntity<SpecialtyDto> get(@PathVariable Long id) {
        if (service.getOne(id) == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        return ResponseEntity
                .ok()
                .body(service.getOne(id));
    }

    @GetMapping("/{id}/medics")
    @ApiOperation(value = "Get medics by specialty",
            notes = "Get medics based on the specialty id received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "The specialty with the entered id does not exist")
    })
    public ResponseEntity<List<MedicDto>> getMedicsBySpecialty(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(medicService.getMedicsBySpecialtyId(id));
    }

    @PostMapping
    @ApiOperation(value = "Add specialty",
            notes = "Add new specialty based on information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The specialty was successfully created"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<SpecialtyDto> addSpecialty(@RequestBody SpecialtyDto specialtyDto) {
        return ResponseEntity
                .ok()
                .body(service.add(specialtyDto));
    }
}