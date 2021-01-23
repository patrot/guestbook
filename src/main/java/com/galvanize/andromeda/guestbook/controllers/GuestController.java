package com.galvanize.andromeda.guestbook.controllers;

import com.galvanize.andromeda.guestbook.models.Guest;
import com.galvanize.andromeda.guestbook.services.GuestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;


@RestController
@AllArgsConstructor
public class GuestController {

    private GuestService guestService;

    @GetMapping("/guests")
    public ResponseEntity<List<Guest>> getAllGuest() {
        List<Guest> guests = guestService.findAll();
        return isEmpty(guests) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(guests, HttpStatus.OK);
    }

    @PostMapping("/guests")
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) {
        Guest response = guestService.createGuest(guest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
