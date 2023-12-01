package com.sysadminanywhere.sysadminanywhere.controller;

import com.sysadminanywhere.sysadminanywhere.domain.Computer;
import com.sysadminanywhere.sysadminanywhere.service.ComputersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/computers")
public class ComputersController {

    private final ComputersService computersService;

    public ComputersController(ComputersService computersService) {
        this.computersService = computersService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Computer>> getComputers() {
        return new ResponseEntity<>(computersService.GetAll(), HttpStatus.OK);
    }

}