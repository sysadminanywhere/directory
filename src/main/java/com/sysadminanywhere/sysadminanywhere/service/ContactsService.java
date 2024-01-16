package com.sysadminanywhere.sysadminanywhere.service;

import com.sysadminanywhere.sysadminanywhere.domain.ContactEntry;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactsService {

    private final LdapService ldapService;

    public ContactsService(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @SneakyThrows
    public List<ContactEntry> getAll() {
        List<ContactEntry> list = new ArrayList<>();
        List<Entry> result = ldapService.search("(&(objectClass=contact)(objectCategory=person))");

        ResolveService<ContactEntry> resolveService = new ResolveService<>(ContactEntry.class);

        for (Entry entry : result) {
            ContactEntry contact = resolveService.getValues(entry);
            list.add(contact);
        }

        return list;
    }

    public ContactEntry getByDN(String dn) {
        return new ContactEntry();
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