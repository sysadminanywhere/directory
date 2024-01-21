package com.sysadminanywhere.directory.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sysadminanywhere.directory.model.ComputerEntry;

import java.util.ArrayList;
import java.util.List;

import org.apache.directory.api.ldap.model.entry.DefaultEntry;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.entry.ImmutableEntry;
import org.apache.directory.api.ldap.model.name.Dn;
import org.junit.jupiter.api.Disabled;
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

    /**
     * Method under test: {@link ComputersService#getAll()}
     */
    @Test
    void testGetAll() {
        // Arrange
        when(ldapService.search(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<ComputerEntry> actualAll = computersService.getAll();

        // Assert
        verify(ldapService).search(Mockito.<String>any());
        assertTrue(actualAll.isEmpty());
    }

    /**
     * Method under test: {@link ComputersService#getAll()}
     */
    @Test
    void testGetAll2() {
        // Arrange
        ArrayList<Entry> entryList = new ArrayList<>();
        entryList.add(new DefaultEntry());
        when(ldapService.search(Mockito.<String>any())).thenReturn(entryList);

        // Act
        List<ComputerEntry> actualAll = computersService.getAll();

        // Assert
        verify(ldapService).search(Mockito.<String>any());
        assertEquals(1, actualAll.size());
    }

    /**
     * Method under test: {@link ComputersService#getAll()}
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
        List<ComputerEntry> actualAll = computersService.getAll();

        // Assert
        verify(ldapService).search(Mockito.<String>any());
        verify(immutableEntry, atLeast(1)).get(Mockito.<String>any());
        verify(immutableEntry).getDn();
        assertEquals(1, actualAll.size());
    }

    /**
     * Method under test: {@link ComputersService#getByCN(String)}
     */
    @Test
    void testGetByCN() {
        // Arrange
        when(ldapService.search(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        ComputerEntry actualByCN = computersService.getByCN("Cn");

        // Assert
        verify(ldapService).search(Mockito.<String>any());
        assertNull(actualByCN);
    }

    /**
     * Method under test: {@link ComputersService#getByCN(String)}
     */
    @Test
    void testGetByCN2() {
        // Arrange
        ArrayList<Entry> entryList = new ArrayList<>();
        entryList.add(new DefaultEntry());
        when(ldapService.search(Mockito.<String>any())).thenReturn(entryList);

        // Act
        computersService.getByCN("Cn");

        // Assert
        verify(ldapService).search(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ComputersService#getByCN(String)}
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
        computersService.getByCN("Cn");

        // Assert
        verify(ldapService).search(Mockito.<String>any());
        verify(immutableEntry, atLeast(1)).get(Mockito.<String>any());
        verify(immutableEntry).getDn();
    }

    /**
     * Method under test:
     * {@link ComputersService#add(String, ComputerEntry, boolean)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAdd() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.apache.directory.api.ldap.model.exception.LdapInvalidDnException: ERR_13609_EQUAL_EXPECTED Unexpected character 'N' at position 23. Expected EQUALS '='.
        //       at org.apache.directory.api.ldap.model.name.FastDnParser.matchEquals(FastDnParser.java:577)
        //       at org.apache.directory.api.ldap.model.name.FastDnParser.parseRdnInternal(FastDnParser.java:183)
        //       at org.apache.directory.api.ldap.model.name.FastDnParser.parseDn(FastDnParser.java:113)
        //       at org.apache.directory.api.ldap.model.name.Dn.parseInternal(Dn.java:1259)
        //       at org.apache.directory.api.ldap.model.name.Dn.<init>(Dn.java:310)
        //       at org.apache.directory.api.ldap.model.entry.DefaultEntry.<init>(DefaultEntry.java:245)
        //       at org.apache.directory.api.ldap.model.entry.DefaultEntry.<init>(DefaultEntry.java:216)
        //       at com.sysadminanywhere.directory.service.ComputersService.add(ComputersService.java:56)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        computersService.add("Distinguished Name", new ComputerEntry(), true);
    }

    /**
     * Method under test: {@link ComputersService#update(ComputerEntry)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.toString()" because the return value of "java.lang.reflect.Field.get(Object)" is null
        //       at com.sysadminanywhere.directory.service.ResolveService.getEntry(ResolveService.java:111)
        //       at com.sysadminanywhere.directory.service.ResolveService.getModifyRequest(ResolveService.java:144)
        //       at com.sysadminanywhere.directory.service.ComputersService.update(ComputersService.java:64)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        when(ldapService.search(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        computersService.update(new ComputerEntry());
    }

    /**
     * Method under test: {@link ComputersService#delete(String)}
     */
    @Test
    void testDelete() {
        // Arrange
        doNothing().when(ldapService).delete(Mockito.<Entry>any());

        // Act
        computersService.delete("");

        // Assert
        verify(ldapService).delete(Mockito.<Entry>any());
    }
}
