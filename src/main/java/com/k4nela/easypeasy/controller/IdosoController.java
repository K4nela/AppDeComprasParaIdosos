package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.model.Idoso;
import com.k4nela.easypeasy.repository.IdosoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/idosos")
public class IdosoController {

    @Autowired
    private IdosoRepository idosoRepository;

    @GetMapping
    public List<Idoso> listar() {
        return idosoRepository.findAll();
    }

    @PostMapping
    public Idoso criar(@RequestBody Idoso idoso) {
        return idosoRepository.save(idoso);
    }

    @GetMapping("/{id}")
    public Idoso buscarPorId(@PathVariable String id) {
        return idosoRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Idoso atualizar(@PathVariable String id, @RequestBody Idoso atualizado) {
        atualizado.setId(id);
        return idosoRepository.save(atualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        idosoRepository.deleteById(id);
    }
}
