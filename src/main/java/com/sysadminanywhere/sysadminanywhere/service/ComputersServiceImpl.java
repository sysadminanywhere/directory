package com.sysadminanywhere.sysadminanywhere.service;

import com.sysadminanywhere.sysadminanywhere.domain.Computer;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComputersServiceImpl implements ComputersService {

    private final LdapService ldapService;

    public ComputersServiceImpl(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @SneakyThrows
    @Override
    public List<Computer> getAll() {
        List<Computer> list = new ArrayList<>();
        List<Entry> result = ldapService.search("(objectClass=computer)");

        ResolveService<Computer> resolveService = new ResolveService<>(Computer.class);

        for (Entry entry : result) {
            Computer computer = resolveService.getValues(entry);
            //list.add(new Computer(entry.getDn().getName(), entry.get("cn").getString()));
        }

        return list;
    }

    @Override
    public Computer getByDN(String dn) {
        return new Computer();
    }

    @Override
    public Computer add(Computer computer) {
        ldapService.add(null);
        return new Computer();
    }

    @Override
    public Computer update(Computer computer) {
        return new Computer();
    }

    @Override
    public void delete(Computer computer) {

    }

}