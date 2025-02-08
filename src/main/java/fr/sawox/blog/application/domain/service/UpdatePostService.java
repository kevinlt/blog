package fr.sawox.blog.application.domain.service;

import fr.sawox.blog.application.domain.model.Post;
import fr.sawox.blog.application.port.in.UpdateAPostUseCase;
import fr.sawox.blog.application.port.out.PostPort;

import java.time.Instant;

public class UpdatePostService implements UpdateAPostUseCase {

    private final PostPort postPort;

    public UpdatePostService(PostPort postPort) {
        this.postPort = postPort;
    }

    @Override
    public void update(Post.PostSnapshot post) {
        Post updatedPost = Post.fromSnapshot(post);
        updatedPost.setUpdatedDate(Instant.now());
        postPort.updatePost(updatedPost);
    }
}
