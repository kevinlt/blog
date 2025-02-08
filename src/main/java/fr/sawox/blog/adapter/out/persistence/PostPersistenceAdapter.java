package fr.sawox.blog.adapter.out.persistence;

import fr.sawox.blog.application.domain.model.Post;
import fr.sawox.blog.application.port.out.PostPort;
import fr.sawox.blog.common.PersistenceAdapter;
import fr.sawox.blog.common.Status;

import java.util.List;
import java.util.UUID;

@PersistenceAdapter
public class PostPersistenceAdapter implements PostPort {

    PostJpaRepository postJPARepository;

    public PostPersistenceAdapter(PostJpaRepository postJPARepository) {
        this.postJPARepository = postJPARepository;
    }

    @Override
    public Post.PostSnapshot addPost(Post post) {
        PostJpaEntity postJpaEntity = PostMapper.toJpaEntity(post);
        PostJpaEntity savedPost = postJPARepository.save(postJpaEntity);
        return PostMapper.toDomain(savedPost).toSnapshot();
    }

    @Override
    public Post.PostSnapshot getPost(UUID id) {
        PostJpaEntity postJpaEntity = postJPARepository.findByUuid(id);
        return PostMapper.toDomain(postJpaEntity).toSnapshot();
    }

    @Override
    public List<Post.PostSnapshot> getPosts() {
        List<PostJpaEntity> postJpaEntities = postJPARepository.findAll();
        return postJpaEntities.stream()
                .map(PostMapper::toDomain)
                .map(Post::toSnapshot)
                .toList();
    }

    @Override
    public Post.PostSnapshot updatePost(Post updatedPost) {
        PostJpaEntity postJpaEntity = PostMapper.toJpaEntity(updatedPost);
        PostJpaEntity savedPost = postJPARepository.save(postJpaEntity);
        return PostMapper.toDomain(savedPost).toSnapshot();
    }

    @Override
    public List<Post.PostSnapshot> getPostsByStatus(Status status) {
        List<PostJpaEntity> postJpaEntities = postJPARepository.findByStatus(status.name());
        return postJpaEntities.stream()
                .map(PostMapper::toDomain)
                .map(Post::toSnapshot)
                .toList();
    }
}
