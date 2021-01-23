package com.galvanize.andromeda.guestbook.unittests.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.andromeda.guestbook.models.Guest;
import com.galvanize.andromeda.guestbook.services.GuestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class GuestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuestService mockGuestService;

    private ObjectMapper mapper;
    private List<Guest> guestList;

    @BeforeEach
    public void setUp() {
        mapper = new ObjectMapper();
        guestList = new ArrayList<>();
        guestList.add(Guest.builder().name("").comment("").build());
    }

    @Test
    public void getAllGuestsTest() throws Exception {

        when(mockGuestService.findAll()).thenReturn(guestList);
        mockMvc.perform(get("/guests"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").exists());

        verify(mockGuestService).findAll();
    }

    @Test
    public void getAllGuestTestsReturn204() throws Exception {

        when(mockGuestService.findAll()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/guests"))
                .andExpect(status().isNoContent());

        verify(mockGuestService).findAll();
    }

    @Test
    public void createGuestTestReturn201WithBody() throws Exception {
        Guest guest = Guest
                .builder()
                .name("guest1")
                .comment("awesome")
                .build();
        when(mockGuestService.createGuest(any(Guest.class))).thenReturn(guest);

        mockMvc.perform(post("/guests")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(guest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("guest1"))
                .andExpect(jsonPath("$.comment").value("awesome"));

        verify(mockGuestService).createGuest(guest);
    }
}
