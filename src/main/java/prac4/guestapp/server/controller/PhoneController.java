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
import prac4.guestapp.server.model.Phone;
import prac4.guestapp.server.repository.PhoneRepository;

@RestController
@RequestMapping("/api/v1")
public class PhoneController {
    @Autowired
    private PhoneRepository phoneRepository;

    // Get all phones
    @GetMapping("/phones")
    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    // Create new phone
    @PostMapping("/phones")
    public Phone createPhone(@RequestBody Phone phone) {
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
        return ResponseEntity.ok(response);
    }
}
