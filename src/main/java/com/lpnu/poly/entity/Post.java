package com.lpnu.poly.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "published_date", nullable = false)
    private LocalDateTime publishedDate;

    @Column(name = "title", nullable = false, length = 60)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "files_id")
    private File files;

    @OneToMany(mappedBy = "post")
    private List<PostEstimant> postEstimants;

    @OneToMany(mappedBy = "post")
    private List<PostHobby> postHobbies;

    @OneToMany(mappedBy = "post")
    private List<PostBranch> postBranches;

    @OneToMany(mappedBy = "post")
    private List<PostLike> postLikes;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

}