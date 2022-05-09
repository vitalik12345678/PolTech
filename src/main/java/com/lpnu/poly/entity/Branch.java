package com.lpnu.poly.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 160)
    private String name;

    @OneToMany(mappedBy = "branch")
    private Set<PostBranch> postBranches = new LinkedHashSet<>();

    @OneToMany(mappedBy = "branch")
    private Set<UserBranch> userBranches = new LinkedHashSet<>();

    public Set<UserBranch> getUserBranches() {
        return userBranches;
    }

    public void setUserBranches(Set<UserBranch> userBranches) {
        this.userBranches = userBranches;
    }

    public Set<PostBranch> getPostBranches() {
        return postBranches;
    }

    public void setPostBranches(Set<PostBranch> postBranches) {
        this.postBranches = postBranches;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}