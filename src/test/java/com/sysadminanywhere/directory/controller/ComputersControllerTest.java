package com.sysadminanywhere.directory.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysadminanywhere.directory.controller.dto.AddComputerDto;
import com.sysadminanywhere.directory.model.ADSID;
import com.sysadminanywhere.directory.model.ComputerEntry;
import com.sysadminanywhere.directory.service.ComputersService;

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

@ContextConfiguration(classes = {ComputersController.class})
@ExtendWith(SpringExtension.class)
class ComputersControllerTest {
    @Autowired
    private ComputersController computersController;

    @MockBean
    private ComputersService computersService;

    /**
     * Method under test: {@link ComputersController#add(AddComputerDto)}
     */
    @Test
    void testAdd() throws Exception {
        // Arrange
        when(computersService.getAll()).thenReturn(new ArrayList<>());

        AddComputerDto addComputerDto = new AddComputerDto();
        addComputerDto.setComputer(new ComputerEntry());
        addComputerDto.setDistinguishedName("Distinguished Name");
        addComputerDto.setEnabled(true);
        String content = (new ObjectMapper()).writeValueAsString(addComputerDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/computers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(computersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ComputersController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        // Arrange
        when(computersService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/computers");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(computersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ComputersController#delete(String)}
     */
    @Test
    void testDelete() throws Exception {
        // Arrange
        when(computersService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/computers")
                .param("distinguishedName", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(computersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ComputersController#update(ComputerEntry)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.sysadminanywhere.directory.model.ComputerEntry["created"])
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
        ComputerEntry computerEntry = new ComputerEntry();
        computerEntry.setAccountExpirationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        computerEntry.setAdsPath("Ads Path");
        computerEntry.setBadLogonCount(3);
        computerEntry.setCn("Cn");
        computerEntry.setCodepage(1);
        computerEntry.setCountryCode(3);
        computerEntry.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        computerEntry.setCriticalSystemObject(true);
        computerEntry.setDescription("The characteristics of someone or something");
        computerEntry.setDistinguishedName("Distinguished Name");
        computerEntry.setDnsHostName("Dns Host Name");
        computerEntry.setInstanceType(1);
        computerEntry.setLastBadPasswordAttempt(LocalDate.of(1970, 1, 1).atStartOfDay());
        computerEntry.setLastLogoff(1);
        computerEntry.setLastLogon(LocalDate.of(1970, 1, 1).atStartOfDay());
        computerEntry.setLocalPolicyFlags(1);
        computerEntry.setLocation("Location");
        computerEntry.setLogonCount(3);
        computerEntry.setManagedBy("Managed By");
        computerEntry.setMemberOf(new ArrayList<>());
        computerEntry.setModified(LocalDate.of(1970, 1, 1).atStartOfDay());
        computerEntry.setMsdsSupportedEncryptionTypes(1);
        computerEntry.setName("Name");
        computerEntry.setObjectCategory("Object Category");
        computerEntry.setObjectClass(new ArrayList<>());
        computerEntry.setObjectGUID(UUID.randomUUID());
        computerEntry.setOperatingSystem("Operating System");
        computerEntry.setOperatingSystemHotfix("Operating System Hotfix");
        computerEntry.setOperatingSystemServicePack("Operating System Service Pack");
        computerEntry.setOperatingSystemVersion("1.0.2");
        computerEntry.setPasswordLastSet(LocalDate.of(1970, 1, 1).atStartOfDay());
        computerEntry.setPrimaryGroupId(1);
        computerEntry.setSamAccountName("Dr Jane Doe");
        computerEntry.setSamAccountType(3);
        computerEntry.setServicePrincipalNames(new ArrayList<>());
        computerEntry.setSid(new ADSID("AXAXAXAX".getBytes("UTF-8")));
        computerEntry.setUserAccountControl(3);
        String content = (new ObjectMapper()).writeValueAsString(computerEntry);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/computers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(computersController).build().perform(requestBuilder);
    }
}
