package com.example.board.repository;

import com.example.board.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryUserRepository implements UserRepository {

    private final Map<Long, User> store = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(1L);

    @Override
    public void save(User user) {
        long id = sequence.getAndIncrement();
        user.setId(id);  // setter 있어야 함
        store.put(id, user);
    }

    @Override
    public Optional<User> findByUserLoginId(String loginId) {
        return store.values().stream()
                .filter(u -> u.getUserLoginId().equals(loginId))
                .findFirst();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public boolean existsByUserLoginId(String loginId) {
        return store.values().stream()
                .anyMatch(u -> u.getUserLoginId().equals(loginId));
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
