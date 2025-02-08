package fr.sawox.blog.application.domain.service;

import fr.sawox.blog.application.domain.model.Post;
import fr.sawox.blog.application.port.in.RetrieveAPostUseCase;
import fr.sawox.blog.application.adapter.out.MockPostPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RetrievePostServiceTest {

    private RetrieveAPostUseCase retrieveAPostUseCase;

    private CreatePostService createPostService;

    private MockPostPort mockPostRepository = new MockPostPort();

    @BeforeEach
    void setUp() {
        retrieveAPostUseCase = new RetrievePostService(mockPostRepository);
        createPostService = new CreatePostService(mockPostRepository);
    }


    @Test
    void should_not_retrieve_a_post_when_no_post() {
        // Given
        // When
        Post.PostSnapshot post = retrieveAPostUseCase.retrieve(null);
        // Then
        assertNull(post);
    }

    @Test
    void should_retrieve_a_post_with_title_and_content() {
        // Given
        Post.PostSnapshot postSnapshot = createPostService.create("title", "content");
        // When
        Post.PostSnapshot retrievedPost = retrieveAPostUseCase.retrieve(postSnapshot.id());
        // Then
        assertNotNull(retrievedPost);
        assertEquals(retrievedPost.id(), postSnapshot.id());
        assertEquals(retrievedPost.title(), postSnapshot.title());
        assertEquals(retrievedPost.content(), postSnapshot.content());
    }

    @Test
    void should_retrieve_a_post_when_multiple_posts() {
        // Given
        Post.PostSnapshot postSnapshot = createPostService.create("title", "content");
        Post.PostSnapshot newPostSnapshot = createPostService.create("new title", "new content");
        // When
        Post.PostSnapshot retrievedPost = retrieveAPostUseCase.retrieve(postSnapshot.id());
        // Then
        assertNotNull(retrievedPost);
        assertEquals(retrievedPost.id(), postSnapshot.id());
        assertEquals(retrievedPost.title(), postSnapshot.title());
        assertEquals(retrievedPost.content(), postSnapshot.content());
    }

    @Test
    void should_not_retrieve_all_posts_when_no_post() {
        // Given
        // When
        var posts = retrieveAPostUseCase.retrieveAll();
        // Then
        assertTrue(posts.isEmpty());
    }

    @Test
    void should_retrieve_all_posts() {
        // Given
        createPostService.create("title", "content");
        createPostService.create("new title", "new content");
        // When
        var posts = retrieveAPostUseCase.retrieveAll();
        // Then
        assertFalse(posts.isEmpty());
        assertEquals(2, posts.size());
    }

}
