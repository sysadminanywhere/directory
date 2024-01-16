package com.sysadminanywhere.sysadminanywhere.service;

import com.sysadminanywhere.sysadminanywhere.domain.GroupEntry;
import com.sysadminanywhere.sysadminanywhere.domain.GroupEntry;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupsService {

    private final LdapService ldapService;

    public GroupsService(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @SneakyThrows
    public List<GroupEntry> getAll() {
        List<GroupEntry> list = new ArrayList<>();
        List<Entry> result = ldapService.search("(objectClass=group)");

        ResolveService<GroupEntry> resolveService = new ResolveService<>(GroupEntry.class);

        for (Entry entry : result) {
            GroupEntry group = resolveService.getValues(entry);
            list.add(group);
        }

        return list;
    }

    public GroupEntry getByDN(String dn) {
        return new GroupEntry();
    }

    public GroupEntry add(GroupEntry group) {
        ldapService.add(null);
        return new GroupEntry();
    }

    public GroupEntry update(GroupEntry group) {
        return new GroupEntry();
    }

    public void delete(GroupEntry group) {

    }

}