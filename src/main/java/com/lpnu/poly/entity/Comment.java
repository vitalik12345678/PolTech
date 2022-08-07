package com.lpnu.poly.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lpnu.poly.entity.mapper.Convertable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment implements Convertable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    @JsonManagedReference
    private Post post;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "file_uri",nullable = true)
    private String fileURI;

    @OneToMany(mappedBy = "comment")
    private List<CommentLike> commentLikes;


}