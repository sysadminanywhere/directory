package com.sysadminanywhere.sysadminanywhere.service;

import com.sysadminanywhere.sysadminanywhere.domain.User;

import java.util.List;

public interface UsersService {
    List<User> getAll();
    User getByDN(String dn);
    User add(User user);
    User update(User user);
    void delete(User user);
}
