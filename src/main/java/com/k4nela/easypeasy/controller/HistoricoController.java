package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.model.Historico;
import com.k4nela.easypeasy.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoRepository historicoRepository;

    @GetMapping
    public List<Historico> listar() {
        return historicoRepository.findAll();
    }

    @PostMapping
    public Historico criar(@RequestBody Historico historico) {
        return historicoRepository.save(historico);
    }

    @GetMapping("/{id}")
    public Historico buscarPorId(@PathVariable String id) {
        return historicoRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Historico atualizar(@PathVariable String id, @RequestBody Historico atualizado) {
        atualizado.setId(id);
        return historicoRepository.save(atualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        historicoRepository.deleteById(id);
    }
}
