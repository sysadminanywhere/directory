package com.sysadminanywhere.sysadminanywhere.service;

import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.cursor.SearchCursor;
import org.apache.directory.api.ldap.model.entry.DefaultEntry;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.*;
import org.apache.directory.api.ldap.model.message.controls.*;
import org.apache.directory.api.ldap.model.name.Dn;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LdapServiceImpl implements LdapService {

    private final LdapConnection connection;
    private String domainName;
    private String defaultNamingContext;
    private Dn baseDn;

    @SneakyThrows
    public LdapServiceImpl(LdapConnection connection) {
        this.connection = connection;

        Entry entry = connection.getRootDse();
        baseDn = new Dn(entry.get("rootdomainnamingcontext").get().getString());
        defaultNamingContext = baseDn.getName();
        domainName = defaultNamingContext.toUpperCase().replace("DC=", "").replace(",", ".").toLowerCase();
        System.out.println();
    }

    @Override
    public String DefaultNamingContext() {
        return defaultNamingContext;
    }

    @Override
    public String DomainName() {
        return domainName;
    }

    @SneakyThrows
    @Override
    public List<Entry> search(String filter) {

        List<Entry> list = new ArrayList<>();

        try {
            SearchRequest searchRequest = new SearchRequestImpl();
            searchRequest.setScope(SearchScope.SUBTREE);
            searchRequest.addAttributes("*");
            searchRequest.setTypesOnly(false);
            searchRequest.setTimeLimit(0);
            searchRequest.setBase(baseDn);

            searchRequest.setFilter(filter);

            int pageSize = 100;

            SortRequest sortRequest = new SortRequestImpl();
            sortRequest.addSortKey(new SortKey("cn"));
            searchRequest.addControl(sortRequest);

            PagedResults pagedResults = new PagedResultsImpl();
            pagedResults.setSize(pageSize);
            searchRequest.addControl(pagedResults);

            while (true) {
                try (SearchCursor searchCursor = connection.search(searchRequest)) {
                    while (searchCursor.next()) {
                        Response response = searchCursor.get();
                        if (response instanceof SearchResultEntry) {
                            Entry resultEntry = ((SearchResultEntry) response).getEntry();
                            list.add(resultEntry);
                        }
                    }
                    SearchResultDone resultDone = searchCursor.getSearchResultDone();
                    if (resultDone != null) {
                        var pageResultResponseControl = (PagedResults) resultDone.getControl(PagedResults.OID);
                        if (pageResultResponseControl == null || pageResultResponseControl.getCookie().length == 0) {
                            break;
                        } else {
                            pagedResults.setCookie(pageResultResponseControl.getCookie());
                        }
                    }
                }
            }

        } catch (LdapException le) {

        }

        return list;

    }

    @SneakyThrows
    public void add(Entry entry) {
        AddRequest addRequest = new AddRequestImpl();
        addRequest.setEntry(entry);

        connection.add(addRequest);
    }

    @SneakyThrows
    @Override
    public void update(Entry entry) {
//        ModifyRequest modifyRequest = new ModifyRequestImpl();
//        modifyRequest.setEntry(entry);
//
//        connection.modify(modifyRequest);
    }

    @SneakyThrows
    @Override
    public void delete(Entry entry) {
        connection.delete(entry.getDn());
    }

}