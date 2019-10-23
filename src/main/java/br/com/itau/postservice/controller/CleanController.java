package br.com.itau.postservice.controller;

import br.com.itau.postservice.repository.CommentsRepository;
import br.com.itau.postservice.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is used ONLY FOR TESTS, IF NECESSARY!
 *
 * This is not part of the application itself!
 */
@RestController
@RequestMapping("clean")
public class CleanController {

    private PostsRepository postsRepository;
    private CommentsRepository commentsRepository;

    @Autowired
    public CleanController(PostsRepository postsRepository, CommentsRepository commentsRepository) {
        this.postsRepository = postsRepository;
        this.commentsRepository = commentsRepository;
    }

    @DeleteMapping
    @Transactional
    public void cleanDb() {
        commentsRepository.deleteAll();
        postsRepository.deleteAll();
    }

}
