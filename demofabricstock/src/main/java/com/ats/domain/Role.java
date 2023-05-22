package com.ats.domain;

import com.ats.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tbl_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    @Column(length = 30, nullable = false)
    private UserRole userRole;

    @Override
    public String toString() {
        return "Role{" +
                "userRole=" + userRole +
                '}';
    }
}
