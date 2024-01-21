package com.sysadminanywhere.directory.controller;

import com.sysadminanywhere.directory.controller.dto.AddUserDto;
import com.sysadminanywhere.directory.model.UserEntry;
import com.sysadminanywhere.directory.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping()
    public ResponseEntity<List<UserEntry>> getUsers() {
        return new ResponseEntity<>(usersService.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserEntry> addUser(@RequestBody AddUserDto addUser) {
        return new ResponseEntity<>(usersService.add(addUser.getDistinguishedName(), addUser.getUser(), addUser.getPassword()), HttpStatus.OK);
    }

}