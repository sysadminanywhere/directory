package com.sysadminanywhere.sysadminanywhere.service.search;

import com.sysadminanywhere.sysadminanywhere.config.LdapConfig;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.cursor.SearchCursor;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.message.*;
import org.apache.directory.api.ldap.model.message.controls.*;
import org.apache.directory.api.ldap.model.name.Dn;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    private final LdapConnection connection;

    public SearchServiceImpl(LdapConnection connection) {
        this.connection = connection;
    }

    @SneakyThrows
    @Override
    public List<Entry> Search(String filter) {

        List<Entry> list = new ArrayList<>();

        Entry entry = connection.getRootDse();
        Dn baseDn = new Dn(entry.get("rootdomainnamingcontext").get().getString());

        SearchRequest searchRequest = new SearchRequestImpl();
        searchRequest.setScope(SearchScope.SUBTREE);
        searchRequest.addAttributes("*");
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

//        connection.unBind();
//        connection.close();

        return list;
    }

}