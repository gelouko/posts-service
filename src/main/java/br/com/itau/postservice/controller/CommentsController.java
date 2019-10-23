package br.com.itau.postservice.controller;

import static br.com.itau.postservice.exceptions.PostServiceExceptionType.DATABASE_ALREADY_POPULATED;

import br.com.itau.postservice.exceptions.PostServiceException;
import br.com.itau.postservice.jsonplaceholder.JsonPlaceholderClient;
import br.com.itau.postservice.jsonplaceholder.payload.CommentPayload;
import br.com.itau.postservice.model.Comment;
import br.com.itau.postservice.model.Post;
import br.com.itau.postservice.repository.CommentsRepository;
import br.com.itau.postservice.repository.PostRepository;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comments")
public class CommentsController {

    private JsonPlaceholderClient jsonPlaceholderClient;
    private PostRepository postRepository;
    private CommentsRepository commentsRepository;

    @Autowired
    public CommentsController(JsonPlaceholderClient jsonPlaceholderClient,
            PostRepository postRepository,
            CommentsRepository commentsRepository) {
        this.jsonPlaceholderClient = jsonPlaceholderClient;
        this.postRepository = postRepository;
        this.commentsRepository = commentsRepository;
    }

    @GetMapping
    public Page<Comment> list(Pageable pageable) {
        return commentsRepository.findAll(pageable);
    }

    @PostMapping
    public void save() {
        if (postRepository.count() > 0) {
            throw new PostServiceException(DATABASE_ALREADY_POPULATED);
        }

        List<CommentPayload> commentPayloads = jsonPlaceholderClient.fetchComments();

        Map<Long, List<CommentPayload>> postComments = commentPayloads.stream()
                .collect(Collectors.groupingBy(CommentPayload::getPostId));

        List<Post> posts = postRepository
                .saveAll(jsonPlaceholderClient.fetchPosts(postComments.keySet()).stream()
                        .map(Post::new).collect(Collectors.toSet()));

        Map<Long, Post> postsMap = posts.stream()
                .collect(Collectors.toMap(Post::getId, post -> post));

        Set<Comment> comments = commentPayloads.stream().map(comment -> new Comment(comment,
                postsMap.get(comment.getPostId()))).collect(Collectors.toSet());

        commentsRepository.saveAll(comments);
    }

}
