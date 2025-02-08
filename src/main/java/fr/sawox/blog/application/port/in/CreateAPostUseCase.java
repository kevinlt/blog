package fr.sawox.blog.application.port.in;


import fr.sawox.blog.application.domain.model.Post;

public interface CreateAPostUseCase {

    Post.PostSnapshot create(String title, String content);
}
