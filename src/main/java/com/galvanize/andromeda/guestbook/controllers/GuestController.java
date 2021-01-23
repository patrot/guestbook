package com.galvanize.andromeda.guestbook.controllers;

import com.galvanize.andromeda.guestbook.models.Guest;
import com.galvanize.andromeda.guestbook.services.GuestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
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
}
