package com.ecommerce.app.account.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {
    private Long id;

    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @Column(name = "email")
    private String email;

    @NotNull(message = "Phone number cannot be null")
    @Min(value = 10, message = "Phone number must be at least 10 characters long")
    private String phoneNumber;
}
