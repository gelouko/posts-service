package br.com.itau.postservice.controller;

import br.com.itau.postservice.jsonplaceholder.JsonPlaceholderClient;
import br.com.itau.postservice.jsonplaceholder.payload.Post;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("posts")
public class PostsController {

    private JsonPlaceholderClient jsonPlaceholderClient;

    @Autowired
    public PostsController(JsonPlaceholderClient jsonPlaceholderClient) {
        this.jsonPlaceholderClient = jsonPlaceholderClient;
    }

    @GetMapping
    public List<Post> list(@RequestParam List<Long> id) {
        return jsonPlaceholderClient.fetchPosts(id);
    }
}
