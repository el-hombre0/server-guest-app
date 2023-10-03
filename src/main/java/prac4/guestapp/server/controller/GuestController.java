package prac4.guestapp.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prac4.guestapp.server.model.Guest;
import prac4.guestapp.server.repository.GuestRepository;

@RestController
@RequestMapping("/api/v1")
public class GuestController {


    @Autowired
    private GuestRepository guestRepository;

    //get all guests

    @GetMapping("/guests")
    public List<Guest> getAllGuests(){
        return guestRepository.findAll();
    }

    //create guest
    @PostMapping("/guests")
    public Guest createGuest(@RequestBody Guest guest){
        return guestRepository.save(guest);
    }
}
