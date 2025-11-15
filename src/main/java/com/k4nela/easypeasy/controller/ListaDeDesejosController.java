package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.entity.ListaDeDesejos;
import com.k4nela.easypeasy.service.ListaDeDesejosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/listas")
public class ListaDeDesejosController {

    @Autowired
    private ListaDeDesejosService listaService;

    @GetMapping
    public List<ListaDeDesejos> listar() {
        return listaService.listar();
    }

    @PostMapping
    public ListaDeDesejos criar(@RequestBody ListaDeDesejos lista) {
        return listaService.criar(lista);
    }

    @GetMapping("/{id}")
    public ListaDeDesejos buscarPorId(@PathVariable String id) {
        return listaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ListaDeDesejos atualizar(@PathVariable String id, @RequestBody ListaDeDesejos lista) {
        return listaService.atualizar(id, lista);
    }

    @PatchMapping("/{id}")
    public ListaDeDesejos atualizarParcial(@PathVariable int id, @RequestBody Map<String, Object> campos) {
        return listaService.atualizarParcial(id, campos);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        listaService.deletar(id);
    }
}

