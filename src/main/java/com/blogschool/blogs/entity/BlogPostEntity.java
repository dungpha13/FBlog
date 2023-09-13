package com.blogschool.blogs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "BlogPost")
public class BlogPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String typePost;

    @Column
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Column
    @CreatedDate
    private Date createdDate;

    @Column
    @LastModifiedDate
    private Date modifiedDate;

    @Column
    private Long approvedBy;

    @Column
    private Boolean isApproved;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private CategoryEntity category;


    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private UserEntity authors;
    @ManyToOne
    @JoinColumn(name = "authorModified_id")
    @JsonIgnore
    private UserEntity authorsModified;

    @OneToMany(mappedBy = "postVote")
    @JsonIgnore
    private List<VoteEntity> votes = new ArrayList<>();

    @OneToMany(mappedBy = "blogPost")
    @JsonIgnore
    private List<ApprovalRequestEntity> approvalRequests = new ArrayList<>();

    @OneToMany(mappedBy = "postComment")
    @JsonIgnore
    private List<CommentEntity> postComments = new ArrayList<>();

    @ManyToMany(mappedBy = "blogPosts")
    @JsonIgnore

    private List<TagEntity> tags = new ArrayList<>();

    public List<VoteEntity> getVotes() {
        return votes;
    }

    public void setVotes(List<VoteEntity> votes) {
        this.votes = votes;
    }

    public List<CommentEntity> getComments() {
        return postComments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.postComments = postComments;
    }

    public List<TagEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
    }

    public Long getPostId() {
        return Id;
    }


    public String getTypePost() {
        return typePost;
    }

    public void setTypePost(String typePost) {
        this.typePost = typePost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Long approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public UserEntity getAuthors() {
        return authors;
    }

    public void setAuthors(UserEntity authors) {
        this.authors = authors;
    }

    public UserEntity getAuthorsModified() {
        return authorsModified;
    }

    public void setAuthorsModified(UserEntity authorsModified) {
        this.authorsModified = authorsModified;
    }
}
