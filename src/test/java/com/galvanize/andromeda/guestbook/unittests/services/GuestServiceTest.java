package com.galvanize.andromeda.guestbook.unittests.services;

import com.galvanize.andromeda.guestbook.services.GuestService;
import org.junit.jupiter.api.BeforeEach;

class GuestServiceTest {

    private GuestService guestService;

    @BeforeEach
    public void setUp() {
        guestService = new GuestService();
    }
}