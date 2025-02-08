package fr.sawox.blog.application.domain.service;

import fr.sawox.blog.application.adapter.out.MockPostPort;
import fr.sawox.blog.application.domain.model.Post;
import fr.sawox.blog.application.port.in.UpdateAPostUseCase;
import fr.sawox.blog.application.port.out.PostPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UpdatePostServiceTest {

    private UpdateAPostUseCase updateAPostUseCase;

    private CreatePostService createPostService;

    private RetrievePostService retrievePostService;

    private PostPort postPort = new MockPostPort();

    @BeforeEach
    void setUp() {
        // Given
        updateAPostUseCase = new UpdatePostService(postPort);
        createPostService = new CreatePostService(postPort);
        retrievePostService = new RetrievePostService(postPort);
    }

    @Test
    void should_update_a_post() {
        // Given
        String title = "title";
        String content = "content";
        Post.PostSnapshot postSnapshot = createPostService.create(title, content);
        // When
        Post post = Post.fromSnapshot(postSnapshot);
        post.setTitle("new title");
        updateAPostUseCase.update(post.toSnapshot());
        // Then
        Post.PostSnapshot updatedPost = retrievePostService.retrieve(post.getId());
        assertNotNull(updatedPost);
        assertEquals("new title", updatedPost.title());
    }

    @Test
    void updated_post_should_have_an_updated_date() {
        // Given
        String title = "title";
        String content = "content";
        Post.PostSnapshot postSnapshot = createPostService.create(title, content);
        // When
        Post post = Post.fromSnapshot(postSnapshot);
        post.setTitle("new title");
        updateAPostUseCase.update(post.toSnapshot());
        // Then
        Post.PostSnapshot updatedPost = retrievePostService.retrieve(post.getId());
        assertNotNull(updatedPost);
        assertNotNull(updatedPost.updatedDate());
    }

    @Test
    void updated_post_should_have_the_same_creation_date() {
        // Given
        String title = "title";
        String content = "content";
        Post.PostSnapshot postSnapshot = createPostService.create(title, content);
        // When
        Post post = Post.fromSnapshot(postSnapshot);
        post.setTitle("new title");
        updateAPostUseCase.update(post.toSnapshot());
        // Then
        Post.PostSnapshot updatedPost = retrievePostService.retrieve(post.getId());
        assertNotNull(updatedPost);
        assertEquals(postSnapshot.creationDate(), updatedPost.creationDate());
    }
}
