package br.com.itau.postservice.model;


import br.com.itau.postservice.jsonplaceholder.payload.CommentPayload;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

@Entity
public class Comment {

    @Id
    private Long id;

    @ManyToOne(targetEntity = Post.class)
    private Post post;

    @NotNull
    @Length(max = 255)
    private String name;

    @NotNull
    @Length(max = 255)
    private String email;

    @NotNull
    @Length(max = 65535)
    private String body;

    @CreationTimestamp
    private Instant createDate;

    @UpdateTimestamp
    private Instant updateDate;

    protected Comment() {}

    public Comment(CommentPayload payload, Post post) {
        this.id = payload.getId();
        this.post = post;
        this.name = payload.getName();
        this.email = payload.getEmail();
        this.body = payload.getBody();
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

}
