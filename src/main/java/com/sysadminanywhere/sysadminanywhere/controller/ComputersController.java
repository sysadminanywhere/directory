package com.sysadminanywhere.sysadminanywhere.controller;

import com.sysadminanywhere.sysadminanywhere.domain.Computer;
import com.sysadminanywhere.sysadminanywhere.service.computers.ComputersServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/computers")
public class ComputersController {

    private final ComputersServiceImpl computersServiceImpl;

    public ComputersController(ComputersServiceImpl computersServiceImpl) {
        this.computersServiceImpl = computersServiceImpl;
    }

    @GetMapping("/")
    public ResponseEntity<List<Computer>> getComputers() {
        return new ResponseEntity<>(computersServiceImpl.GetAll(), HttpStatus.OK);
    }

}