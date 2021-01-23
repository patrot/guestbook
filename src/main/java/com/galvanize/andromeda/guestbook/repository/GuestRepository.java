package com.galvanize.andromeda.guestbook.repository;

import com.galvanize.andromeda.guestbook.models.Guest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Component
public class GuestRepository {
    public List<Guest> findAll() {
        return new ArrayList<>();
    }

    public Guest save(Guest guest) {
        return null;
    }
}
