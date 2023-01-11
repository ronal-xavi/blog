package com.ronal.blog.repository;

import com.ronal.blog.dao.PublicationDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<PublicationDAO,Long> {
}
