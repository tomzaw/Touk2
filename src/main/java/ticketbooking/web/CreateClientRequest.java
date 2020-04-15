package ticketbooking.web;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreateClientRequest {

    @Size(min = 3, message = "Name should be at least 3 characters long.")
    @Pattern(regexp = "[A-Z]+.*", message = "Name should start from capital letter.")
    @NotBlank
    private String name;

    @Size(min = 3, message = "Surname should be at least 3 characters long.")
    @Pattern(regexp = "([A-Z]+.*)||([A-Z]+.*_[A-Z]+.*)", message = "Surname should start from capital letter.")
    @NotBlank
    private String surname;

    @Email
    private String email;

    public CreateClientRequest(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public CreateClientRequest() {
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

}
