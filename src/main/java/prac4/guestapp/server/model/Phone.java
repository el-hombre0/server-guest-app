package prac4.guestapp.server.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // @Column(name = "guest_id")
    // private long guest_id;

    @Column(name = "phone_number")
    private String phone_number;

    public Phone(long id, String phone_number) {
        this.id = id;
        // this.guest_id = guest_id;
        this.phone_number = phone_number;
    }

    public Phone() {

    }

    // Database tables relationship
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "phone")
    private Set<Guest> guests;

    public Set<Guest> getGuests() {
        return guests;
    }

    public void setGuests(Set<Guest> guests) {
        this.guests = guests;
    }

    // Basic getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // public long getGuest_id() {
    //     return guest_id;
    // }

    // public void setGuest_id(long guest_id) {
    //     this.guest_id = guest_id;
    // }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

}
