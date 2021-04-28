package org.unillanos.showcase.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.unillanos.showcase.application.repository.UserRepository;
import org.unillanos.showcase.domain.model.User;
import org.unillanos.showcase.infrastructure.persistence.jpa.repository.SpringDataUserEntityJpaRepository;
import org.unillanos.showcase.infrastructure.persistence.mapper.UserEntityMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class SpringDataUserRepository implements UserRepository {

    @Autowired
    private final SpringDataUserEntityJpaRepository userRepository;

    @Autowired
    private final UserEntityMapper userMapper;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id)
        .map(uEntity -> Optional.of(userMapper.toDomain(uEntity)))
        .orElseGet(Optional::empty);

    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return userMapper.toDomain(userRepository.save(userMapper.toEntity(user)));
    }

    @Override
    public Set<User> findAll() {
        return userRepository.findAll()
        .stream()
        .map(userMapper::toDomain)
        .collect(Collectors.toSet());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
        .map(uEntity -> Optional.of(userMapper.toDomain(uEntity)))
        .orElseGet(Optional::empty);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
        .map(uEntity -> Optional.of(userMapper.toDomain(uEntity)))
        .orElseGet(Optional::empty);
    }
    
}
