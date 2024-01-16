package com.sysadminanywhere.sysadminanywhere.service;

import com.sysadminanywhere.sysadminanywhere.domain.PrinterEntry;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return resolveService.getList(result);
    }

    public PrinterEntry getByCN(String cn) {
        List<Entry> result = ldapService.search("(&(objectClass=computer)(cn=" + cn + "))");
        Optional<Entry> entry = result.stream().findFirst();

        if (entry.isPresent())
            return resolveService.getValue(entry.get());
        else
            return null;
    }

    public PrinterEntry add(PrinterEntry printer) {
        ldapService.add(null);
        return null;
    }

    public PrinterEntry update(PrinterEntry printer) {
        return new PrinterEntry();
    }

    public void delete(PrinterEntry printer) {

    }

}