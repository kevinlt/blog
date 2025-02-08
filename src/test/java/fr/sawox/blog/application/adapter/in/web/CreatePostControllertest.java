package fr.sawox.blog.application.adapter.in.web;

import fr.sawox.blog.adapter.in.web.CreatePostController;
import fr.sawox.blog.application.port.in.CreateAPostUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CreatePostController.class)
public class CreatePostControllertest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateAPostUseCase createAPostUseCase;

    @Test
    void should_create_a_post() throws Exception {
        // Given
        mockMvc.perform(post("/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"title\", \"content\": \"content\"}"))
                .andExpect(status().isOk());

        then(createAPostUseCase).should().create("title", "content");
    }
}
