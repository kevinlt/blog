package fr.sawox.blog.application.domain.model;

import fr.sawox.blog.common.Status;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
public class Post {

    private final UUID id;

    private String title;

    private String content;

    private Instant creationDate;

    private Instant updatedDate;

    private Status status;

    public Post(String title, String content) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Status getStatus() {
        return status;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static Post fromSnapshot(PostSnapshot snapshot) {
        return new Post(snapshot.id, snapshot.title, snapshot.content, snapshot.creationDate, snapshot.updatedDate, snapshot.status);
    }

    public PostSnapshot toSnapshot() {
        return new PostSnapshot(id, title, content, status, creationDate, updatedDate);
    }


    public record PostSnapshot(UUID id, String title, String content, Status status, Instant creationDate, Instant updatedDate) {
        public boolean isDraft() {
            return status == Status.DRAFT;
        }

        public boolean isPublished() {
            return status == Status.PUBLISHED;
        }

        public boolean isArchived() {
            return status == Status.ARCHIVED;
        }

        public boolean isUpdated() {
            return updatedDate != null;
        }

        public void setTitle(String newTitle) {
        }
    }

}
