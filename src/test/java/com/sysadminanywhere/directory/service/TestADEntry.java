package com.sysadminanywhere.directory.service;

import com.sysadminanywhere.directory.model.AD;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestADEntry {

    @AD(name = "cn")
    private String cn;

    @AD(name = "whencreated")
    private LocalDateTime created;

    @AD(name = "distinguishedname")
    private String distinguishedName;

    @AD(name = "objectclass")
    private List<String> objectClass;

    @AD(name = "objectguid")
    private UUID objectGUID;

    @AD(name = "iscriticalsystemobject")
    private boolean isCriticalSystemObject;

    @AD(name = "badpwdcount")
    private int badLogonCount;

    @AD(name = "jpegphoto")
    private byte[] jpegPhoto;

}
