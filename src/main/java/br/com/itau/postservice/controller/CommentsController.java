package br.com.itau.postservice.controller;

import br.com.itau.postservice.jsonplaceholder.JsonPlaceholderClient;
import br.com.itau.postservice.jsonplaceholder.payload.Comment;
import br.com.itau.postservice.model.SaveCommentsExecution;
import br.com.itau.postservice.repository.SaveCommentsExecutionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comments")
public class CommentsController {

    private JsonPlaceholderClient jsonPlaceholderClient;
    private SaveCommentsExecutionRepository saveCommentsExecutionRepository;

    @Autowired
    public CommentsController(JsonPlaceholderClient jsonPlaceholderClient,
            SaveCommentsExecutionRepository saveCommentsExecutionRepository) {
        this.jsonPlaceholderClient = jsonPlaceholderClient;
        this.saveCommentsExecutionRepository = saveCommentsExecutionRepository;
    }

    @GetMapping
    public List<Comment> list() {
        return jsonPlaceholderClient.fetchComments();
    }

    @PostMapping
    public SaveCommentsExecution save() {
        SaveCommentsExecution saveCommentsExecution = new SaveCommentsExecution();

        return saveCommentsExecutionRepository.save(saveCommentsExecution);
    }

}
