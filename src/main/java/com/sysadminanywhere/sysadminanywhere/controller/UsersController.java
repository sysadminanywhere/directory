package com.sysadminanywhere.sysadminanywhere.controller;

import com.sysadminanywhere.sysadminanywhere.controller.dto.AddUserDto;
import com.sysadminanywhere.sysadminanywhere.model.UserEntry;
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
    public ResponseEntity<UserEntry> addUser(@RequestBody AddUserDto addUser) {
        UserEntry user = new UserEntry();
        user.setDisplayName(addUser.getDisplayName());
        user.setInitials(addUser.getInitials());
        user.setFirstName(addUser.getFirstName());
        user.setLastName(addUser.getLastName());
        user.setSamAccountName(addUser.getSAMAccountName());
        user.setUserPrincipalName(addUser.getUserPrincipalName());

        return new ResponseEntity<>(usersService.add(addUser.getDistinguishedName(), user, addUser.getPassword()), HttpStatus.OK);
    }

}