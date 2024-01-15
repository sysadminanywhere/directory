package com.sysadminanywhere.sysadminanywhere.service;

import org.apache.directory.api.ldap.model.entry.Entry;

import java.util.List;

public interface LdapService {
    List<Entry> search(String filter);
    void add(Entry entry);
    void update(Entry entry);
    void delete(Entry entry);
}
