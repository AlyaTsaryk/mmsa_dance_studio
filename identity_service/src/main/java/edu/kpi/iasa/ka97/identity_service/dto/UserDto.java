package edu.kpi.iasa.ka97.identity_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String phone;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        if(phone.startsWith("+380") & phone.length()==13)
        {
            return phone;
        }
        else throw new IllegalArgumentException("field is nor valid");

    }

    public void setPhone(String phone) {
        if(phone.startsWith("+380") & phone.length()==13)
        {
            this.phone = phone;
        }
        else throw new IllegalArgumentException("field is nor valid");

    }
}
