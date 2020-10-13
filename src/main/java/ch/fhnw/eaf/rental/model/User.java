package ch.fhnw.eaf.rental.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Rental.class)
@Entity @Table(name = "USERS")
@AttributeOverride(name = "id", column = @Column(name = "USER_ID"))
@NamedQuery(name = User.FIND_BY_LASTNAME, query = "SELECT u FROM User u WHERE u.lastName = :lastName")
@NamedQuery(name = User.FIND_BY_FIRSTNAME, query = "SELECT u FROM User u WHERE u.firstName = :firstName")
@NamedQuery(name = User.FIND_BY_EMAIL, query = "SELECT u FROM User u WHERE u.email = :email")
public class User extends BasicModel {
    @Column(name = "USER_NAME")
    private String lastName;
    @Column(name = "USER_FIRSTNAME")
    private String firstName;
    @Column(name = "USER_EMAIL")
    private String email;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Rental> rentals;
    
    public static final String FIND_BY_LASTNAME = "User.byLastName";
    public static final String FIND_BY_FIRSTNAME = "User.byFirstName";
    public static final String FIND_BY_EMAIL = "User.byEmail";

    protected User() {
    }

    public User(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.rentals = new ArrayList<>();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public int getCharge() {
        int result = 0;
        for (Rental rental : rentals) {
            result += rental.getMovie().getPriceCategory().getCharge(rental.getRentalDays());
        }
        return result;
    }
}
