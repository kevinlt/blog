package fr.sawox.blog.application.port.out;

import fr.sawox.blog.application.domain.model.Post;
import fr.sawox.blog.application.domain.model.Post.PostSnapshot;
import fr.sawox.blog.common.Status;

import java.util.List;
import java.util.UUID;

public interface PostPort {

    PostSnapshot addPost(Post post);

    PostSnapshot getPost(UUID id);

    List<PostSnapshot> getPosts();

    PostSnapshot updatePost(Post updatedPost);

    List<PostSnapshot> getPostsByStatus(Status status);
}
