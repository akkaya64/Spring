package com.ats.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(length = 225, nullable = false)
    private String firstName;
    @Column(length = 225, nullable = false)
    private String lastName;
    @Column(length = 225, nullable = false)
    private String userName;

    @JoinTable(name = "tbl_user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roleSet = new HashSet<>();

    @JoinTable(name = "tbl_user_address",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "address_id"))
    @OneToOne(fetch =FetchType.LAZY)
    private List<Address> addressString = new ArrayList<>();


}
