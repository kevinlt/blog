package fr.sawox.blog.application.port.in;

import fr.sawox.blog.application.domain.model.Post;

import java.util.List;
import java.util.UUID;

public interface RetrieveAPostUseCase {
    Post.PostSnapshot retrieve(UUID id);

    List<Post.PostSnapshot> retrieveAll();
}
