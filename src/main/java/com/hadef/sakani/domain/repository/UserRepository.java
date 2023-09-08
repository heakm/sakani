package com.hadef.sakani.domain.repository;

import com.hadef.sakani.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);

    User findByEmail(String email);
}
