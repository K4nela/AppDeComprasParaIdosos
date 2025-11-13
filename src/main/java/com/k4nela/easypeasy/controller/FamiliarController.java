package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.model.Familiar;
import com.k4nela.easypeasy.repository.FamiliarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/familiares")
public class FamiliarController {

    @Autowired
    private FamiliarRepository familiarRepository;

    @GetMapping
    public List<Familiar> listar() {
        return familiarRepository.findAll();
    }

    @PostMapping
    public Familiar criar(@RequestBody Familiar familiar) {
        return familiarRepository.save(familiar);
    }

    @GetMapping("/{id}")
    public Familiar buscarPorId(@PathVariable String id) {
        return familiarRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Familiar atualizar(@PathVariable String id, @RequestBody Familiar atualizado) {
        atualizado.setId(id);
        return familiarRepository.save(atualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        familiarRepository.deleteById(id);
    }
}
