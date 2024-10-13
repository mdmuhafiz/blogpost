package com.blogapp.repository;

import com.blogapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
   boolean existsByEmail(String email);
   //boolean existByUsername(String username);
    boolean existsByUsername(String username);
    User findByUsername(String username);
}
