package com.sysadminanywhere.directory.controller;

import static org.mockito.Mockito.when;

import com.sysadminanywhere.directory.service.PrintersService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PrintersController.class})
@ExtendWith(SpringExtension.class)
class PrintersControllerTest {
    @Autowired
    private PrintersController printersController;

    @MockBean
    private PrintersService printersService;

    /**
     * Method under test: {@link PrintersController#delete(String)}
     */
    @Test
    void testDelete() throws Exception {
        // Arrange
        when(printersService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/printers")
                .param("distinguishedName", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(printersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PrintersController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        // Arrange
        when(printersService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/printers");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(printersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
