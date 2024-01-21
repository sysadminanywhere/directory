package com.sysadminanywhere.directory.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysadminanywhere.directory.controller.dto.AddUserDto;
import com.sysadminanywhere.directory.model.ADSID;
import com.sysadminanywhere.directory.model.UserEntry;
import com.sysadminanywhere.directory.service.UsersService;

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

@ContextConfiguration(classes = {UsersController.class})
@ExtendWith(SpringExtension.class)
class UsersControllerTest {
    @Autowired
    private UsersController usersController;

    @MockBean
    private UsersService usersService;

    /**
     * Method under test: {@link UsersController#add(AddUserDto)}
     */
    @Test
    void testAdd() throws Exception {
        // Arrange
        when(usersService.getAll()).thenReturn(new ArrayList<>());

        AddUserDto addUserDto = new AddUserDto();
        addUserDto.setAccountDisabled(true);
        addUserDto.setCannotChangePassword(true);
        addUserDto.setDistinguishedName("Distinguished Name");
        addUserDto.setMustChangePassword(true);
        addUserDto.setPassword("iloveyou");
        addUserDto.setPasswordNeverExpires(true);
        addUserDto.setUser(new UserEntry());
        String content = (new ObjectMapper()).writeValueAsString(addUserDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UsersController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        // Arrange
        when(usersService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UsersController#delete(String)}
     */
    @Test
    void testDelete() throws Exception {
        // Arrange
        when(usersService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users")
                .param("distinguishedName", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UsersController#update(UserEntry)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.sysadminanywhere.directory.model.UserEntry["created"])
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
        UserEntry userEntry = new UserEntry();
        userEntry.setAccountExpirationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        userEntry.setAccountLockoutTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        userEntry.setAdminCount(3);
        userEntry.setAdsPath("Ads Path");
        userEntry.setBadLogonCount(3);
        userEntry.setCity("Oxford");
        userEntry.setCn("Cn");
        userEntry.setCodepage(1);
        userEntry.setCompany("Company");
        userEntry.setCountry("GB");
        userEntry.setCountryCode(3);
        userEntry.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        userEntry.setCriticalSystemObject(true);
        userEntry.setDepartment("Department");
        userEntry.setDescription("The characteristics of someone or something");
        userEntry.setDisplayName("Display Name");
        userEntry.setDistinguishedName("Distinguished Name");
        userEntry.setDivision("Division");
        userEntry.setEmailAddress("42 Main St");
        userEntry.setEmployeeID("Employee ID");
        userEntry.setEmployeeNumber("42");
        userEntry.setFax("Fax");
        userEntry.setFirstName("Jane");
        userEntry.setHomeDirectory("/directory");
        userEntry.setHomeDrive("Home Drive");
        userEntry.setHomePage("Home Page");
        userEntry.setHomePhone("6625550144");
        userEntry.setInitials("Initials");
        userEntry.setInstanceType(1);
        userEntry.setJpegPhoto("AXAXAXAX".getBytes("UTF-8"));
        userEntry.setLastBadPasswordAttempt(LocalDate.of(1970, 1, 1).atStartOfDay());
        userEntry.setLastLogoff(1);
        userEntry.setLastLogon(LocalDate.of(1970, 1, 1).atStartOfDay());
        userEntry.setLastName("Doe");
        userEntry.setLogonCount(3);
        userEntry.setLogonHours("AXAXAXAX".getBytes("UTF-8"));
        userEntry.setLogonWorkstations("Logon Workstations");
        userEntry.setManagedObjects(new ArrayList<>());
        userEntry.setManager("Manager");
        userEntry.setMemberOf(new ArrayList<>());
        userEntry.setMobilePhone("6625550144");
        userEntry.setModified(LocalDate.of(1970, 1, 1).atStartOfDay());
        userEntry.setName("Name");
        userEntry.setObjectCategory("Object Category");
        userEntry.setObjectClass(new ArrayList<>());
        userEntry.setObjectGUID(UUID.randomUUID());
        userEntry.setOffice("Office");
        userEntry.setOfficePhone("6625550144");
        userEntry.setOrganization("Organization");
        userEntry.setOtherName("Other Name");
        userEntry.setPOBox("P OBox");
        userEntry.setPasswordLastSet(LocalDate.of(1970, 1, 1).atStartOfDay());
        userEntry.setPostalCode("Postal Code");
        userEntry.setPrimaryGroupId(1);
        userEntry.setProfilePath("/directory/foo.txt");
        userEntry.setSamAccountName("Dr Jane Doe");
        userEntry.setSamAccountType(3);
        userEntry.setScriptPath("Script Path");
        userEntry.setServicePrincipalName("Service Principal Name");
        userEntry.setSid(new ADSID("AXAXAXAX".getBytes("UTF-8")));
        userEntry.setState("MD");
        userEntry.setStreetAddress("42 Main St");
        userEntry.setTitle("Dr");
        userEntry.setUserAccountControl(3);
        userEntry.setUserPrincipalName("User Principal Name");
        String content = (new ObjectMapper()).writeValueAsString(userEntry);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(usersController).build().perform(requestBuilder);
    }
}
