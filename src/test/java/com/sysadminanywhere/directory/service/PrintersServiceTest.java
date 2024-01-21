package com.sysadminanywhere.directory.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sysadminanywhere.directory.model.PrinterEntry;

import java.util.ArrayList;
import java.util.List;

import org.apache.directory.api.ldap.model.entry.DefaultEntry;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.entry.ImmutableEntry;
import org.apache.directory.api.ldap.model.name.Dn;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PrintersService.class})
@ExtendWith(SpringExtension.class)
class PrintersServiceTest {
    @MockBean
    private LdapService ldapService;

    @Autowired
    private PrintersService printersService;

    /**
     * Method under test: {@link PrintersService#getAll()}
     */
    @Test
    void testGetAll() {
        // Arrange
        when(ldapService.search(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<PrinterEntry> actualAll = printersService.getAll();

        // Assert
        verify(ldapService).search(Mockito.<String>any());
        assertTrue(actualAll.isEmpty());
    }

    /**
     * Method under test: {@link PrintersService#getAll()}
     */
    @Test
    void testGetAll2() {
        // Arrange
        ArrayList<Entry> entryList = new ArrayList<>();
        entryList.add(new DefaultEntry());
        when(ldapService.search(Mockito.<String>any())).thenReturn(entryList);

        // Act
        List<PrinterEntry> actualAll = printersService.getAll();

        // Assert
        verify(ldapService).search(Mockito.<String>any());
        assertEquals(1, actualAll.size());
    }

    /**
     * Method under test: {@link PrintersService#getAll()}
     */
    @Test
    void testGetAll3() {
        // Arrange
        ImmutableEntry immutableEntry = mock(ImmutableEntry.class);
        when(immutableEntry.get(Mockito.<String>any())).thenReturn(null);
        when(immutableEntry.getDn()).thenReturn(new Dn());

        ArrayList<Entry> entryList = new ArrayList<>();
        entryList.add(immutableEntry);
        when(ldapService.search(Mockito.<String>any())).thenReturn(entryList);

        // Act
        List<PrinterEntry> actualAll = printersService.getAll();

        // Assert
        verify(ldapService).search(Mockito.<String>any());
        verify(immutableEntry, atLeast(1)).get(Mockito.<String>any());
        verify(immutableEntry).getDn();
        assertEquals(1, actualAll.size());
    }

    /**
     * Method under test: {@link PrintersService#getByCN(String)}
     */
    @Test
    void testGetByCN() {
        // Arrange
        when(ldapService.search(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        PrinterEntry actualByCN = printersService.getByCN("Cn");

        // Assert
        verify(ldapService).search(Mockito.<String>any());
        assertNull(actualByCN);
    }

    /**
     * Method under test: {@link PrintersService#getByCN(String)}
     */
    @Test
    void testGetByCN2() {
        // Arrange
        ArrayList<Entry> entryList = new ArrayList<>();
        entryList.add(new DefaultEntry());
        when(ldapService.search(Mockito.<String>any())).thenReturn(entryList);

        // Act
        printersService.getByCN("Cn");

        // Assert
        verify(ldapService).search(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PrintersService#getByCN(String)}
     */
    @Test
    void testGetByCN3() {
        // Arrange
        ImmutableEntry immutableEntry = mock(ImmutableEntry.class);
        when(immutableEntry.get(Mockito.<String>any())).thenReturn(null);
        when(immutableEntry.getDn()).thenReturn(new Dn());

        ArrayList<Entry> entryList = new ArrayList<>();
        entryList.add(immutableEntry);
        when(ldapService.search(Mockito.<String>any())).thenReturn(entryList);

        // Act
        printersService.getByCN("Cn");

        // Assert
        verify(ldapService).search(Mockito.<String>any());
        verify(immutableEntry, atLeast(1)).get(Mockito.<String>any());
        verify(immutableEntry).getDn();
    }

    /**
     * Method under test: {@link PrintersService#delete(String)}
     */
    @Test
    void testDelete() {
        // Arrange
        doNothing().when(ldapService).delete(Mockito.<Entry>any());

        // Act
        printersService.delete("");

        // Assert
        verify(ldapService).delete(Mockito.<Entry>any());
    }
}
