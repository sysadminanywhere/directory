package com.sysadminanywhere.sysadminanywhere.service;

import com.sysadminanywhere.sysadminanywhere.domain.UserEntry;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.DefaultEntry;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final LdapService ldapService;

    ResolveService<UserEntry> resolveService = new ResolveService<>(UserEntry.class);

    public UsersService(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @SneakyThrows
    public List<UserEntry> getAll() {
        List<Entry> result = ldapService.search("(&(objectClass=user)(objectCategory=person))");
        return resolveService.getList(result);
    }

    public UserEntry getByCN(String cn) {
        List<Entry> result = ldapService.search("(&(objectClass=computer)(cn=" + cn + "))");
        Optional<Entry> entry = result.stream().findFirst();

        if (entry.isPresent())
            return resolveService.getValue(entry.get());
        else
            return null;
    }

    @SneakyThrows
    public UserEntry add(UserEntry user, String password) {

        if (user.getUserPrincipalName().isEmpty())
            user.setUserPrincipalName(user.getSamAccountName() + "@" + ldapService.DomainName());

        String cn = user.getCn();

        Entry entry = new DefaultEntry(
                "cn=" + cn,
                "displayName", user.getDisplayName(),
                "initials", user.getInitials(),
                "givenName", user.getFirstName(),
                "sn", user.getLastName(),
                "sAMAccountName", user.getSamAccountName(),
                "userPrincipalName", user.getUserPrincipalName(),
                "objectclass:user",
                "objectclass:person",
                "cn", user.getCn(),
                "userPassword", password
        );

        ldapService.add(entry);
        return getByCN(user.getCn());
    }

    public UserEntry update(UserEntry user) {
        return new UserEntry();
    }

    public void delete(UserEntry user) {

    }

}