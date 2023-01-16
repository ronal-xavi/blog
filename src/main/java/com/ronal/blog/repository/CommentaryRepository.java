package com.ronal.blog.repository;

import com.ronal.blog.dao.CommentaryDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaryRepository extends JpaRepository<CommentaryDAO, Integer> {
}
