package com.ignacioleon.repository;

import com.ignacioleon.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    public List<Tutorial> findByTituloIgnoreCase(String titulo);
    public List<Tutorial> findByPublicado(boolean publicado);
}
