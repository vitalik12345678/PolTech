package com.lpnu.poly.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "party")
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private User owner;

    @OneToMany(mappedBy = "party")
    private Set<PartyUser> partyUsers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "party")
    private Set<File> files = new LinkedHashSet<>();

    @OneToMany(mappedBy = "party")
    private Set<Message> messages = new LinkedHashSet<>();

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<File> getFiles() {
        return files;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }

    public Set<PartyUser> getPartyUsers() {
        return partyUsers;
    }

    public void setPartyUsers(Set<PartyUser> partyUsers) {
        this.partyUsers = partyUsers;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}