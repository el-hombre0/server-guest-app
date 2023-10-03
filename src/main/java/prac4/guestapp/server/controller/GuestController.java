package prac4.guestapp.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prac4.guestapp.server.exception.ResourceNotFoundException;
import prac4.guestapp.server.model.Guest;
import prac4.guestapp.server.repository.GuestRepository;

@RestController
@RequestMapping("/api/v1")
public class GuestController {

    @Autowired
    private GuestRepository guestRepository;

    // get all guests

    @GetMapping("/guests")
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    // create guest
    @PostMapping("/guests")
    public Guest createGuest(@RequestBody Guest guest) {
        return guestRepository.save(guest);
    }

    // Update guest
    @PutMapping("/guests/{id}")
    public ResponseEntity<Guest> updateGuest(@PathVariable Long id, @RequestBody Guest guestDetails) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guest does not exists with id: " + id));
        guest.setFirstName(guestDetails.getFirstName());
        guest.setLastName(guestDetails.getLastName());
        guest.setEmail(guestDetails.getEmail());
        guest.setPhone(guestDetails.getPhone());
        guest.setPositions(guestDetails.getPositions());

        Guest updatedGuest = guestRepository.save(guest);
        return ResponseEntity.ok(updatedGuest);
    }

    // Delete the guest
    @DeleteMapping("/guests/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteGuest(@PathVariable Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guest does not exists with id: " + id));
        guestRepository.delete(guest);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
