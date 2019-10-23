package br.com.itau.postservice.model;

import java.time.Instant;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class SaveCommentsExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private UUID identifier;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "create_date")
    @CreationTimestamp
    private Instant createDate;

    @UpdateTimestamp
    private Instant updateDate;

    public SaveCommentsExecution() {
        this.status = Status.PENDING;
        this.identifier = UUID.randomUUID();
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public Status getStatus() {
        return status;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    private enum Status {
        PENDING,
        FETCHING_COMMENTS,
        SAVING_COMMENTS,
        FETCHING_POSTS,
        SAVING_POSTS,
        FINISHED
    }

}
