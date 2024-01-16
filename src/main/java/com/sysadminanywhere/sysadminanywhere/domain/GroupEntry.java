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
    public LocalDateTime created;

    @AD(name = "description")
    public String description;

    @AD(name = "distinguishedname")
    public String distinguishedName;

    @AD(name = "whenchanged")
    public LocalDateTime modified;

    @AD(name = "objectcategory")
    public String objectCategory;

    @AD(name = "objectclass")
    public List<String> objectClass;

//    @AD(name = "objectguid")
//    public Guid objectGUID;
//
//    @AD(name = "objectsid")
//    public ADSID sid;

    @AD(name = "samaccountname")
    public String samAccountName;

    @AD(name = "managedby")
    public String managedBy;

    @AD(name = "memberof")
    public List<String> memberOf;

    @AD(name = "member")
    public List<String> members;

    @AD(name = "grouptype")
    public long groupType;

    @AD(name = "iscriticalsystemobject")
    public boolean isCriticalSystemObject;

    @AD(name = "samaccounttype")
    public int samAccountType;

    @AD(name = "systemflags")
    public int systemFlags;

    @AD(name = "adspath")
    public String adsPath;

    @AD(name = "name")
    public String name;

    @AD(name = "instancetype")
    public int instanceType;

    @AD(name = "admincount")
    public int adminCount;

    @AD(name = "primarygroupid")
    public int primaryGroupId;

//    public String ADGroupType
//    {
//        get
//        {
//            return ADHelper.GetGroupType(GroupType);
//        }
//    }

}