package tech.fublog.FuBlog.controller;

import tech.fublog.FuBlog.dto.request.RequestCommentDTO;
import tech.fublog.FuBlog.dto.response.ResponseCommentDTO;
import tech.fublog.FuBlog.model.ResponseObject;
import tech.fublog.FuBlog.exception.CommentException;
import tech.fublog.FuBlog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/blogPosts/comment")
@CrossOrigin(origins = {"http://localhost:5173", "https://fublog.tech"})
//@CrossOrigin(origins = "*")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertComment(@RequestBody ResponseCommentDTO commentDTO) {
        try {
            commentService.insertComment(commentDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("ok", "Comment have been inserted", ""));
        } catch (CommentException ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject("failed", ex.getMessage(), ""));
        }
    }

    @GetMapping("/view/{postId}")
    public ResponseEntity<ResponseObject> viewComment(@PathVariable Long postId) {
        try {
            List<ResponseCommentDTO> dtoList = commentService.viewComment(postId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("ok", "found", dtoList));
        } catch (CommentException ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject("failed", ex.getMessage(), ""));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseObject> updateComment(@RequestBody RequestCommentDTO requestCommentDTO) {
        try {
            commentService.updateComment(requestCommentDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("ok", "Comment have been updated", ""));
        } catch (CommentException ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject("failed", ex.getMessage(), ""));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseObject> deleteComment(
            @RequestBody RequestCommentDTO requestCommentDTO) {
        try {
            commentService.deleteComment(requestCommentDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("ok", "Comment have been deleted", ""));
        } catch (CommentException ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject("failed", ex.getMessage(), ""));
        }
    }

    @GetMapping("/count/{postId}")
    public ResponseEntity<ResponseObject> countComment(@PathVariable Long postId) {
        try {
            Long count = commentService.countComment(postId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("ok", "found", count));
        } catch (CommentException ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject("failed", ex.getMessage(), ""));
        }
    }

}
