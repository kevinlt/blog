package fr.sawox.blog.application.adapter.out.persistence;

import fr.sawox.blog.adapter.out.persistence.PostJpaEntity;
import fr.sawox.blog.adapter.out.persistence.PostJpaRepository;
import fr.sawox.blog.application.domain.model.Post;
import fr.sawox.blog.application.port.out.PostPort;
import fr.sawox.blog.common.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.UUID;

public class PostPersistenceAdapterTest extends AbstractBaseIntegration {

    @Autowired
    private PostJpaRepository postJpaRepository;

    @Autowired
    private PostPort postPort;

    @Test
    void should_save_a_post() {
        // Given
        var postSnapshot = new Post.PostSnapshot(
                UUID.fromString("d9b0a4d0-4f1a-4b0e-8b1a-9b0a4d0f1a4b"),
                "title",
                "content",
                Status.DRAFT,
                Instant.now(),
                null,
                null);
        postPort.addPost(Post.fromSnapshot(postSnapshot));
        // When
        PostJpaEntity postJpaEntity = postJpaRepository.findAll().getFirst();
        // Then
        assert postJpaEntity != null;
    }

    @Test
    void should_retrieve_all_posts() {
        // Given
        var postSnapshot = new Post.PostSnapshot(
                UUID.fromString("d9b0a4d0-4f1a-4b0e-8b1a-9b0a4d0f1a4b"),
                "title",
                "content",
                Status.DRAFT,
                Instant.now(),
                null,
                null);
        postPort.addPost(Post.fromSnapshot(postSnapshot));

        var postSnapshot2 = new Post.PostSnapshot(
                UUID.fromString("d9b0a4d0-4f1a-4b0e-8b1a-9b0a4d0f1a4c"),
                "title2",
                "content2",
                Status.DRAFT,
                Instant.now(),
                null,
                null);
        postPort.addPost(Post.fromSnapshot(postSnapshot2));
        // When
        var posts = postPort.getPosts();
        // Then
        assert posts.size() == 2;
    }

    @Test
    void should_retrieve_all_posts_by_status() {
        // Given
        var postSnapshot = new Post.PostSnapshot(
                UUID.fromString("d9b0a4d0-4f1a-4b0e-8b1a-9b0a4d0f1a4b"),
                "title",
                "content",
                Status.DRAFT,
                Instant.now(),
                null,
                null);
        postPort.addPost(Post.fromSnapshot(postSnapshot));

        var postSnapshot2 = new Post.PostSnapshot(
                UUID.fromString("d9b0a4d0-4f1a-4b0e-8b1a-9b0a4d0f1a4c"),
                "title2",
                "content2",
                Status.PUBLISHED,
                Instant.now(),
                null,
                null);
        postPort.addPost(Post.fromSnapshot(postSnapshot2));

        var postSnapshot3 = new Post.PostSnapshot(
                UUID.fromString("d9b0a4d0-4f1a-4b0e-8b1a-9b0a4d0f1a4d"),
                "title3",
                "content3",
                Status.DRAFT,
                Instant.now(),
                null,
                null);
        postPort.addPost(Post.fromSnapshot(postSnapshot3));
        // When
        var posts = postPort.getPostsByStatus(Status.DRAFT);
        // Then
        assert posts.size() == 2;
    }

}
