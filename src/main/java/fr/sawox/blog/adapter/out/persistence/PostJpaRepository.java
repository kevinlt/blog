package fr.sawox.blog.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PostJpaRepository extends JpaRepository<PostJpaEntity, Long> {

    @Query("SELECT p FROM post p WHERE p.uuid = :uuid")
    PostJpaEntity findByUuid(UUID uuid);

}
