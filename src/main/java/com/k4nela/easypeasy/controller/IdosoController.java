package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.entity.Idoso;
import com.k4nela.easypeasy.repository.IdosoRepository;
import com.k4nela.easypeasy.service.IdosoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/idosos")
public class IdosoController {

    @Autowired
    private IdosoRepository idosoRepository;
    @Autowired
    private IdosoService idosoService;

    @GetMapping
    public List<Idoso> listar() {
        return IdosoService.listar();
    }

    @PostMapping
    public Idoso criar(@RequestBody Idoso idoso) {
        return idosoService.criar(idoso);
    }

    @GetMapping("/{id}")
    public Idoso buscarPorId(@PathVariable String id) {
        return idosoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Idoso atualizar(@PathVariable String id, @RequestBody Idoso atualizado) {
        return idosoService.atualizar(id, atualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        idosoService.deletar(id);
    }


}
