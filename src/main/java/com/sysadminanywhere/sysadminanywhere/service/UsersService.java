package com.sysadminanywhere.sysadminanywhere.service;

import com.sysadminanywhere.sysadminanywhere.domain.UserEntry;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    private final LdapService ldapService;

    public UsersService(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @SneakyThrows
    public List<UserEntry> getAll() {
        List<UserEntry> list = new ArrayList<>();
        List<Entry> result = ldapService.search("(&(objectClass=user)(objectCategory=person))");

        ResolveService<UserEntry> resolveService = new ResolveService<>(UserEntry.class);

        for (Entry entry : result) {
            UserEntry user = resolveService.getValues(entry);
            list.add(user);
        }

        return list;
    }

    public UserEntry getByDN(String dn) {
        return new UserEntry();
    }

    public UserEntry add(UserEntry user) {
        ldapService.add(null);
        return null;
    }

    public UserEntry update(UserEntry user) {
        return new UserEntry();
    }

    public void delete(UserEntry user) {

    }

}