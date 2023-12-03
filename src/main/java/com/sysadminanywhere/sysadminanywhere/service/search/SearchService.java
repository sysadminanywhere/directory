package com.sysadminanywhere.sysadminanywhere.service.search;

import org.apache.directory.api.ldap.model.entry.Entry;

import java.util.List;

public interface SearchService {

    List<Entry> Search(String filter);

}
