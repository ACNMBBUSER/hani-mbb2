package com.profile.profile.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProfileDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="name", nullable = false)
    @NotBlank(message = "Name should not be null")
    private String name;

    @Column(name="age", nullable = false)
    private int age;

    @Column(name="occupation", nullable = false)
    @NotBlank(message = "Occupation should not be null")
    private String occupation;

    @Column(name="phone_number", nullable = false)
    @NotBlank(message = "Phone Number should not be null")
    private String phoneNumber;


}
