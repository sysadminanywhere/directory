package com.sysadminanywhere.sysadminanywhere.controller;

import static org.mockito.Mockito.when;

import com.sysadminanywhere.sysadminanywhere.service.ComputersServiceImpl;

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

@ContextConfiguration(classes = {ComputersController.class})
@ExtendWith(SpringExtension.class)
class ComputersControllerTest {
    @Autowired
    private ComputersController computersController;

    @MockBean
    private ComputersServiceImpl computersServiceImpl;

    @Test
    void testGetComputers() throws Exception {
        when(computersServiceImpl.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/computers");
        MockMvcBuilders.standaloneSetup(computersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
