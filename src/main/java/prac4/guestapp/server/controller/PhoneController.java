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
import prac4.guestapp.server.model.Phone;
import prac4.guestapp.server.repository.PhoneRepository;

@RestController
@RequestMapping("/api/v1")
public class PhoneController {
    @Autowired
    private PhoneRepository phoneRepository;

    // Get all the phones
    @GetMapping("/phones")
    public List<Phone> getAllPhones() {
        try {
            Socket socket = new Socket("graylog", 5555);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("[POSTGRES] GET ALL PHONES");
            out.close();
            socket.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return phoneRepository.findAll();
    }

    // Create the new phone
    @PostMapping("/phones")
    public Phone createPhone(@RequestBody Phone phone) {
        try {
            Socket socket = new Socket("graylog", 5555);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("[POSTGRES] CREATE NEW PHONE");
            out.close();
            socket.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return phoneRepository.save(phone);
    }

    // Update the phone
    @PutMapping("/phones/{id}")
    public ResponseEntity<Phone> updatePhone(@PathVariable Long id, @RequestBody Phone phoneDetails) {
        Phone phone = phoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Phone does not exists with id: " + id));
        phone.setPhone_number(phoneDetails.getPhone_number());
        // phone.setGuests(phoneDetails.getGuests());
        // phone.setGuest(phoneDetails.getGuest());
        Phone updatedPhone = phoneRepository.save(phone);

        try {
            Socket socket = new Socket("graylog", 5555);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("[POSTGRES] UPDATE PHONE");
            out.close();
            socket.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(updatedPhone);
    }

    // Delete the phone
    @DeleteMapping("/phones/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePhone(@PathVariable Long id) {
        Phone phone = phoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Phone does not exists with id: " + id));
        phoneRepository.delete(phone);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        try {
            Socket socket = new Socket("graylog", 5555);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("[POSTGRES] DELETE PHONE");
            out.close();
            socket.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }
}
