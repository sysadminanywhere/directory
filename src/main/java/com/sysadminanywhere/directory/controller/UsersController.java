package com.sysadminanywhere.directory.controller;

import com.sysadminanywhere.directory.controller.dto.AddUserDto;
import com.sysadminanywhere.directory.model.ComputerEntry;
import com.sysadminanywhere.directory.model.UserEntry;
import com.sysadminanywhere.directory.service.UsersService;
import lombok.NonNull;
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
    public ResponseEntity<List<UserEntry>> getAll() {
        return new ResponseEntity<>(usersService.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserEntry> add(@NonNull @RequestBody AddUserDto addUser) {
        return new ResponseEntity<>(usersService.add(
                addUser.getDistinguishedName(),
                addUser.getUser(),
                addUser.getPassword(),
                addUser.isCannotChangePassword(),
                addUser.isPasswordNeverExpires(),
                addUser.isAccountDisabled(),
                addUser.isMustChangePassword()
        ), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<UserEntry> update(@NonNull @RequestBody UserEntry user) {
        return new ResponseEntity<>(usersService.update(user), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity delete(@NonNull @RequestParam String distinguishedName) {
        usersService.delete(distinguishedName);
        return new ResponseEntity(HttpStatus.OK);
    }

}