package br.com.itau.postservice.jsonplaceholder;

import static br.com.itau.postservice.exceptions.PostServiceExceptionType.JSON_PLACEHOLDER_RESPONSE_ERROR;

import br.com.itau.postservice.exceptions.PostServiceException;
import br.com.itau.postservice.jsonplaceholder.payload.CommentPayload;
import br.com.itau.postservice.jsonplaceholder.payload.PostPayload;
import java.util.List;
import java.util.Set;
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
    private String baseUrl;

    @Autowired
    public JsonPlaceholderClient(RestTemplate restTemplate,
            @Value("${json-placeholder-api.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public List<CommentPayload> fetchComments() {
        String uri = UriComponentsBuilder.fromHttpUrl(baseUrl).path("/comments").toUriString();

        ResponseEntity<List<CommentPayload>> response = restTemplate
                .exchange(uri, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<CommentPayload>>() {});

        rejectIfError(response);

        return response.getBody();
    }

    public List<PostPayload> fetchPosts(Set<Long> ids) {
        String uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/posts")
                .queryParam("id", ids)
                .toUriString();

        ResponseEntity<List<PostPayload>> response = restTemplate
                .exchange(uri, HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<PostPayload>>() {
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
