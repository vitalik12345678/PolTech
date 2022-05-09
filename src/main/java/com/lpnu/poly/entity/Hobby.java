package com.lpnu.poly.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "hobby")
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @OneToMany(mappedBy = "hobby")
    private Set<UserHobby> userHobbies = new LinkedHashSet<>();

    @OneToMany(mappedBy = "hobby")
    private Set<PostHobby> postHobbies = new LinkedHashSet<>();

    public Set<PostHobby> getPostHobbies() {
        return postHobbies;
    }

    public void setPostHobbies(Set<PostHobby> postHobbies) {
        this.postHobbies = postHobbies;
    }

    public Set<UserHobby> getUserHobbies() {
        return userHobbies;
    }

    public void setUserHobbies(Set<UserHobby> userHobbies) {
        this.userHobbies = userHobbies;
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
}