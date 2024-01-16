package com.sysadminanywhere.sysadminanywhere.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComputerEntry {

    @AD(name = "cn")
    private String cn;

    @AD(name = "whencreated")
    private LocalDateTime created;

    @AD(name = "description")
    private String description;

    @AD(name = "distinguishedname")
    private String distinguishedName;

    @AD(name = "whenchanged")
    private LocalDateTime modified;

    @AD(name = "objectcategory")
    private String objectCategory;

    @AD(name = "objectclass")
    private List<String> objectClass;

    @AD(name = "objectguid")
    private UUID objectGUID;

    @AD(name = "objectsid")
    private ADSID sid;

    @AD(name = "samaccountname")
    private String samAccountName;

    @AD(name = "accountexpires")
    private LocalDateTime accountExpirationDate;

    @AD(name = "badpwdcount")
    private int badLogonCount;

    @AD(name = "badpasswordtime")
    private LocalDateTime lastBadPasswordAttempt;

    @AD(name = "lastlogon")
    private LocalDateTime lastLogon;

    @AD(name = "location")
    private String location;

    @AD(name = "managedby")
    private String managedBy;

    @AD(name = "memberof")
    private List<String> memberOf;

    @AD(name = "operatingsystem")
    private String operatingSystem;

    @AD(name = "operatingsystemhotfix")
    private String operatingSystemHotfix;

    @AD(name = "operatingsystemservicepack")
    private String operatingSystemServicePack;

    @AD(name = "operatingsystemversion")
    private String operatingSystemVersion;

    @AD(name = "pwdlastset")
    private LocalDateTime passwordLastSet;

    @AD(name = "serviceprincipalname")
    private List<String> servicePrincipalNames;

    @AD(name = "primarygroupid")
    private int primaryGroupId;

    @AD(name = "useraccountcontrol")
    private int userAccountControl;

//    private UserAccountControls UserControl
//    {
//        get
//        {
//            return (UserAccountControls)UserAccountControl;
//        }
//        set
//        {
//            UserAccountControl = (int)value;
//        }
//    }

    @AD(name = "msds-supportedencryptiontypes")
    private int msdsSupportedEncryptionTypes;

    @AD(name = "iscriticalsystemobject")
    private boolean isCriticalSystemObject;

    @AD(name = "dnshostname")
    private String dnsHostName;

    @AD(name = "samaccounttype")
    private int samAccountType;

    @AD(name = "countrycode")
    private int countryCode;

    @AD(name = "localpolicyflags")
    private int localPolicyFlags;

    @AD(name = "logoncount")
    private int logonCount;

    @AD(name = "adspath")
    private String adsPath;

    @AD(name = "name")
    private String name;

    @AD(name = "lastlogoff")
    private int lastLogoff;

    @AD(name = "instancetype")
    private int instanceType;

    @AD(name = "codepage")
    private int codepage;

}