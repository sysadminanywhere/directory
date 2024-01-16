package com.sysadminanywhere.sysadminanywhere.service;

import com.sysadminanywhere.sysadminanywhere.domain.PrinterEntry;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrintersService {

    private final LdapService ldapService;

    public PrintersService(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @SneakyThrows
    public List<PrinterEntry> getAll() {
        List<PrinterEntry> list = new ArrayList<>();
        List<Entry> result = ldapService.search("(objectClass=printQueue)");

        ResolveService<PrinterEntry> resolveService = new ResolveService<>(PrinterEntry.class);

        for (Entry entry : result) {
            PrinterEntry printer = resolveService.getValues(entry);
            list.add(printer);
        }

        return list;
    }

    public PrinterEntry getByDN(String dn) {
        return new PrinterEntry();
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