package com.telemed.controller;
import com.telemed.dto.AddMedicRequest;
import com.telemed.dto.MedicDto;
import com.telemed.dto.MedicamentDto;
import com.telemed.service.MedicService;
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
@Api(value = "/medics", tags = "Medics")
@RequestMapping("/medics")
public class MedicController {

    @Autowired
    private final MedicService service;

    public MedicController(MedicService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(value = "Get all medics",
            notes = "Get all medics from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The data was retrieved successfully"),
            @ApiResponse(code = 404, message = "No medics were found")
    })
    public ResponseEntity<List<MedicDto>> getAll(){
        return ResponseEntity
                .ok()
                .body(service.getAllMedics());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get medic",
            notes = "Get medic based on the id received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "The medic with the entered id does not exist")
    })
    public ResponseEntity<MedicDto> get(@PathVariable Long id) {
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
    @ApiOperation(value = "Add medic",
            notes = "Add new medic based on information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The medic was successfully created"),
            @ApiResponse(code = 400, message = "Validation error on the received request"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<MedicDto> addMedic(@RequestBody AddMedicRequest medicDto) {
        return ResponseEntity
                .ok()
                .body(service.add(medicDto));
    }
}