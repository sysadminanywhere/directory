package com.sysadminanywhere.sysadminanywhere.service.computers;

import com.sysadminanywhere.sysadminanywhere.domain.Computer;

import java.util.List;

public interface ComputersService {
    List<Computer> getAll();
    Computer getByDN(String dn);
    Computer add(Computer computer);
    Computer update(Computer computer);
    void delete(Computer computer);
}
