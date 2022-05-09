package com.lpnu.poly.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "party")
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private User owner;

    @OneToMany(mappedBy = "party")
    private List<PartyUser> partyUsers;

    @OneToMany(mappedBy = "party")
    private List<File> files;

    @OneToMany(mappedBy = "party")
    private List<Message> messages;

}