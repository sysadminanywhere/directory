package com.sysadminanywhere.sysadminanywhere.service;

import com.sysadminanywhere.sysadminanywhere.domain.Computer;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.cursor.SearchCursor;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.message.*;
import org.apache.directory.api.ldap.model.message.controls.*;
import org.apache.directory.api.ldap.model.name.Dn;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComputersService {

    @SneakyThrows
    public List<Computer> GetAll() {

        List<Computer> list = new ArrayList<>();

        LdapConnection connection = new LdapNetworkConnection("192.168.245.129", 389);

        BindRequest bindRequest = new BindRequestImpl();
        bindRequest.setCredentials("Secret2#");
        bindRequest.setSimple(true);
        bindRequest.setName("admin");
        connection.bind(bindRequest);

        SearchRequest searchRequest = new SearchRequestImpl();
        searchRequest.setScope(SearchScope.SUBTREE);
        searchRequest.addAttributes("*");
        searchRequest.setTimeLimit(0);
        searchRequest.setBase(new Dn("DC=example,DC=com"));

        searchRequest.setFilter("(objectClass=computer)");

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
                        list.add(new Computer(resultEntry.getDn().getName(), resultEntry.get("cn").getString()));
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

        connection.unBind();
        connection.close();

        return list;
    }

}