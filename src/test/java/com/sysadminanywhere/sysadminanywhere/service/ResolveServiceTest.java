package com.sysadminanywhere.sysadminanywhere.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.directory.api.ldap.model.entry.Attribute;
import org.apache.directory.api.ldap.model.entry.DefaultAttribute;
import org.apache.directory.api.ldap.model.entry.DefaultEntry;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.entry.ImmutableEntry;
import org.apache.directory.api.ldap.model.message.MessageTypeEnum;
import org.apache.directory.api.ldap.model.message.ModifyRequest;
import org.apache.directory.api.ldap.model.name.Dn;
import org.apache.directory.api.ldap.model.name.Rdn;
import org.junit.jupiter.api.Test;

class ResolveServiceTest {

    @Test
    void testGetADList() {
        Class<Object> typeArgumentClass = Object.class;
        ResolveService<Object> resolveService = new ResolveService<>(typeArgumentClass);

        assertTrue(resolveService.getADList(new ArrayList<>()).isEmpty());
    }

    @Test
    void testGetADList2() {
        Class<Object> typeArgumentClass = Object.class;
        ResolveService<Object> resolveService = new ResolveService<>(typeArgumentClass);

        ArrayList<Entry> list = new ArrayList<>();
        list.add(new DefaultEntry());

        assertEquals(1, resolveService.getADList(list).size());
    }

    @Test
    void testGetADList3() {
        Class<Object> typeArgumentClass = Object.class;
        ResolveService<Object> resolveService = new ResolveService<>(typeArgumentClass);

        ArrayList<Entry> list = new ArrayList<>();
        list.add(mock(ImmutableEntry.class));

        assertEquals(1, resolveService.getADList(list).size());
    }

    @Test
    void testGetADValue() {
        Class<Object> typeArgumentClass = Object.class;
        ResolveService<Object> resolveService = new ResolveService<>(typeArgumentClass);
        DefaultEntry entry = new DefaultEntry();

        resolveService.getADValue(entry);

        Entry entry2 = resolveService.getEntry("Item");
        assertEquals(0, entry2.size());
        assertFalse(entry2.isSchemaAware());
        assertTrue(entry2.getAttributes().isEmpty());
        assertEquals(entry, entry2);
        Dn expectedDn = entry.getDn();
        assertSame(expectedDn, entry2.getDn());
    }

    @Test
    void testGetADValue2() {
        Class<Object> typeArgumentClass = Object.class;
        ResolveService<Object> resolveService = new ResolveService<>(typeArgumentClass);

        resolveService.getADValue(mock(ImmutableEntry.class));

        Entry entry = resolveService.getEntry("Item");
        Dn dn = entry.getDn();
        assertEquals("", dn.getEscaped());
        assertEquals("", dn.getName());
        assertEquals("", dn.getNormName());
        Rdn rdn = dn.getRdn();
        assertEquals("", rdn.getEscaped());
        assertEquals("", rdn.getName());
        assertEquals("", rdn.getNormName());
        assertEquals(0, entry.size());
        assertEquals(0, dn.size());
        assertEquals(0, rdn.size());
        assertFalse(entry.isSchemaAware());
        assertFalse(dn.isSchemaAware());
        assertFalse(rdn.isSchemaAware());
        assertTrue(entry.getAttributes().isEmpty());
        assertTrue(dn.getRdns().isEmpty());
        assertTrue(dn.isEmpty());
        assertTrue(dn.isRootDse());
        assertSame(dn, dn.getParent());
    }

    @Test
    void testGetEntry() {
        Class<Object> typeArgumentClass = Object.class;
        ResolveService<Object> resolveService = new ResolveService<>(typeArgumentClass);

        Entry actualEntry = resolveService.getEntry("Item");

        assertEquals(0, actualEntry.size());
        assertFalse(actualEntry.isSchemaAware());
    }

    @Test
    void testGetModifyRequest() {
        Class<Object> typeArgumentClass = Object.class;
        ResolveService<Object> resolveService = new ResolveService<>(typeArgumentClass);
        DefaultEntry newEntry = new DefaultEntry();

        ModifyRequest actualModifyRequest = resolveService.getModifyRequest(newEntry, new DefaultEntry());

        assertEquals(-1, actualModifyRequest.getMessageId());
        assertEquals(MessageTypeEnum.MODIFY_REQUEST, actualModifyRequest.getType());
        assertFalse(actualModifyRequest.isAbandoned());
        assertTrue(actualModifyRequest.getModifications().isEmpty());
        assertTrue(actualModifyRequest.getControls().isEmpty());
        assertTrue(actualModifyRequest.hasResponse());
    }

    void testGetModifyRequest2() {
        Class<Object> typeArgumentClass = Object.class;
        ResolveService<Object> resolveService = new ResolveService<>(typeArgumentClass);
        ImmutableEntry newEntry = mock(ImmutableEntry.class);
        when(newEntry.getAttributes()).thenReturn(new ArrayList<>());

        ModifyRequest actualModifyRequest = resolveService.getModifyRequest(newEntry, new DefaultEntry());

        verify(newEntry).getAttributes();
        assertEquals(-1, actualModifyRequest.getMessageId());
        assertEquals(MessageTypeEnum.MODIFY_REQUEST, actualModifyRequest.getType());
        assertFalse(actualModifyRequest.isAbandoned());
        assertTrue(actualModifyRequest.getModifications().isEmpty());
        assertTrue(actualModifyRequest.getControls().isEmpty());
        assertTrue(actualModifyRequest.hasResponse());
    }

    @Test
    void testGetModifyRequest3() {
        Class<Object> typeArgumentClass = Object.class;
        ResolveService<Object> resolveService = new ResolveService<>(typeArgumentClass);

        ArrayList<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new DefaultAttribute("42"));
        ImmutableEntry newEntry = mock(ImmutableEntry.class);
        when(newEntry.getAttributes()).thenReturn(attributeList);

        ModifyRequest actualModifyRequest = resolveService.getModifyRequest(newEntry, new DefaultEntry());

        verify(newEntry).getAttributes();
        assertEquals(-1, actualModifyRequest.getMessageId());
        assertEquals(1, actualModifyRequest.getModifications().size());
        assertEquals(MessageTypeEnum.MODIFY_REQUEST, actualModifyRequest.getType());
        assertFalse(actualModifyRequest.isAbandoned());
        assertTrue(actualModifyRequest.getControls().isEmpty());
        assertTrue(actualModifyRequest.hasResponse());
    }

    @Test
    void testGetModifyRequest4() {
        Class<Object> typeArgumentClass = Object.class;
        ResolveService<Object> resolveService = new ResolveService<>(typeArgumentClass);

        ArrayList<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new DefaultAttribute("42"));
        attributeList.add(new DefaultAttribute("42"));
        ImmutableEntry newEntry = mock(ImmutableEntry.class);
        when(newEntry.getAttributes()).thenReturn(attributeList);

        ModifyRequest actualModifyRequest = resolveService.getModifyRequest(newEntry, new DefaultEntry());

        verify(newEntry).getAttributes();
        assertEquals(-1, actualModifyRequest.getMessageId());
        assertEquals(2, actualModifyRequest.getModifications().size());
        assertEquals(MessageTypeEnum.MODIFY_REQUEST, actualModifyRequest.getType());
        assertFalse(actualModifyRequest.isAbandoned());
        assertTrue(actualModifyRequest.getControls().isEmpty());
        assertTrue(actualModifyRequest.hasResponse());
    }

    @Test
    void testGetModifyRequest5() {
        Class<Object> typeArgumentClass = Object.class;
        ResolveService<Object> resolveService = new ResolveService<>(typeArgumentClass);
        ImmutableEntry newEntry = mock(ImmutableEntry.class);
        when(newEntry.getAttributes()).thenReturn(new ArrayList<>());
        ImmutableEntry oldEntry = mock(ImmutableEntry.class);
        when(oldEntry.getAttributes()).thenReturn(new ArrayList<>());

        ModifyRequest actualModifyRequest = resolveService.getModifyRequest(newEntry, oldEntry);

        verify(newEntry).getAttributes();
        verify(oldEntry).getAttributes();
        assertEquals(-1, actualModifyRequest.getMessageId());
        assertEquals(MessageTypeEnum.MODIFY_REQUEST, actualModifyRequest.getType());
        assertFalse(actualModifyRequest.isAbandoned());
        assertTrue(actualModifyRequest.getModifications().isEmpty());
        assertTrue(actualModifyRequest.getControls().isEmpty());
        assertTrue(actualModifyRequest.hasResponse());
    }

    @Test
    void testGetModifyRequest6() {
        Class<Object> typeArgumentClass = Object.class;
        ResolveService<Object> resolveService = new ResolveService<>(typeArgumentClass);

        ArrayList<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new DefaultAttribute("42"));
        ImmutableEntry newEntry = mock(ImmutableEntry.class);
        when(newEntry.getAttributes()).thenReturn(attributeList);
        ImmutableEntry oldEntry = mock(ImmutableEntry.class);
        when(oldEntry.contains(isA(Attribute[].class))).thenReturn(true);
        when(oldEntry.getAttributes()).thenReturn(new ArrayList<>());

        ModifyRequest actualModifyRequest = resolveService.getModifyRequest(newEntry, oldEntry);

        verify(oldEntry).contains(isA(Attribute[].class));
        verify(newEntry).getAttributes();
        verify(oldEntry).getAttributes();
        assertEquals(-1, actualModifyRequest.getMessageId());
        assertEquals(1, actualModifyRequest.getModifications().size());
        assertEquals(MessageTypeEnum.MODIFY_REQUEST, actualModifyRequest.getType());
        assertFalse(actualModifyRequest.isAbandoned());
        assertTrue(actualModifyRequest.getControls().isEmpty());
        assertTrue(actualModifyRequest.hasResponse());
    }

    @Test
    void testGetModifyRequest7() {
        Class<Object> typeArgumentClass = Object.class;
        ResolveService<Object> resolveService = new ResolveService<>(typeArgumentClass);

        ArrayList<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new DefaultAttribute("42"));
        ImmutableEntry newEntry = mock(ImmutableEntry.class);
        when(newEntry.contains(isA(Attribute[].class))).thenReturn(true);
        when(newEntry.getAttributes()).thenReturn(attributeList);

        ArrayList<Attribute> attributeList2 = new ArrayList<>();
        attributeList2.add(new DefaultAttribute("42"));
        ImmutableEntry oldEntry = mock(ImmutableEntry.class);
        when(oldEntry.contains(isA(Attribute[].class))).thenReturn(true);
        when(oldEntry.getAttributes()).thenReturn(attributeList2);

        ModifyRequest actualModifyRequest = resolveService.getModifyRequest(newEntry, oldEntry);

        verify(newEntry).contains(isA(Attribute[].class));
        verify(oldEntry).contains(isA(Attribute[].class));
        verify(newEntry).getAttributes();
        verify(oldEntry).getAttributes();
        assertEquals(-1, actualModifyRequest.getMessageId());
        assertEquals(1, actualModifyRequest.getModifications().size());
        assertEquals(MessageTypeEnum.MODIFY_REQUEST, actualModifyRequest.getType());
        assertFalse(actualModifyRequest.isAbandoned());
        assertTrue(actualModifyRequest.getControls().isEmpty());
        assertTrue(actualModifyRequest.hasResponse());
    }

    @Test
    void testGetModifyRequest8() {
        Class<Object> typeArgumentClass = Object.class;
        ResolveService<Object> resolveService = new ResolveService<>(typeArgumentClass);

        ArrayList<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new DefaultAttribute("42"));
        ImmutableEntry newEntry = mock(ImmutableEntry.class);
        when(newEntry.contains(isA(Attribute[].class))).thenReturn(false);
        when(newEntry.getAttributes()).thenReturn(attributeList);

        ArrayList<Attribute> attributeList2 = new ArrayList<>();
        attributeList2.add(new DefaultAttribute("42"));
        ImmutableEntry oldEntry = mock(ImmutableEntry.class);
        when(oldEntry.contains(isA(Attribute[].class))).thenReturn(true);
        when(oldEntry.getAttributes()).thenReturn(attributeList2);

        ModifyRequest actualModifyRequest = resolveService.getModifyRequest(newEntry, oldEntry);

        verify(newEntry).contains(isA(Attribute[].class));
        verify(oldEntry).contains(isA(Attribute[].class));
        verify(newEntry).getAttributes();
        verify(oldEntry).getAttributes();
        assertEquals(-1, actualModifyRequest.getMessageId());
        assertEquals(2, actualModifyRequest.getModifications().size());
        assertEquals(MessageTypeEnum.MODIFY_REQUEST, actualModifyRequest.getType());
        assertFalse(actualModifyRequest.isAbandoned());
        assertTrue(actualModifyRequest.getControls().isEmpty());
        assertTrue(actualModifyRequest.hasResponse());
    }

}
