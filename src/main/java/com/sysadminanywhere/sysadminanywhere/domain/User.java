package com.sysadminanywhere.sysadminanywhere.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @AD(name = "dn")
    private String dn;

    @AD(name = "name")
    private String name;
}