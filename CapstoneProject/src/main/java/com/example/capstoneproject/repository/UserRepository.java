package com.example.capstoneproject.repository;

import com.example.capstoneproject.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.name = ?1")
    User findByName(String name);

    @Query("SELECT u FROM User u WHERE u.userType = ?1")
    List<User> findAllByUserType(String userType);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);
}
