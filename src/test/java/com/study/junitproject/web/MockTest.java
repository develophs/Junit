package com.study.junitproject.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
public class MockTest {

    @Autowired
    MockMvc mock;

    @Test
    @DisplayName("mock 테스트 시작")
    void test() throws Exception {
        mock.perform(MockMvcRequestBuilders.post("/api/v1/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"mockStudy\", \"author\":\"jake\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1))
                .andDo(MockMvcResultHandlers.print());
    }
}
