package fr.sawox.blog.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "post")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class PostJpaEntity {

    @Id
    private UUID uuid;
    private String title;
    private String content;
    private String status;
    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;
}
