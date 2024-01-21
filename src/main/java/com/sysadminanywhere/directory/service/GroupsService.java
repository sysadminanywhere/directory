package com.sysadminanywhere.directory.service;

import com.sysadminanywhere.directory.model.GroupEntry;
import com.sysadminanywhere.directory.model.GroupScope;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.DefaultEntry;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.message.ModifyRequest;
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
    public GroupEntry add(String distinguishedName, GroupEntry group, GroupScope groupScope, boolean isSecurity) {
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
        ModifyRequest modifyRequest = resolveService.getModifyRequest(group, getByCN(group.getCn()));
        ldapService.update(modifyRequest);

        return getByCN(group.getCn());
    }

    @SneakyThrows
    public void delete(String distinguishedName) {
        Entry entry = new DefaultEntry(distinguishedName);
        ldapService.delete(entry);
    }

}