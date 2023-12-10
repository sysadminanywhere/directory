package com.sysadminanywhere.sysadminanywhere.service.users;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sysadminanywhere.sysadminanywhere.domain.User;
import com.sysadminanywhere.sysadminanywhere.service.ldap.LdapService;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UsersServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UsersServiceImplTest {
    @MockBean
    private LdapService ldapService;

    @Autowired
    private UsersServiceImpl usersServiceImpl;

    /**
     * Method under test: {@link UsersServiceImpl#getAll()}
     */
    @Test
    void testGetAll() {
        when(ldapService.search(Mockito.<String>any())).thenReturn(new ArrayList<>());
        List<User> actualGetAllResult = usersServiceImpl.getAll();
        verify(ldapService).search(Mockito.<String>any());
        assertTrue(actualGetAllResult.isEmpty());
    }
}
