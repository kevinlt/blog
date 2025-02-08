package fr.sawox.blog.application.port.in;

import fr.sawox.blog.application.domain.model.Post;

public interface PublishAPostUseCase {

    void publishPost(Post.PostSnapshot postSnapshot);
}
