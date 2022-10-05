package com.ignacioleon.service;

import com.ignacioleon.model.Tutorial;

import java.util.List;
import java.util.Optional;

public interface TutorialService {
    public List<Tutorial> findAll();
    public List<Tutorial> findByTituloIgnoreCase(String titulo);
    public Optional<Tutorial> findById(Long id);
    public Tutorial save(Tutorial tutorial);
    public void deleteById(Long id);
    public void deleteAll();
    public List<Tutorial> findByPublicado(boolean publicado);
}
