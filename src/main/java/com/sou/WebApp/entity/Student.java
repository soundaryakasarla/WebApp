package com.sou.WebApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String emailId;
    private String department;
    private String marks;
//    private String studentClass;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;



}

//For Creation of table, since we don't create a table directly we use a class
// and pass in the values


