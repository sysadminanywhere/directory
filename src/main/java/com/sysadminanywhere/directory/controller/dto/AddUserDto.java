package com.sysadminanywhere.directory.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserDto {
    private String distinguishedName;
    private String displayName;
    private String initials;
    private String firstName;
    private String lastName;
    private String sAMAccountName;
    private String userPrincipalName;
    private String password;
}