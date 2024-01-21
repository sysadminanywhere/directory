package com.sysadminanywhere.directory.controller.dto;

import com.sysadminanywhere.directory.model.ComputerEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddComputerDto {
    private String distinguishedName;
    private ComputerEntry computer;
    private boolean isEnabled;
}