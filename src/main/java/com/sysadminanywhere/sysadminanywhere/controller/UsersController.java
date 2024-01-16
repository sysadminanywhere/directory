package com.sysadminanywhere.sysadminanywhere.controller;

import com.sysadminanywhere.sysadminanywhere.domain.UserEntry;
import com.sysadminanywhere.sysadminanywhere.service.UsersService;
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
    public ResponseEntity<UserEntry> addUser(@RequestBody UserEntry user){
        return new ResponseEntity<>(usersService.add(user), HttpStatus.OK);
    }

}