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
import prac4.guestapp.server.model.Position;
import prac4.guestapp.server.repository.PositionRepository;

@RestController
@RequestMapping("/api/v1")
public class PositionController {
    @Autowired
    private PositionRepository positionRepository;

    // Get all positions

    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    // create guest
    @PostMapping("/positions")
    public Position createPosition(@RequestBody Position position) {
        return positionRepository.save(position);
    }

    // Update guest
    @PutMapping("/positions/{id}")
    public ResponseEntity<Position> updatePosition(@PathVariable Long id, @RequestBody Position positionDetails) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position does not exists with id: " + id));
        position.setTitle(positionDetails.getTitle());
        // position.setGuests(positionDetails.getGuests());

        Position updatedPosition = positionRepository.save(position);
        return ResponseEntity.ok(updatedPosition);
    }

    // Delete the guest
    @DeleteMapping("/positions/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteGuest(@PathVariable Long id) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position does not exists with id: " + id));
        positionRepository.delete(position);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
