package prac4.guestapp.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.*;
import java.net.*;

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

    // Get all the guests
    @GetMapping("/guests")
    public List<Guest> getAllGuests() {
        try {
            Socket socket = new Socket("graylog", 5555);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("[POSTGRES] GET ALL GUESTS");
            out.close();
            socket.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return guestRepository.findAll();
    }

    // Create the guest
    @PostMapping("/guests")
    public Guest createGuest(@RequestBody Guest guest) {
        try {
            Socket socket = new Socket("graylog", 5555);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("[POSTGRES] CREATE NEW GUEST");
            out.close();
            socket.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return guestRepository.save(guest);
    }

    // Update the guest
    @PutMapping("/guests/{id}")
    public ResponseEntity<Guest> updateGuest(@PathVariable Long id, @RequestBody Guest guestDetails) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guest does not exists with id: " + id));
        guest.setFirstName(guestDetails.getFirstName());
        guest.setLastName(guestDetails.getLastName());
        guest.setEmail(guestDetails.getEmail());
        guest.setPhones(guestDetails.getPhones());

        Guest updatedGuest = guestRepository.save(guest);
        
        try {
            Socket socket = new Socket("graylog", 5555);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("[POSTGRES] UPDATE GUEST");
            out.close();
            socket.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

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

        try {
            Socket socket = new Socket("graylog", 5555);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("[POSTGRES] DELETE GUEST");
            out.close();
            socket.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }
}
