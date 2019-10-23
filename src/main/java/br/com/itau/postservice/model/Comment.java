package br.com.itau.postservice.model;


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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Comment(Long id, Post post, String name, String email, String body) {
        this.id = id;
        this.post = post;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public Post getPost() {
        return post;
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
