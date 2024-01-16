package com.sysadminanywhere.sysadminanywhere.service;

import com.sysadminanywhere.sysadminanywhere.domain.ComputerEntry;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComputersService {

    private final LdapService ldapService;

    public ComputersService(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @SneakyThrows
    public List<ComputerEntry> getAll() {
        List<ComputerEntry> list = new ArrayList<>();
        List<Entry> result = ldapService.search("(objectClass=computer)");

        ResolveService<ComputerEntry> resolveService = new ResolveService<>(ComputerEntry.class);

        for (Entry entry : result) {
            ComputerEntry computer = resolveService.getValues(entry);
            list.add(computer);
        }

        return list;
    }

    public ComputerEntry getByDN(String dn) {
        return new ComputerEntry();
    }

    public ComputerEntry add(ComputerEntry computer) {
        ldapService.add(null);
        return new ComputerEntry();
    }

    public ComputerEntry update(ComputerEntry computer) {
        return new ComputerEntry();
    }

    public void delete(ComputerEntry computer) {

    }

}