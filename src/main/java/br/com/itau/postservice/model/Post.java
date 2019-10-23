package br.com.itau.postservice.model;

import br.com.itau.postservice.jsonplaceholder.payload.PostPayload;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.Instant;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

@Entity
public class Post {

    @Id
    private Long id;

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;

    @NotNull
    @Length(max = 255)
    private String title;

    @NotNull
    @Length(max = 255)
    private String body;

    @CreationTimestamp
    private Instant createDate;

    @UpdateTimestamp
    private Instant updateDate;

    protected Post() {}

    public Post(PostPayload payload) {
        this.id = payload.getId();
        this.title = payload.getTitle();
        this.body = payload.getBody();
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

}
