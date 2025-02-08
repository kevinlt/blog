package fr.sawox.blog.application.domain.service;

import fr.sawox.blog.application.domain.model.Post;
import fr.sawox.blog.application.port.in.PublishAPostUseCase;
import fr.sawox.blog.application.port.out.PostPort;

public class PublishPostService implements PublishAPostUseCase {

    private final PostPort postPort;

    public PublishPostService(PostPort postPort) {
        this.postPort = postPort;
    }

    @Override
    public void publishPost(Post.PostSnapshot postSnapshot) {
        Post post = Post.fromSnapshot(postSnapshot);
        post.publish();
        postPort.updatePost(post);
    }
}
