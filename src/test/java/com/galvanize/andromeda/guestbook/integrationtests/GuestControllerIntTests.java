package com.galvanize.andromeda.guestbook.integrationtests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.andromeda.guestbook.models.Guest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GuestControllerIntTests {

    @Autowired
    MockMvc mockMvc;

    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    public void getAllGuestsTest() throws Exception {
        mockMvc.perform(get("/guests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Mickey Mouse"))
                .andExpect(jsonPath("$[0].comment").value("Happy birthday Minnie"));


    }

    @Test
    public void createGuestTestReturnWith201WithBody() throws Exception {
        Guest guest = Guest
                .builder()
                .name("Donald Duck")
                .comment("Have a great day from Donald and Daisy")
                .build();

        mockMvc.perform(post("/guests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(guest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("Donald Duck"))
                .andExpect(jsonPath("$.comment").value("Have a great day from Donald and Daisy"));
    }
}
