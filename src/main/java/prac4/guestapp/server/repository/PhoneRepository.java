package prac4.guestapp.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import prac4.guestapp.server.model.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
