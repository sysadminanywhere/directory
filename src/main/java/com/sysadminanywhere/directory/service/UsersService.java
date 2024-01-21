package com.sysadminanywhere.directory.service;

import com.sysadminanywhere.directory.model.UserEntry;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.DefaultEntry;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.springframework.stereotype.Service;

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
        return resolveService.getADList(result);
    }

    public UserEntry getByCN(String cn) {
        List<Entry> result = ldapService.search("(&(objectClass=computer)(cn=" + cn + "))");
        Optional<Entry> entry = result.stream().findFirst();

        if (entry.isPresent())
            return resolveService.getADValue(entry.get());
        else
            return null;
    }

    @SneakyThrows
    public UserEntry add(String distinguishedName,
                         UserEntry user,
                         String password,
                         boolean isCannotChangePassword,
                         boolean isPasswordNeverExpires,
                         boolean isAccountDisabled,
                         boolean isMustChangePassword) {

        if (user.getUserPrincipalName().isEmpty())
            user.setUserPrincipalName(user.getSamAccountName() + "@" + ldapService.DomainName());

        String dn;

        if(distinguishedName.isEmpty()) {
            dn = "cn=" + user.getCn() + "," + ldapService.getUsersContainer();
        } else {
            dn = "cn=" + user.getCn() + "," + distinguishedName;
        }

        Entry entry = new DefaultEntry(
                dn,
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

    @SneakyThrows
    public UserEntry update(UserEntry user) {
        Entry entry = resolveService.getEntry(user);
        Entry oldEntry = resolveService.getEntry(getByCN(user.getCn()));

        ldapService.update(resolveService.getModifyRequest(entry, oldEntry));
        return getByCN(user.getCn());
    }

    @SneakyThrows
    public void delete(String distinguishedName) {
        Entry entry = new DefaultEntry(distinguishedName);
        ldapService.delete(entry);
    }

}