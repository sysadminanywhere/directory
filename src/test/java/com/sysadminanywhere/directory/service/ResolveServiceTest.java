package com.sysadminanywhere.directory.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.DefaultEntry;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.entry.Modification;
import org.apache.directory.api.ldap.model.entry.ModificationOperation;
import org.apache.directory.api.ldap.model.message.ModifyRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ResolveServiceTest {

    static TestADEntry testADEntry;
    static Entry testEntry;

    @SneakyThrows
    @BeforeAll
    static void init() {

        String dn = "CN=test,DC=example,DC=com";

        testADEntry = new TestADEntry();
        testADEntry.setCn("test");
        testADEntry.setCreated(LocalDateTime.now());
        testADEntry.setBadLogonCount(10);
        testADEntry.setJpegPhoto(new byte[1]);
        testADEntry.setDistinguishedName(dn);
        testADEntry.setCriticalSystemObject(true);
        testADEntry.setObjectClass(Arrays.asList("top", "test", "object"));
        testADEntry.setObjectGUID(UUID.fromString("ae22aa40-6f59-4bd6-9e67-a42877589a05"));

        testEntry = new DefaultEntry(
                dn,
                "cn", testADEntry.getCn(),
//                "whencreated", testADEntry.getCreated(),
                "objectguid", String.valueOf(testADEntry.getObjectGUID()),
                "iscriticalsystemobject", String.valueOf(testADEntry.isCriticalSystemObject()),
                "badpwdcount", String.valueOf(testADEntry.getBadLogonCount()),
                "jpegphoto", testADEntry.getJpegPhoto(),
                "objectclass:top",
                "objectclass:test",
                "objectclass:object"
        );
    }

    @Test
    void testGetADValue() {
        Class<TestADEntry> typeArgumentClass = TestADEntry.class;
        ResolveService<TestADEntry> resolveService = new ResolveService<>(typeArgumentClass);

        TestADEntry result = resolveService.getADValue(testEntry);

        assertNotNull(result);

        assertEquals(testADEntry.getCn(), result.getCn());
        //assertEquals(testADEntry.getCreated(), result.getCreated());
        assertEquals(testADEntry.getDistinguishedName(), result.getDistinguishedName());
        assertEquals(testADEntry.getObjectClass(), result.getObjectClass());
        assertEquals(testADEntry.getJpegPhoto().length, result.getJpegPhoto().length);
        assertEquals(testADEntry.getBadLogonCount(), result.getBadLogonCount());
        assertEquals(testADEntry.getObjectGUID().toString(), result.getObjectGUID().toString());
        assertEquals(testADEntry.isCriticalSystemObject(), result.isCriticalSystemObject());
    }

    @Test
    void testGetEntry() {
        Class<TestADEntry> typeArgumentClass = TestADEntry.class;
        ResolveService<TestADEntry> resolveService = new ResolveService<>(typeArgumentClass);

        Entry result = resolveService.getEntry(testADEntry);

        assertNotNull(result);

        assertEquals(testADEntry.getDistinguishedName(), result.getDn().getName());
        assertEquals(testADEntry.getCn(), result.get("cn").get().getString());
//        assertEquals(testADEntry.getCreated(), result.get("whencreated").get().getString());
        assertEquals(testADEntry.getObjectGUID().toString(), result.get("objectguid").get().getString());
        assertEquals(testADEntry.getJpegPhoto().length, result.get("jpegphoto").get().getBytes().length);
        assertEquals(testADEntry.getObjectClass().size(), result.get("objectclass").get().length());
        assertEquals(testADEntry.getBadLogonCount(), Integer.valueOf(result.get("badpwdcount").get().getString()));
        assertEquals(testADEntry.isCriticalSystemObject(), Boolean.valueOf(result.get("iscriticalsystemobject").get().getString()));
    }

    @SneakyThrows
    @Test
    void testNothingModifyRequest() {
        String dn = "CN=test,DC=example,DC=com";

        Entry newEntry = new DefaultEntry(
                dn,
                "cn", "test1",
                "objectguid", "11111111-6f59-4bd6-9e67-a42877589a05",
                "iscriticalsystemobject", "true",
                "badpwdcount", "1",
                "jpegphoto", new byte[10],
                "objectclass:top",
                "objectclass:test",
                "objectclass:object"
        );

        Entry oldEntry = new DefaultEntry(
                dn,
                "cn", "test1",
                "objectguid", "11111111-6f59-4bd6-9e67-a42877589a05",
                "iscriticalsystemobject", "true",
                "badpwdcount", "1",
                "jpegphoto", new byte[10],
                "objectclass:top",
                "objectclass:test",
                "objectclass:object"
        );

        Class<TestADEntry> typeArgumentClass = TestADEntry.class;
        ResolveService<TestADEntry> resolveService = new ResolveService<>(typeArgumentClass);

        ModifyRequest result = resolveService.getModifyRequest(newEntry, oldEntry);

        assertNotNull(result);
        assertEquals(0, result.getModifications().size());
    }

    @SneakyThrows
    @Test
    void testReplaceModifyRequest() {
        String dn = "CN=test,DC=example,DC=com";

        Entry newEntry = new DefaultEntry(
                dn,
                "cn", "test1",
                "objectguid", "11111111-6f59-4bd6-9e67-a42877589a05",
                "iscriticalsystemobject", "true",
                "badpwdcount", "1",
                "jpegphoto", new byte[10],
                "objectclass:top",
                "objectclass:test",
                "objectclass:object"
        );

        Entry oldEntry = new DefaultEntry(
                dn,
                "cn", "test2",
                "objectguid", "22222222-6f59-4bd6-9e67-a42877589a05",
                "iscriticalsystemobject", "false",
                "badpwdcount", "0",
                "jpegphoto", new byte[10],
                "objectclass:top",
                "objectclass:test",
                "objectclass:object"
        );

        Class<TestADEntry> typeArgumentClass = TestADEntry.class;
        ResolveService<TestADEntry> resolveService = new ResolveService<>(typeArgumentClass);

        ModifyRequest result = resolveService.getModifyRequest(newEntry, oldEntry);

        assertNotNull(result);
        assertEquals(4, result.getModifications().size());

        for (Modification modification : result.getModifications()) {
            assertEquals(ModificationOperation.REPLACE_ATTRIBUTE, modification.getOperation());
        }
    }

    @SneakyThrows
    @Test
    void testAddModifyRequest() {
        String dn = "CN=test,DC=example,DC=com";

        Entry newEntry = new DefaultEntry(
                dn,
                "cn", "test",
                "objectguid", "11111111-6f59-4bd6-9e67-a42877589a05",
                "iscriticalsystemobject", "true",
                "badpwdcount", "1",
                "jpegphoto", new byte[10],
                "objectclass:top",
                "objectclass:test",
                "objectclass:object"
        );

        Entry oldEntry = new DefaultEntry(
                dn,
                "cn", "test",
                "objectguid", "11111111-6f59-4bd6-9e67-a42877589a05",
                "objectclass:top",
                "objectclass:test",
                "objectclass:object"
        );

        Class<TestADEntry> typeArgumentClass = TestADEntry.class;
        ResolveService<TestADEntry> resolveService = new ResolveService<>(typeArgumentClass);

        ModifyRequest result = resolveService.getModifyRequest(newEntry, oldEntry);

        assertNotNull(result);
        assertEquals(3, result.getModifications().size());

        for (Modification modification : result.getModifications()) {
            assertEquals(ModificationOperation.ADD_ATTRIBUTE, modification.getOperation());
        }
    }

    @SneakyThrows
    @Test
    void testRemoveModifyRequest() {
        String dn = "CN=test,DC=example,DC=com";

        Entry newEntry = new DefaultEntry(
                dn,
                "cn", "test",
                "objectguid", "11111111-6f59-4bd6-9e67-a42877589a05",
                "objectclass:top",
                "objectclass:test",
                "objectclass:object"
        );

        Entry oldEntry = new DefaultEntry(
                dn,
                "cn", "test",
                "objectguid", "11111111-6f59-4bd6-9e67-a42877589a05",
                "iscriticalsystemobject", "true",
                "badpwdcount", "1",
                "jpegphoto", new byte[10],
                "objectclass:top",
                "objectclass:test",
                "objectclass:object"
        );


        Class<TestADEntry> typeArgumentClass = TestADEntry.class;
        ResolveService<TestADEntry> resolveService = new ResolveService<>(typeArgumentClass);

        ModifyRequest result = resolveService.getModifyRequest(newEntry, oldEntry);

        assertNotNull(result);
        assertEquals(3, result.getModifications().size());

        for (Modification modification : result.getModifications()) {
            assertEquals(ModificationOperation.REMOVE_ATTRIBUTE, modification.getOperation());
        }
    }

}