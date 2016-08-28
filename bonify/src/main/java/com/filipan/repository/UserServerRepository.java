package com.filipan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filipan.model.UserServer;

public interface UserServerRepository extends JpaRepository<UserServer, Long> {

}
