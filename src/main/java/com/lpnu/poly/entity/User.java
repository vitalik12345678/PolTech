package com.lpnu.poly.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

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
    private Set<Party> parties = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<PostEstimant> postEstimants = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserHobby> userHobbies = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<PartyUser> partyUsers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Post> posts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "firstUser")
    private Set<Chat> chatTT = new LinkedHashSet<>();

    @OneToMany(mappedBy = "secondUser")
    private Set<Chat> chats = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<PostLike> postLikes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Message> messages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserBranch> userBranches = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<CommentLike> commentLikes = new LinkedHashSet<>();

    public Set<CommentLike> getCommentLikes() {
        return commentLikes;
    }

    public void setCommentLikes(Set<CommentLike> commentLikes) {
        this.commentLikes = commentLikes;
    }

    public Set<UserBranch> getUserBranches() {
        return userBranches;
    }

    public void setUserBranches(Set<UserBranch> userBranches) {
        this.userBranches = userBranches;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<PostLike> getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(Set<PostLike> postLikes) {
        this.postLikes = postLikes;
    }

    public Set<Chat> getChats() {
        return chats;
    }

    public void setChats(Set<Chat> chats) {
        this.chats = chats;
    }

    public Set<Chat> getChatTT() {
        return chatTT;
    }

    public void setChatTT(Set<Chat> chatTT) {
        this.chatTT = chatTT;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<PartyUser> getPartyUsers() {
        return partyUsers;
    }

    public void setPartyUsers(Set<PartyUser> partyUsers) {
        this.partyUsers = partyUsers;
    }

    public Set<UserHobby> getUserHobbies() {
        return userHobbies;
    }

    public void setUserHobbies(Set<UserHobby> userHobbies) {
        this.userHobbies = userHobbies;
    }

    public Set<PostEstimant> getPostEstimants() {
        return postEstimants;
    }

    public void setPostEstimants(Set<PostEstimant> postEstimants) {
        this.postEstimants = postEstimants;
    }

    public Set<Party> getParties() {
        return parties;
    }

    public void setParties(Set<Party> parties) {
        this.parties = parties;
    }

    public File getAvatar() {
        return avatar;
    }

    public void setAvatar(File avatar) {
        this.avatar = avatar;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}