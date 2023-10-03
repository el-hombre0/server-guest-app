package prac4.guestapp.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import prac4.guestapp.server.model.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

}
