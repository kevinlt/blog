package fr.sawox.blog.adapter.in.web;

import fr.sawox.blog.application.domain.model.Post;
import fr.sawox.blog.application.port.in.CreateAPostUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CreatePostController {

    private final CreateAPostUseCase createAPostUseCase;

    @PostMapping("/post")
    void createPost(@RequestBody Post.PostSnapshot postSnapshot) {
        createAPostUseCase.create(postSnapshot.title(), postSnapshot.content());
    }
}
