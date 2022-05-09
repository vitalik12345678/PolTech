package com.lpnu.poly.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "graduation_year", nullable = false)
    private Integer graduationYear;

    @Column(name = "work", length = 225)
    private String work;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "middle_name", nullable = false, length = 30)
    private String middleName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar")
    private File avatar;

    @OneToMany(mappedBy = "owner")
    private List<Party> parties;

    @OneToMany(mappedBy = "user")
    private List<PostEstimant> postEstimants;

    @OneToMany(mappedBy = "user")
    private List<UserHobby> userHobbies;

    @OneToMany(mappedBy = "user")
    private List<PartyUser> partyUsers;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "firstUser")
    private List<Chat> chatTT;

    @OneToMany(mappedBy = "secondUser")
    private List<Chat> chats;

    @OneToMany(mappedBy = "user")
    private List<PostLike> postLikes;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Message> messages;

    @OneToMany(mappedBy = "user")
    private List<UserBranch> userBranches;

    @OneToMany(mappedBy = "user")
    private List<CommentLike> commentLikes;
}