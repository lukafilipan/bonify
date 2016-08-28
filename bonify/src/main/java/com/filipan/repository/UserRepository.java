package com.filipan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filipan.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
