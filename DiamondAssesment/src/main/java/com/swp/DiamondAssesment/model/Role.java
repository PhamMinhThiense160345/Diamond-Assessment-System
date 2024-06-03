package com.swp.DiamondAssesment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Roles")
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 50)
    @Nationalized
    private String RoleName;

    @OneToMany(mappedBy = "role_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<User> userList = new ArrayList<>();

}
