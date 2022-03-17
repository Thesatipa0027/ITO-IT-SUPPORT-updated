package com.itsupport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsupport.entity.Priority;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {

}
