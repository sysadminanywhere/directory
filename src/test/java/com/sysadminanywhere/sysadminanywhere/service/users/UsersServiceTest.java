package com.sysadminanywhere.sysadminanywhere.service.users;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sysadminanywhere.sysadminanywhere.domain.UserEntry;
import com.sysadminanywhere.sysadminanywhere.service.LdapService;

import java.util.ArrayList;
import java.util.List;

import com.sysadminanywhere.sysadminanywhere.service.UsersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UsersService.class})
@ExtendWith(SpringExtension.class)
class UsersServiceTest {
    @MockBean
    private LdapService ldapService;

    @Autowired
    private UsersService usersService;

    /**
     * Method under test: {@link UsersService#getAll()}
     */
    @Test
    void testGetAll() {
        when(ldapService.search(Mockito.<String>any())).thenReturn(new ArrayList<>());
        List<UserEntry> actualGetAllResult = usersService.getAll();
        verify(ldapService).search(Mockito.<String>any());
        assertTrue(actualGetAllResult.isEmpty());
    }
}
