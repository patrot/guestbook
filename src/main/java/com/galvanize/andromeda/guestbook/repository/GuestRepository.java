package com.galvanize.andromeda.guestbook.repository;

import com.galvanize.andromeda.guestbook.models.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
}
