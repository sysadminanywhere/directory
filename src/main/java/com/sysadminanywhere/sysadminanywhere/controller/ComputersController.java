package com.sysadminanywhere.sysadminanywhere.controller;

import com.sysadminanywhere.sysadminanywhere.domain.Computer;
import com.sysadminanywhere.sysadminanywhere.service.ComputersServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/computers")
public class ComputersController {

    private final ComputersServiceImpl computersServiceImpl;

    public ComputersController(ComputersServiceImpl computersServiceImpl) {
        this.computersServiceImpl = computersServiceImpl;
    }

    @GetMapping()
    public ResponseEntity<List<Computer>> getComputers() {
        return new ResponseEntity<>(computersServiceImpl.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Computer> addComputer(@RequestBody Computer computer){
        return new ResponseEntity<>(computersServiceImpl.add(computer), HttpStatus.OK);
    }

}