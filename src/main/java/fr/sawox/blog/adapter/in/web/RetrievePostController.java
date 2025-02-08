package fr.sawox.blog.adapter.in.web;

import fr.sawox.blog.application.domain.model.Post;
import fr.sawox.blog.application.port.in.RetrieveAPostUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RetrievePostController {

    private final RetrieveAPostUseCase retrievePostUseCase;

    @GetMapping("/posts")
    public List<Post.PostSnapshot> getPosts() {
        return retrievePostUseCase.retrieveAll();
    }
}
