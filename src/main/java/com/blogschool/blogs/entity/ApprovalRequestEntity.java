package com.blogschool.blogs.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "ApprovalRequest")
public class ApprovalRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private boolean isApproved;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private UserEntity request;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private UserEntity review;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private BlogPostEntity blogPost;



}
