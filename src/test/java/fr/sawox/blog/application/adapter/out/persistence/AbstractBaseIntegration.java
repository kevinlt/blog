package fr.sawox.blog.application.adapter.out.persistence;

import docker.TestContainers;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@EnableAutoConfiguration
@EntityScan("fr.sawox.blog.adapter.out.persistence")
@EnableJpaRepositories("fr.sawox.blog.adapter.out.persistence")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
public class AbstractBaseIntegration extends TestContainers {
}