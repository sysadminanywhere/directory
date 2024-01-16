package com.sysadminanywhere.sysadminanywhere.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupEntry {

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

//    @AD(name = "objectguid")
//    private Guid objectGUID;
//
//    @AD(name = "objectsid")
//    private ADSID sid;

    @AD(name = "samaccountname")
    private String samAccountName;

    @AD(name = "managedby")
    private String managedBy;

    @AD(name = "memberof")
    private List<String> memberOf;

    @AD(name = "member")
    private List<String> members;

    @AD(name = "grouptype")
    private long groupType;

    @AD(name = "iscriticalsystemobject")
    private boolean isCriticalSystemObject;

    @AD(name = "samaccounttype")
    private int samAccountType;

    @AD(name = "systemflags")
    private int systemFlags;

    @AD(name = "adspath")
    private String adsPath;

    @AD(name = "name")
    private String name;

    @AD(name = "instancetype")
    private int instanceType;

    @AD(name = "admincount")
    private int adminCount;

    @AD(name = "primarygroupid")
    private int primaryGroupId;

//    private String ADGroupType
//    {
//        get
//        {
//            return ADHelper.GetGroupType(GroupType);
//        }
//    }

}