package com.sysadminanywhere.directory.service;

import com.sysadminanywhere.directory.model.ComputerEntry;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.DefaultEntry;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComputersService {

    private final LdapService ldapService;

    ResolveService<ComputerEntry> resolveService = new ResolveService<>(ComputerEntry.class);

    public ComputersService(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @SneakyThrows
    public List<ComputerEntry> getAll() {
        List<Entry> result = ldapService.search("(objectClass=computer)");
        return resolveService.getADList(result);
    }

    public ComputerEntry getByCN(String cn) {
        List<Entry> result = ldapService.search("(&(objectClass=computer)(cn=" + cn + "))");
        Optional<Entry> entry = result.stream().findFirst();

        if (entry.isPresent())
            return resolveService.getADValue(entry.get());
        else
            return null;
    }

    public ComputerEntry add(ComputerEntry computer) {
        ldapService.add(null);
        return new ComputerEntry();
    }

    public ComputerEntry update(ComputerEntry computer) {
        return new ComputerEntry();
    }

    @SneakyThrows
    public void delete(ComputerEntry computer) {
        Entry entry = new DefaultEntry(computer.getDistinguishedName());
        ldapService.delete(entry);
    }

}