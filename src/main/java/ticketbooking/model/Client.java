package ticketbooking.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "Name should be at least 3 characters long.")
    @Pattern(regexp = "[A-Z]+[a-z]*", message = "Name should start from capital letter.")
    @NotBlank
    private String name;

    @Size(min = 3, message = "Surname should be at least 3 characters long.")
    @Pattern(regexp = "(([A-Z]+)|([A-Z]+.*_[A-Z]+))[a-z]*", message = "Surname should start from capital letter.")
    @NotBlank
    private String surname;

    @Column(unique = true)
    @Email
    private String email;

    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    public Client(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AppUser{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + '}';
    }
}
