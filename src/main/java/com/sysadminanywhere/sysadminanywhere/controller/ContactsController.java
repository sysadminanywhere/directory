package com.sysadminanywhere.sysadminanywhere.controller;

import com.sysadminanywhere.sysadminanywhere.model.ContactEntry;
import com.sysadminanywhere.sysadminanywhere.service.ContactsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactsController {

    private final ContactsService contactsService;

    public ContactsController(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @GetMapping()
    public ResponseEntity<List<ContactEntry>> getContacts() {
        return new ResponseEntity<>(contactsService.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ContactEntry> addContact(@RequestBody ContactEntry contact){
        return new ResponseEntity<>(contactsService.add(contact), HttpStatus.OK);
    }

}