package com.sysadminanywhere.sysadminanywhere.controller;

import com.sysadminanywhere.sysadminanywhere.domain.User;
import com.sysadminanywhere.sysadminanywhere.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(usersService.GetAll(), HttpStatus.OK);
    }

}