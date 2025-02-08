package fr.sawox.blog.adapter.out.persistence;

import fr.sawox.blog.application.domain.model.Post;
import fr.sawox.blog.common.Status;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class PostMapper {

    static Post toDomain(PostJpaEntity postJpaEntity) {
        return new Post(
                postJpaEntity.getUuid(),
                postJpaEntity.getTitle(),
                postJpaEntity.getContent(),
                postJpaEntity.getCreationDate().toInstant(ZoneOffset.UTC),
                postJpaEntity.getUpdatedDate() != null ? postJpaEntity.getUpdatedDate().toInstant(ZoneOffset.UTC) : null,
                postJpaEntity.getPublicationDate() != null ? postJpaEntity.getPublicationDate().toInstant(ZoneOffset.UTC) : null,
                Status.valueOf(postJpaEntity.getStatus())
                );
    }

    static PostJpaEntity toJpaEntity(Post post) {
        return new PostJpaEntity(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getStatus().name(),
                LocalDateTime.ofInstant(post.getCreationDate(), ZoneOffset.UTC),
                post.getUpdatedDate() != null ? LocalDateTime.ofInstant(post.getUpdatedDate(), ZoneOffset.UTC) : null,
                post.getPublicationDate() != null ? LocalDateTime.ofInstant(post.getPublicationDate(), ZoneOffset.UTC) : null
        );
    }
}
