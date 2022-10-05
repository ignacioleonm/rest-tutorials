package com.ignacioleon.service;

import com.ignacioleon.model.Tutorial;
import com.ignacioleon.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TutorialServiceImp implements TutorialService {

    @Autowired //repository dependency injection
    private TutorialRepository tutorialRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Tutorial> findAll() {
        return tutorialRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tutorial> findByTituloIgnoreCase(String titulo) {
        return tutorialRepository.findByTituloIgnoreCase(titulo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tutorial> findById(Long id) {
        return tutorialRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Tutorial save(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        tutorialRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteAll() {
        tutorialRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tutorial> findByPublicado(boolean publicado) {
        return tutorialRepository.findByPublicado(publicado);
    }
}
