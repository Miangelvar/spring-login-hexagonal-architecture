package org.unillanos.showcase.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.unillanos.showcase.application.repository.UserRepository;
import org.unillanos.showcase.domain.model.User;

public class MongoDbUserRepository implements UserRepository{

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean existsByUsername(String username) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public User save(User user) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
