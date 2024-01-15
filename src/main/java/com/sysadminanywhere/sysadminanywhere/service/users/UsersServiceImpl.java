package com.sysadminanywhere.sysadminanywhere.service.users;

import com.sysadminanywhere.sysadminanywhere.domain.User;
import com.sysadminanywhere.sysadminanywhere.service.ldap.LdapService;
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

        for (Entry entry : result) {
            list.add(new User(entry.getDn().getName(), entry.get("cn").getString()));
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