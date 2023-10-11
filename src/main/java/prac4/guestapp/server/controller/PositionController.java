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
import prac4.guestapp.server.model.Position;
import prac4.guestapp.server.repository.PositionRepository;

@RestController
@RequestMapping("/api/v1")
public class PositionController {
    @Autowired
    private PositionRepository positionRepository;

    // Get all the positions
    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        try {
            Socket socket = new Socket("graylog", 5555);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("[POSTGRES] GET ALL POSITIONS");
            out.close();
            socket.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return positionRepository.findAll();
    }

    // Create the new position
    @PostMapping("/positions")
    public Position createPosition(@RequestBody Position position) {
        try {
            Socket socket = new Socket("graylog", 5555);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("[POSTGRES] CREATE NEW POSITION");
            out.close();
            socket.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return positionRepository.save(position);
    }

    // Update the position
    @PutMapping("/positions/{id}")
    public ResponseEntity<Position> updatePosition(@PathVariable Long id, @RequestBody Position positionDetails) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position does not exists with id: " + id));
        position.setTitle(positionDetails.getTitle());
        // position.setGuests(positionDetails.getGuests());

        Position updatedPosition = positionRepository.save(position);

        try {
            Socket socket = new Socket("graylog", 5555);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("[POSTGRES] UPDATE POSITION");
            out.close();
            socket.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(updatedPosition);
    }

    // Delete the position
    @DeleteMapping("/positions/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteGuest(@PathVariable Long id) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position does not exists with id: " + id));
        positionRepository.delete(position);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        try {
            Socket socket = new Socket("graylog", 5555);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("[POSTGRES] DELETE POSITION");
            out.close();
            socket.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }
}
