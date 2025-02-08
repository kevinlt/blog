package fr.sawox.blog.application.adapter.out.persistence;

import fr.sawox.blog.adapter.out.persistence.PostJpaRepository;
import fr.sawox.blog.adapter.out.persistence.PostPersistenceAdapter;
import fr.sawox.blog.application.port.out.PostPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IntegrationTestConfiguration {

    @Bean
    public PostPort postPort(PostJpaRepository postJpaRepository) {
        return new PostPersistenceAdapter(postJpaRepository);
    }
}