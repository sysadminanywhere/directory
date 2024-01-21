package com.sysadminanywhere.directory.controller;

import com.sysadminanywhere.directory.controller.dto.AddContactDto;
import com.sysadminanywhere.directory.model.ComputerEntry;
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
    public ResponseEntity<List<ContactEntry>> getAll() {
        return new ResponseEntity<>(contactsService.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ContactEntry> add(@RequestBody AddContactDto addContact){
        return new ResponseEntity<>(contactsService.add(addContact.getDistinguishedName(), addContact.getContact()), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ContactEntry> update(@RequestBody ContactEntry contact) {
        return new ResponseEntity<>(contactsService.update(contact), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity delete(@RequestParam String distinguishedName) {
        contactsService.delete(distinguishedName);
        return new ResponseEntity(HttpStatus.OK);
    }

}