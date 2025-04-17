package com.example.board.repository;

import com.example.board.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {
    private final List<Post> posts = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // 게시글 저장
    public Post save(Post post) {
        post = new Post(idGenerator.getAndIncrement(), post.getTitle(), post.getContent(), post.getAuthor());
        posts.add(post);
        return post;
    }

    // 모든 게시글 조회
    public List<Post> findAll() {
        return posts;
    }

    // 특정 게시글 조회
    public Optional<Post> findById(Long id) {
        return posts.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    // 게시글 삭제
    public boolean deleteById(Long id) {
        return posts.removeIf(p -> p.getId().equals(id));
    }
}
