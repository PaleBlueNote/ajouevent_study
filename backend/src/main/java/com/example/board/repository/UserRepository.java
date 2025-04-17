package com.example.board.repository;

import com.example.board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

//public interface UserRepository extends JpaRepository<User, Long> {
public interface UserRepository{
    void save(User user);
    Optional<User> findByUserLoginId(String loginId);
    Optional<User> findById(Long id);
    void deleteById(Long id);
    boolean existsByUserLoginId(String loginId);
}
