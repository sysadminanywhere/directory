package com.sysadminanywhere.sysadminanywhere.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactEntry {

    @AD(name = "cn")
    private String cn;

    @AD(name = "whencreated")
    private LocalDateTime created;

    @AD(name = "description")
    private String description;

    @AD(name = "distinguishedname")
    private String distinguishedName;

    @AD(name = "displayname")
    private String displayName;

    @AD(name = "whenchanged")
    private LocalDateTime modified;

    @AD(name = "objectcategory")
    private String objectCategory;

    @AD(name = "objectclass")
    private List<String> objectClass;

//    @AD(name = "objectguid")
//    private Guid objectGUID;
//
//    @AD(name = "objectsid")
//    private ADSID sid;

    @AD(name = "l")
    private String city;

    @AD(name = "company")
    private String company;

    @AD(name = "c")
    private String country;

    @AD(name = "department")
    private String department;

    @AD(name = "division")
    private String division;

    @AD(name = "mail")
    private String emailAddress;

    @AD(name = "employeeid")
    private String employeeID;

    @AD(name = "employeenumber")
    private String employeeNumber;

    @AD(name = "facsimiletelephonenumber")
    private String fax;

    @AD(name = "givenName")
    private String firstName;

    @AD(name = "wwwhomepage")
    private String homePage;

    @AD(name = "homephone")
    private String homePhone;

    @AD(name = "initials")
    private String initials;

    @AD(name = "mobile")
    private String mobilePhone;

    @AD(name = "physicaldeliveryofficename")
    private String office;

    @AD(name = "telephonenumber")
    private String officePhone;

    @AD(name = "o")
    private String organization;

    @AD(name = "middlename")
    private String otherName;

    @AD(name = "postofficebox")
    private String pOBox;

    @AD(name = "postalcode")
    private String postalCode;

    @AD(name = "st")
    private String state;

    @AD(name = "streetaddress")
    private String streetAddress;

    @AD(name = "sn")
    private String lastName;

    @AD(name = "title")
    private String title;

    @AD(name = "countrycode")
    private int countryCode;

    @AD(name = "adspath")
    private String adsPath;

    @AD(name = "name")
    private String name;

    @AD(name = "instancetype")
    private int instanceType;

}