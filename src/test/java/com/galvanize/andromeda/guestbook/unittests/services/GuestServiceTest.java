package com.galvanize.andromeda.guestbook.unittests.services;

import com.galvanize.andromeda.guestbook.models.Guest;
import com.galvanize.andromeda.guestbook.repository.GuestRepository;
import com.galvanize.andromeda.guestbook.services.GuestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestServiceTest {

    @InjectMocks
    private GuestService guestService;

    @Mock
    private GuestRepository mockedGuestRepository;

    private List<Guest> guests;

    @BeforeEach
    public void setUp() {
        guests = new ArrayList<>();
        guests.add(Guest.builder().build());
    }

    @Test
    public void test_findAll_returnListOfGuest() {
        when(mockedGuestRepository.findAll()).thenReturn(guests);
        List<Guest> result = guestService.findAll();
        assertEquals(1, result.size());
    }

    @Test
    public void test_findAll_returnEmptyList() {
        when(mockedGuestRepository.findAll()).thenReturn(new ArrayList<>());
        List<Guest> result = guestService.findAll();
        assertEquals(0, result.size());
    }

    @Test
    public void test_create_returnGuestObject() {
        Guest expectedGuest = Guest.builder()
                .name("Mickey Mouse")
                .comment("Steam boat willie")
                .build();

        when(mockedGuestRepository.save(any(Guest.class))).thenReturn(expectedGuest);
        Guest newGuest = guestService.createGuest(expectedGuest);
        assertEquals(expectedGuest, newGuest);

        verify(mockedGuestRepository).save(expectedGuest);
    }
}