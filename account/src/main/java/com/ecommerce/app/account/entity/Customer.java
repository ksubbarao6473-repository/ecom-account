package com.ecommerce.app.account.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotNull(message = "First name cannot be null")
    private String firstName;
    @NotNull(message = "Last name cannot be null")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;
    @NotNull(message = "Phone number cannot be null")
    @Column(name = "phone_number")
    private String phoneNumber;


}
