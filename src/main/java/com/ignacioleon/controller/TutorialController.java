package com.ignacioleon.controller;


import com.ignacioleon.model.Tutorial;
import com.ignacioleon.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api")
public class TutorialController {
    @Autowired
    private TutorialService tutorialService;

    //GETALLTUTORIAL
    @GetMapping("/tutoriales")
    public ResponseEntity<List<Tutorial>> getAllTutorial(@RequestParam(required = false) String titulo) {
        try {
            List<Tutorial> tutoriales = new ArrayList<>(); //sirve para almacenar los tutoriales obtenidos mediante el repositorio
            if (titulo == null) {
                tutorialService.findAll().forEach(tutoriales::add);
            } else {
                tutoriales = tutorialService.findByTituloIgnoreCase(titulo);
            }

            if (tutoriales.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutoriales, HttpStatus.OK);//status 200
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); //error 500
        }
    }

    //getTutorialById
    @GetMapping("/tutoriales/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") Long id) {
        try {
            Optional<Tutorial> tutorialEncontrado = tutorialService.findById(id);
            if (tutorialEncontrado.isPresent()) {
                return new ResponseEntity<>(tutorialEncontrado.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(NOT_FOUND); //error 404 not found
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //createTutorial
    @PostMapping("/tutoriales")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        try {
            if (tutorial.getTitulo() != null && tutorial.getDescripcion() != null) {
                Tutorial tutorialGuardado = tutorialService.save(tutorial);
                return new ResponseEntity<>(tutorialGuardado, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //updateTutorial
    @PutMapping("/tutoriales/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable Long id, @RequestBody Tutorial tutorial) {

        try {
            Optional<Tutorial> tutorialEncontrado = tutorialService.findById(id);

            if (tutorialEncontrado.isPresent()) {
                Tutorial tutorialModificado = tutorialEncontrado.get();
                tutorialModificado.setTitulo(tutorial.getTitulo());
                tutorialModificado.setDescripcion(tutorial.getDescripcion());
                tutorialModificado.setPublicado(tutorial.isPublicado());

                return new ResponseEntity<>(tutorialService.save(tutorialModificado), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //deleteTutorial
    @DeleteMapping("/tutoriales/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") Long id) {
        try {
            Optional<Tutorial> tutorialEncontrado = tutorialService.findById(id);
            if (tutorialEncontrado.isPresent()) {
                tutorialService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //deleteAllTutorial
    @DeleteMapping("/tutoriales")
    public ResponseEntity<HttpStatus> deleteAllTutorial(){
        try{
            tutorialService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //findByPublicado
    @GetMapping("/tutoriales/publicados")
    public ResponseEntity<List<Tutorial>> findByPublicado(){
        try{
            List<Tutorial> tutorialesPublicados = tutorialService.findByPublicado(true);

            if (tutorialesPublicados.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(tutorialesPublicados, HttpStatus.OK);
            }

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
