package com.blogschool.blogs.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Votes")
public class VoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long Id;

    @Column
    private Long voteValue; // 1 for upvote, -1 for downvote

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userVote;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private BlogPostEntity postVote;

    public VoteEntity(Long voteValue, UserEntity userVote, BlogPostEntity postVote) {
        this.voteValue = voteValue;
        this.userVote = userVote;
        this.postVote = postVote;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userVote, postVote);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        VoteEntity vote = (VoteEntity) obj;
        return Objects.equals(userVote, vote.getUserVote()) &&
                Objects.equals(postVote, vote.getPostVote());
    }
}
