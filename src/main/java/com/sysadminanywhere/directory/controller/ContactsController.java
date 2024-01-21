package com.sysadminanywhere.directory.controller;

import com.sysadminanywhere.directory.controller.dto.AddContactDto;
import com.sysadminanywhere.directory.model.ContactEntry;
import com.sysadminanywhere.directory.model.UserEntry;
import com.sysadminanywhere.directory.service.ContactsService;
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
    public ResponseEntity<ContactEntry> addContact(@RequestBody AddContactDto addContact){
        return new ResponseEntity<>(contactsService.add(addContact.getDistinguishedName(), addContact.getContact()), HttpStatus.OK);
    }

}