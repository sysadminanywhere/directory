package com.sysadminanywhere.sysadminanywhere.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @AD(name = "dn")
    private String dn;

    @AD(name = "name")
    private String name;
}
