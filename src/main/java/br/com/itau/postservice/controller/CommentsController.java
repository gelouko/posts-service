package br.com.itau.postservice.controller;

import br.com.itau.postservice.jsonplaceholder.JsonPlaceholderClient;
import br.com.itau.postservice.jsonplaceholder.payload.Comment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comments")
public class CommentsController {

    private JsonPlaceholderClient jsonPlaceholderClient;

    @Autowired
    public CommentsController(JsonPlaceholderClient jsonPlaceholderClient) {
        this.jsonPlaceholderClient = jsonPlaceholderClient;
    }

    @GetMapping
    public List<Comment> list() {
        return jsonPlaceholderClient.fetchComments();
    }
}
