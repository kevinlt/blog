package fr.sawox.blog.application.domain.service;

import fr.sawox.blog.application.domain.model.Post;
import fr.sawox.blog.application.port.in.RetrieveAPostUseCase;
import fr.sawox.blog.application.port.out.PostPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RetrievePostService implements RetrieveAPostUseCase {

    private final PostPort postPort;

    public RetrievePostService(PostPort postPort) {
        this.postPort = postPort;
    }

    @Override
    public Post.PostSnapshot retrieve(UUID id) {
        return postPort.getPost(id);
    }

    @Override
    public List<Post.PostSnapshot> retrieveAll() {
        return postPort.getPosts();
    }
}
