package com.lpnu.poly.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "published_date", nullable = false)
    private Instant publishedDate;

    @Column(name = "title", nullable = false, length = 60)
    private String title;

    @Column(name = "estimat")
    private Integer estimat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "files_id")
    private File files;

    @OneToMany(mappedBy = "post")
    private Set<PostEstimant> postEstimants = new LinkedHashSet<>();

    @OneToMany(mappedBy = "post")
    private Set<PostHobby> postHobbies = new LinkedHashSet<>();

    @OneToMany(mappedBy = "post")
    private Set<PostBranch> postBranches = new LinkedHashSet<>();

    @OneToMany(mappedBy = "post")
    private Set<PostLike> postLikes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments = new LinkedHashSet<>();

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

    public Set<PostBranch> getPostBranches() {
        return postBranches;
    }

    public void setPostBranches(Set<PostBranch> postBranches) {
        this.postBranches = postBranches;
    }

    public Set<PostHobby> getPostHobbies() {
        return postHobbies;
    }

    public void setPostHobbies(Set<PostHobby> postHobbies) {
        this.postHobbies = postHobbies;
    }

    public Set<PostEstimant> getPostEstimants() {
        return postEstimants;
    }

    public void setPostEstimants(Set<PostEstimant> postEstimants) {
        this.postEstimants = postEstimants;
    }

    public File getFiles() {
        return files;
    }

    public void setFiles(File files) {
        this.files = files;
    }

    public Integer getEstimat() {
        return estimat;
    }

    public void setEstimat(Integer estimat) {
        this.estimat = estimat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Instant publishedDate) {
        this.publishedDate = publishedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}