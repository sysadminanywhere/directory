package com.sysadminanywhere.directory.controller.dto;

import com.sysadminanywhere.directory.model.GroupEntry;
import com.sysadminanywhere.directory.model.GroupScopes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddGroupDto {
    private String distinguishedName;
    private GroupEntry group;
    private GroupScopes groupScope;
    private boolean isSecurity;
}