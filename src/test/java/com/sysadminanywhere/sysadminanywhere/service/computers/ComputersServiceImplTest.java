package com.sysadminanywhere.sysadminanywhere.service.computers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sysadminanywhere.sysadminanywhere.domain.Computer;
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

@ContextConfiguration(classes = {ComputersServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ComputersServiceImplTest {
    @Autowired
    private ComputersServiceImpl computersServiceImpl;

    @MockBean
    private LdapService ldapService;

    @Test
    void testGetAll() {
        when(ldapService.search(Mockito.<String>any())).thenReturn(new ArrayList<>());
        List<Computer> actualGetAllResult = computersServiceImpl.getAll();
        verify(ldapService).search(Mockito.<String>any());
        assertTrue(actualGetAllResult.isEmpty());
    }
}
