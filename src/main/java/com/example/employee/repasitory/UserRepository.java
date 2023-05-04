package com.example.employee.repasitory;

import com.example.employee.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<Object> findByEmail(String username);
}
