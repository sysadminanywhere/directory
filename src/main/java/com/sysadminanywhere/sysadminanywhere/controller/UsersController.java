package com.sysadminanywhere.sysadminanywhere.controller;

import com.sysadminanywhere.sysadminanywhere.domain.User;
import com.sysadminanywhere.sysadminanywhere.service.users.UsersServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersServiceImpl usersServiceImpl;

    public UsersController(UsersServiceImpl usersServiceImpl) {
        this.usersServiceImpl = usersServiceImpl;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(usersServiceImpl.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody User user){
        return new ResponseEntity<>(usersServiceImpl.add(user), HttpStatus.OK);
    }

}