package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.entity.Historico;
import com.k4nela.easypeasy.service.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping
    public List<Historico> listar() {
        return historicoService.listar();
    }

    @PostMapping
    public Historico criar(@RequestBody Historico historico) {
        return historicoService.criar(historico);
    }

    @GetMapping("/{id}")
    public Historico buscarPorId(@PathVariable String id) {
        return historicoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Historico atualizar(@PathVariable String id, @RequestBody Historico atualizado) {
        return historicoService.atualizar(id, atualizado);
    }

    @PatchMapping("/{id}")
    public Historico atualizarParcial(@PathVariable int id, @RequestBody Map<String, Object> campos) {
        return historicoService.atualizarParcial(id, campos);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        historicoService.deletar(id);
    }
}
