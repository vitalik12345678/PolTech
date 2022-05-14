package com.lpnu.poly.entity;

import com.lpnu.poly.entity.mapper.Convertable;
import com.lpnu.poly.type.PostgresSQLEnumType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import org.hibernate.annotations.TypeDef;



@Getter
@Setter
@Entity
@Table(name = "users")
@TypeDef(name = "pgsql_enum", typeClass = PostgresSQLEnumType.class)
public class User implements Convertable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    @Column(columnDefinition = "user_type")

    private Graduate graduate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar")
    private File avatar;

    @ManyToOne
    @JoinColumn(name = "role")
    private Role role;

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