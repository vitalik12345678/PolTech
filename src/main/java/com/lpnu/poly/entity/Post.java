package com.lpnu.poly.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lpnu.poly.entity.mapper.Convertable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post implements Convertable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column(name = "published_date", nullable = false)
    private LocalDateTime publishedDate;

    @Column(name = "title", nullable = false, length = 60)
    private String title;

    @Column(name = "context")
    private String context;

    @Column(name = "avatar_uri")
    private String avatarURI;

    @Column(name = "comments_available")
    private Boolean commentsAvailable;

    @OneToMany(mappedBy = "post")
    private List<PostEstimant> postEstimants;

    @OneToMany(mappedBy = "post")
    private List<PostHobby> postHobbies;

    @OneToMany(mappedBy = "post")
    private List<PostBranch> postBranches;

    @OneToMany(mappedBy = "post")
    private List<PostLike> postLikes;

    @OneToMany(mappedBy = "post")
    @JsonBackReference
    private List<Comment> comments;

}