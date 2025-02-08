package fr.sawox.blog.application.adapter.out;

import fr.sawox.blog.application.port.out.PostPort;

import fr.sawox.blog.application.domain.model.Post;

import java.util.*;

public class MockPostPort implements PostPort {

    private final Map<UUID, Post> posts = new HashMap<>();

    @Override
    public Post.PostSnapshot addPost(Post post) {
        posts.put(post.getId(), post);
        return post.toSnapshot();
    }

    @Override
    public Post.PostSnapshot getPost(UUID id) {
        Post post = posts.get(id);
        if (post == null) {
            return null;
        }
        return post.toSnapshot();
    }

    @Override
    public List<Post.PostSnapshot> getPosts() {
        return posts.values().stream()
                .map(Post::toSnapshot)
                .toList();
    }

    @Override
    public Post.PostSnapshot updatePost(Post updatedPost) {
        return posts.put(updatedPost.getId(), updatedPost).toSnapshot();
    }
}
