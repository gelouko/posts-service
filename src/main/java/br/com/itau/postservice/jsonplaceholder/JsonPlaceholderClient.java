package br.com.itau.postservice.jsonplaceholder;

import static br.com.itau.postservice.exceptions.PostServiceExceptionType.JSON_PLACEHOLDER_RESPONSE_ERROR;

import br.com.itau.postservice.exceptions.PostServiceException;
import br.com.itau.postservice.exceptions.PostServiceExceptionType;
import br.com.itau.postservice.jsonplaceholder.payload.Comment;
import java.util.List;
import javax.naming.CommunicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class JsonPlaceholderClient {

    private RestTemplate restTemplate;

    @Autowired
    public JsonPlaceholderClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Comment> fetchComments() {
        ResponseEntity<List<Comment>> response = restTemplate
                .exchange("https://jsonplaceholder.typicode.com/comments", HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<Comment>>() {
                        });

        if (response.getStatusCode().isError()) {
            throw new PostServiceException(JSON_PLACEHOLDER_RESPONSE_ERROR);
        }

        return response.getBody();
    }
}
