package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.model.ListaDeDesejos;
import com.k4nela.easypeasy.repository.ListaDeDesejosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listas")
public class ListaDeDesejosController {

    @Autowired
    private ListaDeDesejosRepository listaRepository;

    @GetMapping
    public List<ListaDeDesejos> listar() {
        return listaRepository.findAll();
    }

    @PostMapping
    public ListaDeDesejos criar(@RequestBody ListaDeDesejos lista) {
        return listaRepository.save(lista);
    }

    @GetMapping("/{id}")
    public ListaDeDesejos buscarPorId(@PathVariable String id) {
        return listaRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public ListaDeDesejos atualizar(@PathVariable String id, @RequestBody ListaDeDesejos listaAtualizada) {
        listaAtualizada.setId(id);
        return listaRepository.save(listaAtualizada);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        listaRepository.deleteById(id);
    }
}
