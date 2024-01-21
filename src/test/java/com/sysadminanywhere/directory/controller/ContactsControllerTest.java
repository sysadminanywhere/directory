package com.sysadminanywhere.directory.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysadminanywhere.directory.controller.dto.AddContactDto;
import com.sysadminanywhere.directory.model.ADSID;
import com.sysadminanywhere.directory.model.ContactEntry;
import com.sysadminanywhere.directory.service.ContactsService;

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

@ContextConfiguration(classes = {ContactsController.class})
@ExtendWith(SpringExtension.class)
class ContactsControllerTest {
    @Autowired
    private ContactsController contactsController;

    @MockBean
    private ContactsService contactsService;

    /**
     * Method under test: {@link ContactsController#add(AddContactDto)}
     */
    @Test
    void testAdd() throws Exception {
        // Arrange
        when(contactsService.getAll()).thenReturn(new ArrayList<>());

        AddContactDto addContactDto = new AddContactDto();
        addContactDto.setContact(new ContactEntry());
        addContactDto.setDistinguishedName("Distinguished Name");
        String content = (new ObjectMapper()).writeValueAsString(addContactDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(contactsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ContactsController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        // Arrange
        when(contactsService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/contacts");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(contactsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ContactsController#delete(String)}
     */
    @Test
    void testDelete() throws Exception {
        // Arrange
        when(contactsService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/contacts")
                .param("distinguishedName", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(contactsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ContactsController#update(ContactEntry)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.sysadminanywhere.directory.model.ContactEntry["created"])
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
        ContactEntry contactEntry = new ContactEntry();
        contactEntry.setAdsPath("Ads Path");
        contactEntry.setCity("Oxford");
        contactEntry.setCn("Cn");
        contactEntry.setCompany("Company");
        contactEntry.setCountry("GB");
        contactEntry.setCountryCode(3);
        contactEntry.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        contactEntry.setDepartment("Department");
        contactEntry.setDescription("The characteristics of someone or something");
        contactEntry.setDisplayName("Display Name");
        contactEntry.setDistinguishedName("Distinguished Name");
        contactEntry.setDivision("Division");
        contactEntry.setEmailAddress("42 Main St");
        contactEntry.setEmployeeID("Employee ID");
        contactEntry.setEmployeeNumber("42");
        contactEntry.setFax("Fax");
        contactEntry.setFirstName("Jane");
        contactEntry.setHomePage("Home Page");
        contactEntry.setHomePhone("6625550144");
        contactEntry.setInitials("Initials");
        contactEntry.setInstanceType(1);
        contactEntry.setLastName("Doe");
        contactEntry.setMobilePhone("6625550144");
        contactEntry.setModified(LocalDate.of(1970, 1, 1).atStartOfDay());
        contactEntry.setName("Name");
        contactEntry.setObjectCategory("Object Category");
        contactEntry.setObjectClass(new ArrayList<>());
        contactEntry.setObjectGUID(UUID.randomUUID());
        contactEntry.setOffice("Office");
        contactEntry.setOfficePhone("6625550144");
        contactEntry.setOrganization("Organization");
        contactEntry.setOtherName("Other Name");
        contactEntry.setPOBox("P OBox");
        contactEntry.setPostalCode("Postal Code");
        contactEntry.setSid(new ADSID("AXAXAXAX".getBytes("UTF-8")));
        contactEntry.setState("MD");
        contactEntry.setStreetAddress("42 Main St");
        contactEntry.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(contactEntry);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(contactsController).build().perform(requestBuilder);
    }
}
