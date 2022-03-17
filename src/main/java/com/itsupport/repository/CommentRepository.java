package com.itsupport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsupport.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
