package com.galvanize.andromeda.guestbook.unittests.controllers;

import com.galvanize.andromeda.guestbook.models.Guest;
import com.galvanize.andromeda.guestbook.services.GuestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class GuestControllerTests {

    private List<Guest> guestList;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuestService mockGuestService;

    @BeforeEach
    public void setUp() {
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
}
