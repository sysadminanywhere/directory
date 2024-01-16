package com.sysadminanywhere.sysadminanywhere.service;

import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.message.SearchScope;

import java.util.List;

public interface LdapService {

    String DomainName();
    String DefaultNamingContext();

    List<Entry> search(String filter, SearchScope searchScope);
    List<Entry> search(String filter);
    void add(Entry entry);
    void update(Entry entry);
    void delete(Entry entry);

    String getComputersContainer();
    String getUsersContainer();
}
