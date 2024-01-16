package com.sysadminanywhere.sysadminanywhere.service;

import com.sysadminanywhere.sysadminanywhere.domain.User;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private final LdapService ldapService;

    public UsersServiceImpl(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @SneakyThrows
    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        List<Entry> result = ldapService.search("(&(objectClass=user)(objectCategory=person))");

        ResolveService<User> resolveService = new ResolveService<>(User.class);

        for (Entry entry : result) {
            User user = resolveService.getValues(entry);
            list.add(user);
        }

        return list;
    }

    @Override
    public User getByDN(String dn) {
        return new User();
    }

    @Override
    public User add(User user) {
        ldapService.add(null);
        return null;
    }

    @Override
    public User update(User user) {
        return new User();
    }

    @Override
    public void delete(User user) {

    }

}