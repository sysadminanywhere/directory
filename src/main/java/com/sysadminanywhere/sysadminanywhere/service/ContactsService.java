package com.sysadminanywhere.sysadminanywhere.service;

import com.sysadminanywhere.sysadminanywhere.domain.ContactEntry;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactsService {

    private final LdapService ldapService;

    ResolveService<ContactEntry> resolveService = new ResolveService<>(ContactEntry.class);

    public ContactsService(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @SneakyThrows
    public List<ContactEntry> getAll() {
        List<Entry> result = ldapService.search("(&(objectClass=contact)(objectCategory=person))");
        return resolveService.getList(result);
    }

    public ContactEntry getByCN(String cn) {
        List<Entry> result = ldapService.search("(&(objectClass=computer)(cn=" + cn + "))");
        Optional<Entry> entry = result.stream().findFirst();

        if (entry.isPresent())
            return resolveService.getValue(entry.get());
        else
            return null;
    }

    public ContactEntry add(ContactEntry contact) {
        ldapService.add(null);
        return null;
    }

    public ContactEntry update(ContactEntry contact) {
        return new ContactEntry();
    }

    public void delete(ContactEntry contact) {

    }

}