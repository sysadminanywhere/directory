package com.sysadminanywhere.directory.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysadminanywhere.directory.controller.dto.AddGroupDto;
import com.sysadminanywhere.directory.model.ADSID;
import com.sysadminanywhere.directory.model.GroupEntry;
import com.sysadminanywhere.directory.model.GroupScope;
import com.sysadminanywhere.directory.service.GroupsService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {GroupsController.class})
@ExtendWith(SpringExtension.class)
class GroupsControllerTest {
    @Autowired
    private GroupsController groupsController;

    @MockBean
    private GroupsService groupsService;

    /**
     * Method under test: {@link GroupsController#add(AddGroupDto)}
     */
    @Test
    void testAdd() throws Exception {
        // Arrange
        when(groupsService.getAll()).thenReturn(new ArrayList<>());

        AddGroupDto addGroupDto = new AddGroupDto();
        addGroupDto.setDistinguishedName("Distinguished Name");
        addGroupDto.setGroup(new GroupEntry());
        addGroupDto.setGroupScope(GroupScope.Local);
        addGroupDto.setSecurity(true);
        String content = (new ObjectMapper()).writeValueAsString(addGroupDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/groups")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(groupsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link GroupsController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        // Arrange
        when(groupsService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/groups");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(groupsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link GroupsController#delete(String)}
     */
    @Test
    void testDelete() throws Exception {
        // Arrange
        when(groupsService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/groups")
                .param("distinguishedName", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(groupsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link GroupsController#update(GroupEntry)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.sysadminanywhere.directory.model.GroupEntry["created"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1308)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:479)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:318)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4719)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3964)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        GroupEntry groupEntry = new GroupEntry();
        groupEntry.setAdminCount(3);
        groupEntry.setAdsPath("Ads Path");
        groupEntry.setCn("Cn");
        groupEntry.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        groupEntry.setCriticalSystemObject(true);
        groupEntry.setDescription("The characteristics of someone or something");
        groupEntry.setDistinguishedName("Distinguished Name");
        groupEntry.setGroupType(1L);
        groupEntry.setInstanceType(1);
        groupEntry.setManagedBy("Managed By");
        groupEntry.setMemberOf(new ArrayList<>());
        groupEntry.setMembers(new ArrayList<>());
        groupEntry.setModified(LocalDate.of(1970, 1, 1).atStartOfDay());
        groupEntry.setName("Name");
        groupEntry.setObjectCategory("Object Category");
        groupEntry.setObjectClass(new ArrayList<>());
        groupEntry.setObjectGUID(UUID.randomUUID());
        groupEntry.setPrimaryGroupId(1);
        groupEntry.setSamAccountName("Dr Jane Doe");
        groupEntry.setSamAccountType(3);
        groupEntry.setSid(new ADSID("AXAXAXAX".getBytes("UTF-8")));
        groupEntry.setSystemFlags(1);
        String content = (new ObjectMapper()).writeValueAsString(groupEntry);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/groups")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(groupsController).build().perform(requestBuilder);
    }
}
