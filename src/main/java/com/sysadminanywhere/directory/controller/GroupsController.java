package com.sysadminanywhere.directory.controller;

import com.sysadminanywhere.directory.controller.dto.AddGroupDto;
import com.sysadminanywhere.directory.model.ComputerEntry;
import com.sysadminanywhere.directory.model.GroupEntry;
import com.sysadminanywhere.directory.service.GroupsService;
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
    public ResponseEntity<List<GroupEntry>> getAll() {
        return new ResponseEntity<>(groupsService.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<GroupEntry> add(@RequestBody AddGroupDto addGroup) {
        return new ResponseEntity<>(groupsService.add(
                addGroup.getDistinguishedName(),
                addGroup.getGroup(),
                addGroup.getGroupScope(),
                addGroup.isSecurity()
        ), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<GroupEntry> update(@RequestBody GroupEntry group) {
        return new ResponseEntity<>(groupsService.update(group), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity delete(@RequestParam String distinguishedName) {
        groupsService.delete(distinguishedName);
        return new ResponseEntity(HttpStatus.OK);
    }

}