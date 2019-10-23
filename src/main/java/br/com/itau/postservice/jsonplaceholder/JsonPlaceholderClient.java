package br.com.itau.postservice.jsonplaceholder;

import static br.com.itau.postservice.exceptions.PostServiceExceptionType.JSON_PLACEHOLDER_RESPONSE_ERROR;

import br.com.itau.postservice.exceptions.PostServiceException;
import br.com.itau.postservice.jsonplaceholder.payload.Comment;
import br.com.itau.postservice.jsonplaceholder.payload.Post;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class JsonPlaceholderClient {

    private final RestTemplate restTemplate;
    private final UriComponentsBuilder uriComponentsBuilder;

    @Autowired
    public JsonPlaceholderClient(RestTemplate restTemplate,
            @Value("${json-placeholder-api.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl);
    }

    public List<Comment> fetchComments() {
        String uri = uriComponentsBuilder.path("/comments").toUriString();

        ResponseEntity<List<Comment>> response = restTemplate
                .exchange(uri, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Comment>>() {});

        rejectIfError(response);

        return response.getBody();
    }

    public List<Post> fetchPosts(List<Long> ids) {
        String uri = uriComponentsBuilder
                .path("/comments")
                .queryParam("id", ids)
                .toUriString();

        ResponseEntity<List<Post>> response = restTemplate
                .exchange(uri, HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<Post>>() {
                        });

        rejectIfError(response);

        return response.getBody();
    }

    private void rejectIfError(ResponseEntity response) {
        if (response.getStatusCode().isError()) {
            throw new PostServiceException(JSON_PLACEHOLDER_RESPONSE_ERROR);
        }
    }
}
