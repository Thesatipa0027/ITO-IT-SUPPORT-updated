package com.itsupport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsupport.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
