package com.lpnu.poly.entity;

import com.lpnu.poly.entity.mapper.Convertable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "message")
public class Message implements Convertable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "file_uri",nullable = true)
    private String fileURI;

    @Column(name = "message_date", nullable = false)
    private LocalDateTime messageDate;

}