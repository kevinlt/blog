package fr.sawox.blog.application.domain.model;

import fr.sawox.blog.common.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class Post {

    private final UUID id;

    private String title;

    private String content;

    private Instant creationDate;

    private Instant updatedDate;

    private Instant publicationDate;

    private Status status;

    public Post(String title, String content) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
    }

    public static Post fromSnapshot(PostSnapshot snapshot) {
        return new Post(snapshot.id, snapshot.title, snapshot.content, snapshot.creationDate, snapshot.updatedDate, snapshot.publicationDate, snapshot.status);
    }

    public PostSnapshot toSnapshot() {
        return new PostSnapshot(id, title, content, status, creationDate, updatedDate, publicationDate);
    }

    public void publish() {
        this.status = Status.PUBLISHED;
        this.publicationDate = Instant.now();
        this.updatedDate = Instant.now();
    }

    public record PostSnapshot(UUID id, String title, String content, Status status, Instant creationDate, Instant updatedDate, Instant publicationDate) {
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
    }

}
