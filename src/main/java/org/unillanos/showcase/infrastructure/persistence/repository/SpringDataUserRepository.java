package org.unillanos.showcase.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.unillanos.showcase.domain.User;
import org.unillanos.showcase.domain.UserRepository;
import org.unillanos.showcase.infrastructure.persistence.jpa.SpringDataUserJpaRepository;
import org.unillanos.showcase.infrastructure.persistence.jpa.UserEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class SpringDataUserRepository implements UserRepository {

    @Autowired
    private final SpringDataUserJpaRepository userRepository;
    @Autowired
    private final ModelMapper mapper;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id)
        .map(userEntity -> Optional.of(mapper.map(userEntity, User.class)))
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
        UserEntity savedUser = userRepository.save(mapper.map(user, UserEntity.class));
        return mapper.map(savedUser, User.class);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll()
        .stream()
        .map(userEntity -> mapper.map(userEntity, User.class))
        .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
        .map(userEntity -> Optional.of(mapper.map(userEntity, User.class)))
        .orElseGet(Optional::empty);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
        .map(userEntity -> Optional.of(mapper.map(userEntity, User.class)))
        .orElseGet(Optional::empty);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        
    }
    
}
