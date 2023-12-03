package com.sysadminanywhere.sysadminanywhere.service.computers;

import com.sysadminanywhere.sysadminanywhere.domain.Computer;
import com.sysadminanywhere.sysadminanywhere.service.search.SearchService;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ComputersServiceImpl implements ComputersService {

    private final SearchService searchService;

    public ComputersServiceImpl(SearchService searchService) {
        this.searchService = searchService;
    }

    @SneakyThrows
    @Override
    public List<Computer> GetAll() {
        List<Computer> list = new ArrayList<>();
        List<Entry> result = searchService.Search("(objectClass=computer)");

        for (Entry entry : result) {
            list.add(new Computer(entry.getDn().getName(), entry.get("cn").getString()));
        }

        return list;
    }

}