package com.galvanize.andromeda.guestbook.services;

import com.galvanize.andromeda.guestbook.models.Guest;
import com.galvanize.andromeda.guestbook.repository.GuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GuestService {

    private GuestRepository guestRepository;

    public List<Guest> findAll() {


        return guestRepository.findAll();
    }

    public Guest createGuest(Guest guest) {
        return null;
    }
}
