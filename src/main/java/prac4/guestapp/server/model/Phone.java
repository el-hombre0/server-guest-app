package prac4.guestapp.server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "phone_number")
    private String phone_number;

    public Phone(long id, String phone_number, Guest guest) {
        this.id = id;
        this.phone_number = phone_number;
        this.guest = guest;
    }

    public Phone() {

    }

    // Database tables relationship
    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;

    // Basic getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

}
