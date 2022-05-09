package com.lpnu.poly.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "first_user_id", nullable = false)
    private User firstUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "second_user_id", nullable = false)
    private User secondUser;

    @OneToMany(mappedBy = "chat")
    private List<File> files;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages;
}