package com.sysadminanywhere.sysadminanywhere.service.users;

import com.sysadminanywhere.sysadminanywhere.domain.Computer;
import com.sysadminanywhere.sysadminanywhere.domain.User;
import com.sysadminanywhere.sysadminanywhere.service.search.SearchService;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService{

    private final SearchService searchService;

    public UsersServiceImpl(SearchService searchService) {
        this.searchService = searchService;
    }

    @SneakyThrows
    @Override
    public List<User> GetAll() {
        List<User> list = new ArrayList<>();
        List<Entry> result = searchService.Search("(&(objectClass=user)(objectCategory=person))");

        for (Entry entry : result) {
            list.add(new User(entry.getDn().getName(), entry.get("cn").getString()));
        }

        return list;
    }

}