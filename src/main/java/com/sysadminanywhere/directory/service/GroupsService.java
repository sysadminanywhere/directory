package com.sysadminanywhere.directory.service;

import com.sysadminanywhere.directory.model.GroupEntry;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.DefaultEntry;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupsService {

    private final LdapService ldapService;

    ResolveService<GroupEntry> resolveService = new ResolveService<>(GroupEntry.class);

    public GroupsService(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @SneakyThrows
    public List<GroupEntry> getAll() {
        List<Entry> result = ldapService.search("(objectClass=group)");
        return resolveService.getADList(result);
    }

    public GroupEntry getByCN(String cn) {
        List<Entry> result = ldapService.search("(&(objectClass=computer)(cn=" + cn + "))");
        Optional<Entry> entry = result.stream().findFirst();

        if (entry.isPresent())
            return resolveService.getADValue(entry.get());
        else
            return null;
    }

    @SneakyThrows
    public GroupEntry add(String distinguishedName, GroupEntry group) {
        String dn;

        if(distinguishedName.isEmpty()) {
            dn = "cn=" + group.getCn() + "," + ldapService.getUsersContainer();
        } else {
            dn = "cn=" + group.getCn() + "," + distinguishedName;
        }

        Entry entry = new DefaultEntry(
                dn,
                "description", group.getDescription(),
                "groupType", group.getGroupType(),
                "sAMAccountName", group.getSamAccountName(),
                "objectclass:group",
                "cn", group.getCn()
        );

        ldapService.add(entry);
        return getByCN(group.getCn());
    }

    public GroupEntry update(GroupEntry group) {
        return new GroupEntry();
    }

    @SneakyThrows
    public void delete(GroupEntry group) {
        Entry entry = new DefaultEntry(group.getDistinguishedName());
        ldapService.delete(entry);
    }

}