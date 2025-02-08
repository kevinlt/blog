package fr.sawox.blog.application.domain.service;

import fr.sawox.blog.application.adapter.out.MockPostPort;
import fr.sawox.blog.application.domain.model.Post;
import fr.sawox.blog.application.port.in.PublishAPostUseCase;
import fr.sawox.blog.common.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class PublishPostServiceTest {

    private PublishAPostUseCase publishAPostUseCase;

    private final MockPostPort postPort = new MockPostPort();

    @BeforeEach
    void setUp() {
        // Given
        publishAPostUseCase = new PublishPostService(postPort);
    }

    @Test
    void should_publish_a_post() {
        Post post = new Post("title", "content");
        // Given
        Post.PostSnapshot postSnapshot = postPort.addPost(post);
        // When
        publishAPostUseCase.publishPost(postSnapshot);
        // Then
        Post.PostSnapshot publishPost = postPort.getPost(postSnapshot.id());
        assertTrue("Post should be published", publishPost.isPublished());
    }

    @Test
    void publish_post_should_have_a_publication_date() {
        Post post = new Post("title", "content");
        // Given
        Post.PostSnapshot postSnapshot = postPort.addPost(post);
        // When
        publishAPostUseCase.publishPost(postSnapshot);
        // Then
        Post.PostSnapshot publishPost = postPort.getPost(postSnapshot.id());
        assertTrue("Post should have a published date", publishPost.publicationDate() != null);
    }
}
