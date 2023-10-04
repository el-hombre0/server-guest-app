package prac4.guestapp.server.model;

import java.util.Set;

// import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
// import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "guest")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    public Guest(String firstName, String lastName, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Guest() {
    }

    // Database tables relationship
    // @ManyToMany
    // @JoinTable(name = "guest_position", joinColumns = @JoinColumn(name = "guest_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "position_id", referencedColumnName = "id"))
    // private Set<Position> positions;

    // public void setPositions(Set<Position> positions) {
    //     this.positions = positions;
    // }

    // public Set<Position> getPositions() {
    //     return positions;
    // }

    // @OneToMany()
    // private Set<Phone> phones;

    // public Set<Phone> getPhones() {
    //     return phones;
    // }

    // public void setPhones(Set<Phone> phones) {
    //     this.phones = phones;
    // }

    // Basic getters and setters

    public void setId(long id) {
        this.id = id;

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
