package fr.sawox.blog.adapter.in.web;

import fr.sawox.blog.application.domain.model.Post;
import fr.sawox.blog.application.port.in.RetrieveAPostUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class RetrievePostController {

    private final RetrieveAPostUseCase retrievePostUseCase;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        List<Post.PostSnapshot> posts = retrievePostUseCase.retrieveAll();
        model.addAttribute("posts", posts);
        return "fragments :: posts-list";
    }
}
