package com.sysadminanywhere.directory.service;

import com.sysadminanywhere.directory.model.ComputerEntry;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.DefaultEntry;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComputersService {

    private final LdapService ldapService;

    ResolveService<ComputerEntry> resolveService = new ResolveService<>(ComputerEntry.class);

    public ComputersService(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @SneakyThrows
    public List<ComputerEntry> getAll() {
        List<Entry> result = ldapService.search("(objectClass=computer)");
        return resolveService.getADList(result);
    }

    public ComputerEntry getByCN(String cn) {
        List<Entry> result = ldapService.search("(&(objectClass=computer)(cn=" + cn + "))");
        Optional<Entry> entry = result.stream().findFirst();

        if (entry.isPresent())
            return resolveService.getADValue(entry.get());
        else
            return null;
    }

    @SneakyThrows
    public ComputerEntry add(String distinguishedName, ComputerEntry computer) {
        String dn;

        if(distinguishedName.isEmpty()) {
            dn = "cn=" + computer.getCn() + "," + ldapService.getComputersContainer();
        } else {
            dn = "cn=" + computer.getCn() + "," + distinguishedName;
        }

        Entry entry = new DefaultEntry(
                dn,
                "description", computer.getDescription(),
                "location", computer.getLocation(),
                "sAMAccountName", computer.getSamAccountName(),
                "objectclass:computer",
                "cn", computer.getCn()
        );

        ldapService.add(entry);
        return getByCN(computer.getCn());
    }

    public ComputerEntry update(ComputerEntry computer) {
        return new ComputerEntry();
    }

    @SneakyThrows
    public void delete(ComputerEntry computer) {
        Entry entry = new DefaultEntry(computer.getDistinguishedName());
        ldapService.delete(entry);
    }

}