package br.com.itau.postservice.controller;

import br.com.itau.postservice.model.Post;
import br.com.itau.postservice.repository.PostsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("posts")
public class PostsController {

    private PostsRepository postsRepository;

    @Autowired
    public PostsController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @GetMapping
    public Page<Post> list(Pageable pageable) {
        return postsRepository.findAll(pageable);
    }

    @GetMapping("top")
    public List<Post> listTop() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        return postsRepository.findTopPosts(pageRequest);
    }

}
