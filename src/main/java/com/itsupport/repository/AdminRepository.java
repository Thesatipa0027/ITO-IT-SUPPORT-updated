package com.itsupport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsupport.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
