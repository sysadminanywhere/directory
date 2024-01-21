package com.sysadminanywhere.directory.controller.dto;

import com.sysadminanywhere.directory.model.UserEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserDto {
    private String distinguishedName;
    private UserEntry user;
    private String password;
    private boolean isCannotChangePassword;
    private boolean isPasswordNeverExpires;
    private boolean isAccountDisabled;
    private boolean isMustChangePassword;
}