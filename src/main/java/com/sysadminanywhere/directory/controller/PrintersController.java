package com.sysadminanywhere.directory.controller;

import com.sysadminanywhere.directory.model.ComputerEntry;
import com.sysadminanywhere.directory.model.PrinterEntry;
import com.sysadminanywhere.directory.service.PrintersService;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/printers")
public class PrintersController {

    private final PrintersService printersService;

    public PrintersController(PrintersService printersService) {
        this.printersService = printersService;
    }

    @GetMapping()
    public ResponseEntity<List<PrinterEntry>> getAll() {
        return new ResponseEntity<>(printersService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity delete(@NonNull @RequestParam String distinguishedName) {
        printersService.delete(distinguishedName);
        return new ResponseEntity(HttpStatus.OK);
    }


}