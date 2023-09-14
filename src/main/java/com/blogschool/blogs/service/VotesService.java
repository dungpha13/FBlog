package com.blogschool.blogs.service;

import com.blogschool.blogs.entity.BlogPostEntity;
import com.blogschool.blogs.entity.ResponeObject;
import com.blogschool.blogs.entity.UserEntity;
import com.blogschool.blogs.entity.VoteEntity;
import com.blogschool.blogs.repository.BlogPostRepository;
import com.blogschool.blogs.repository.UserRepository;
import com.blogschool.blogs.repository.VotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotesService {

    private final VotesRepository votesRepository;
    private final BlogPostRepository blogPostRepository;
    private final UserRepository userRepository;

    @Autowired
    public VotesService(VotesRepository votesRepository, BlogPostRepository blogPostRepository, UserRepository userRepository) {
        this.votesRepository = votesRepository;
        this.blogPostRepository = blogPostRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<ResponeObject> insertVotes(Long voteValue, Long postId, Long userId) {
        Optional<BlogPostEntity> blogPostEntity = blogPostRepository.findById(postId);
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (blogPostEntity.isPresent() && userEntity.isPresent()) {
            BlogPostEntity blogPost = blogPostEntity.get();
            UserEntity user = userEntity.get();
            VoteEntity voteEntity = new VoteEntity(voteValue, user, blogPost);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("ok", "vote have been inserted", votesRepository.save(voteEntity)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("failed", "vote can not insert", ""));
        }
    }
}
