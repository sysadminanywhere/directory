package com.sysadminanywhere.directory.controller;

import com.sysadminanywhere.directory.model.ComputerEntry;
import com.sysadminanywhere.directory.service.ComputersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/computers")
public class ComputersController {

    private final ComputersService computersService;

    public ComputersController(ComputersService computersService) {
        this.computersService = computersService;
    }

    @GetMapping()
    public ResponseEntity<List<ComputerEntry>> getComputers() {
        return new ResponseEntity<>(computersService.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ComputerEntry> addComputer(@RequestBody ComputerEntry computer){
        return new ResponseEntity<>(computersService.add(computer), HttpStatus.OK);
    }

}