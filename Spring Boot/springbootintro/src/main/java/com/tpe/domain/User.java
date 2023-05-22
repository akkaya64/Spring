package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name= "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, nullable = false)
    private String firstName;

    @Column(length = 25, nullable = false)
    private String lastName;

    @Column(length = 25, nullable = false, unique = true)
    private String userName;

    @Column(length = 255, nullable = false)
    private String password;

    @JoinTable(name="tbl_user_role",
     joinColumns = @JoinColumn(name="user_id"),
     inverseJoinColumns = @JoinColumn(name="role_id"))
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> role = new HashSet<>(); // Admin ; Student

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Student student;



}
