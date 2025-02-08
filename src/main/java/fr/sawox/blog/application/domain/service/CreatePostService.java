package fr.sawox.blog.application.domain.service;

import fr.sawox.blog.application.domain.model.Post;
import fr.sawox.blog.application.port.in.CreateAPostUseCase;
import fr.sawox.blog.application.port.out.PostPort;
import fr.sawox.blog.common.Status;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CreatePostService implements CreateAPostUseCase {

    private final PostPort postPort;

    public CreatePostService(PostPort postPort) {
        this.postPort = postPort;
    }

    @Override
    public Post.PostSnapshot create(String title, String content) {
        Post post = new Post(title, content);
        post.setStatus(Status.DRAFT);
        post.setCreationDate(Instant.now());
        return postPort.addPost(post);
    }
}
