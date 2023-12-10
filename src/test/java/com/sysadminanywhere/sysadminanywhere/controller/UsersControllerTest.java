package com.sysadminanywhere.sysadminanywhere.controller;

import static org.mockito.Mockito.when;

import com.sysadminanywhere.sysadminanywhere.service.users.UsersServiceImpl;

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

@ContextConfiguration(classes = {UsersController.class})
@ExtendWith(SpringExtension.class)
class UsersControllerTest {
    @Autowired
    private UsersController usersController;

    @MockBean
    private UsersServiceImpl usersServiceImpl;

    @Test
    void testGetUsers() throws Exception {
        when(usersServiceImpl.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users");
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
