package com.sysadminanywhere.directory.controller;

import com.sysadminanywhere.directory.model.PrinterEntry;
import com.sysadminanywhere.directory.service.PrintersService;
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
    public ResponseEntity<List<PrinterEntry>> getPrinters() {
        return new ResponseEntity<>(printersService.getAll(), HttpStatus.OK);
    }

}