package com.sysadminanywhere.sysadminanywhere.service.computers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sysadminanywhere.sysadminanywhere.domain.ComputerEntry;
import com.sysadminanywhere.sysadminanywhere.service.ComputersService;
import com.sysadminanywhere.sysadminanywhere.service.LdapService;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ComputersService.class})
@ExtendWith(SpringExtension.class)
class ComputersServiceTest {
    @Autowired
    private ComputersService computersService;

    @MockBean
    private LdapService ldapService;

    @Test
    void testGetAll() {
        when(ldapService.search(Mockito.<String>any())).thenReturn(new ArrayList<>());
        List<ComputerEntry> actualGetAllResult = computersService.getAll();
        verify(ldapService).search(Mockito.<String>any());
        assertTrue(actualGetAllResult.isEmpty());
    }
}
