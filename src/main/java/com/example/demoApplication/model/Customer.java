package com.example.demoApplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="Customers")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;

}
