package com.sysadminanywhere.directory.controller.dto;

import com.sysadminanywhere.directory.model.ContactEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddContactDto {
    private String distinguishedName;
    private ContactEntry contact;
}