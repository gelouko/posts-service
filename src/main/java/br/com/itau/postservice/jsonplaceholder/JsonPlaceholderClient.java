package br.com.itau.postservice.jsonplaceholder;

import static br.com.itau.postservice.exceptions.PostServiceExceptionType.JSON_PLACEHOLDER_RESPONSE_ERROR;

import br.com.itau.postservice.exceptions.PostServiceException;
import br.com.itau.postservice.exceptions.PostServiceExceptionType;
import br.com.itau.postservice.jsonplaceholder.payload.Comment;
import br.com.itau.postservice.jsonplaceholder.payload.Post;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.naming.CommunicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

    public List<Post> fetchPosts(List<Long> ids) {
        String uri = UriComponentsBuilder
                .fromHttpUrl("https://jsonplaceholder.typicode.com")
                .path("/comments")
                .queryParam("id", ids)
                .toUriString();

        ResponseEntity<List<Post>> response = restTemplate
                .exchange(uri, HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<Post>>() {
                        });

        if (response.getStatusCode().isError()) {
            throw new PostServiceException(JSON_PLACEHOLDER_RESPONSE_ERROR);
        }

        return response.getBody();
    }
}
