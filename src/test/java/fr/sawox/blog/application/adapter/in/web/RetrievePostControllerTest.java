package fr.sawox.blog.application.adapter.in.web;

import fr.sawox.blog.adapter.in.web.RetrievePostController;
import fr.sawox.blog.application.port.in.RetrieveAPostUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RetrievePostController.class)
public class RetrievePostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private RetrieveAPostUseCase retrievePostUseCase;

    @Test
    void should_retrieve_all_posts() throws Exception {
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk());

        then(retrievePostUseCase).should().retrieveAll();
    }
}
