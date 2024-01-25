package com.sysadminanywhere.directory.service;

import com.sysadminanywhere.directory.model.PrinterEntry;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.DefaultEntry;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrintersService {

    private final LdapService ldapService;

    ResolveService<PrinterEntry> resolveService = new ResolveService<>(PrinterEntry.class);

    public PrintersService(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @SneakyThrows
    public List<PrinterEntry> getAll() {
        List<Entry> result = ldapService.search("(objectClass=printQueue)");
        return resolveService.getADList(result);
    }

    public PrinterEntry getByCN(String cn) {
        List<Entry> result = ldapService.search("(&(objectClass=printQueue)(cn=" + cn + "))");
        Optional<Entry> entry = result.stream().findFirst();

        if (entry.isPresent())
            return resolveService.getADValue(entry.get());
        else
            return null;
    }

    @SneakyThrows
    public void delete(String distinguishedName) {
        Entry entry = new DefaultEntry(distinguishedName);
        ldapService.delete(entry);
    }

}