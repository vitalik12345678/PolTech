package com.lpnu.poly.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 160)
    private String name;

    @OneToMany(mappedBy = "branch")
    private List<PostBranch> postBranches;

    @OneToMany(mappedBy = "branch")
    private Set<UserBranch> userBranches = new LinkedHashSet<>();
}