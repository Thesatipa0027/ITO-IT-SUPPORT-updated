package com.itsupport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsupport.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {

}
