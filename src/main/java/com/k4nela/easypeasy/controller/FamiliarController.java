package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.entity.Familiar;
import com.k4nela.easypeasy.entity.Historico;
import com.k4nela.easypeasy.entity.Idoso;
import com.k4nela.easypeasy.repository.FamiliarRepository;
import com.k4nela.easypeasy.service.FamiliarService;
import com.k4nela.easypeasy.service.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/familiares")
public class FamiliarController {

    @Autowired
    private FamiliarRepository familiarRepository;
    @Autowired
    private FamiliarService familiarService;
    @Autowired
    private HistoricoService historicoService;

    @GetMapping
    public List<Familiar> listar() {
        return familiarService.listar();
    }

    @PostMapping
    public Familiar criar(@RequestBody Familiar familiar) {
        return familiarService.criar(familiar);
    }

    @GetMapping("/{id}")
    public Familiar buscarPorId(@PathVariable String id) {
        return familiarService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Familiar atualizar(@PathVariable String id, @RequestBody Familiar atualizado) {
        return familiarService.atualizar(id, atualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        familiarService.deletar(id);
    }

    @GetMapping("/{id}/idosos")
    public List<Idoso> listarIdosos(@PathVariable String id) {
        return familiarService.listarIdososPorFamiliar(id);
    }

    @PostMapping("/historicos/{id}/status")
    public Historico atualizarStatusHistorico(@PathVariable int id, @RequestBody Map<String, String> body) {
        String novoStatus = body.get("status");
        String familiarId = body.get("familiarId"); // pega do body
        Familiar familiarLogado = familiarService.buscarPorId(familiarId);

        return historicoService.atualizarStatusHistorico(id, novoStatus, familiarLogado);
    }
}
