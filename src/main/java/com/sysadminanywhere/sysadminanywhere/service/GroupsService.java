package com.sysadminanywhere.sysadminanywhere.service;

import com.sysadminanywhere.sysadminanywhere.model.GroupEntry;
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

    public GroupEntry add(GroupEntry group) {
        ldapService.add(null);
        return new GroupEntry();
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