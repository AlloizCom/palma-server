package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.enums.Role;

@Dto
public class UserEntityDto {
    private Long id;
    private Boolean available;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Role role;

    public UserEntityDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntityDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntityDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserEntityDto setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntityDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public UserEntityDto setRole(Role role) {
        this.role = role;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserEntityDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public UserEntityDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "UserEntityDto{" +
                "id=" + id +
                ", available=" + available +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
