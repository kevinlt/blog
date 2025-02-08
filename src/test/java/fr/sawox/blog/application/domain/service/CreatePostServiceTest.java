package fr.sawox.blog.application.domain.service;

import fr.sawox.blog.application.domain.model.Post;
import fr.sawox.blog.application.port.in.CreateAPostUseCase;
import fr.sawox.blog.application.port.out.PostPort;
import fr.sawox.blog.common.Status;
import fr.sawox.blog.application.adapter.out.MockPostPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreatePostServiceTest {

    private CreateAPostUseCase createAPostUseCase;

    private PostPort postPort = new MockPostPort();

    @BeforeEach
    void setUp() {
        createAPostUseCase = new CreatePostService(postPort);
    }

    @Test
    void should_create_a_post() {
        // Given
        String title = "title";
        String content = "content";
        // When
        Post.PostSnapshot post = createAPostUseCase.create(title, content);
        // Then
        assertNotNull(post);
    }

    @Test
    void post_should_be_in_draft_when_created() {
        // Given
        String title = "title";
        String content = "content";
        // When
        Post.PostSnapshot post = createAPostUseCase.create(title, content);
        // Then
        assertNotNull(post);
        assertEquals(Status.DRAFT, post.status());
    }

    @Test
    void created_post_should_have_an_id() {
        // Given
        String title = "title";
        String content = "content";
        // When
        Post.PostSnapshot post = createAPostUseCase.create(title, content);
        // Then
        assertNotNull(post);
        assertNotNull(post.id());
    }

    @Test
    void created_post_sould_have_a_creation_date() {
        // Given
        String title = "title";
        String content = "content";
        // When
        Post.PostSnapshot post = createAPostUseCase.create(title, content);
        // Then
        assertNotNull(post);
        assertNotNull(post.creationDate());
    }
}
