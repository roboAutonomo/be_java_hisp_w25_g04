package com.breakingbytes.be_java_hisp_w25_g04.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Test
    public void testFollowUser() throws Exception {
        Integer idUser = 1;
        Integer idUserToFollow = 4;
        mockMvc.perform(post("/users/{user_id}/follow/{user_id_to_follow}", idUser, idUserToFollow))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
