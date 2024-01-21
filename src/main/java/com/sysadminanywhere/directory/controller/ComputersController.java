package com.sysadminanywhere.directory.controller;

import com.sysadminanywhere.directory.controller.dto.AddComputerDto;
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
    public ResponseEntity<ComputerEntry> addComputer(@RequestBody AddComputerDto addComputer){
        ComputerEntry computer = new ComputerEntry();
        computer.setCn(addComputer.getCn());
        computer.setDescription(addComputer.getDescription());
        computer.setLocation(addComputer.getLocation());
        computer.setSamAccountName(addComputer.getSAMAccountName());

        return new ResponseEntity<>(computersService.add(addComputer.getDistinguishedName(), computer), HttpStatus.OK);
    }

}