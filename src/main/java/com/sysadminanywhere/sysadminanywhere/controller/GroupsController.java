package com.sysadminanywhere.sysadminanywhere.controller;

import com.sysadminanywhere.sysadminanywhere.domain.GroupEntry;
import com.sysadminanywhere.sysadminanywhere.service.GroupsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupsController {

    private final GroupsService groupsService;

    public GroupsController(GroupsService groupsService) {
        this.groupsService = groupsService;
    }

    @GetMapping()
    public ResponseEntity<List<GroupEntry>> getUsers() {
        return new ResponseEntity<>(groupsService.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<GroupEntry> addUser(@RequestBody GroupEntry user){
        return new ResponseEntity<>(groupsService.add(user), HttpStatus.OK);
    }

}