package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.entity.*;
import com.k4nela.easypeasy.repository.FamiliarRepository;
import com.k4nela.easypeasy.service.FamiliarService;
import com.k4nela.easypeasy.service.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{idosoId}/idosos")
    public List<Idoso> listarIdosos(@PathVariable String idosoId) {
        return familiarService.listarIdososPorFamiliar(idosoId);
    }

    @GetMapping("/{familiarId}/idosos/listas")
    public ResponseEntity<List<ListaDeDesejos>> listarTodasAsListas(@PathVariable String familiarId) {
        return ResponseEntity.ok(familiarService.listarTodasAsListasPorFamiliar(familiarId));
    }

    @GetMapping("/{familiarId}/idosos/{idosoId}/listas")
    public ResponseEntity<List<ListaDeDesejos>> listarListasDoIdoso(@PathVariable String familiarId, @PathVariable String idosoId) {
        List<ListaDeDesejos> listas = familiarService.listarListasDeUmIdoso(familiarId, idosoId);
        return ResponseEntity.ok(listas);
    }

    @GetMapping("/{familiarId}/idosos/{idosoId}/listas/{listaId}")
    public ResponseEntity<ListaDeDesejos> verListaEspecifica(@PathVariable String familiarId, @PathVariable String idosoId, @PathVariable int listaId) {
        ListaDeDesejos lista = familiarService.verListaDoIdoso(familiarId, idosoId, listaId);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{familiarId}/idosos/{idosoId}/listas/{listaId}/itens")
    public ResponseEntity<List<Item>> verItensDaLista(@PathVariable String familiarId, @PathVariable String idosoId, @PathVariable int listaId) {
        List<Item> itens = familiarService.verItens(familiarId, idosoId, listaId);
        return ResponseEntity.ok(itens);
    }

    @PatchMapping("/{familiarId}/itens/{itemId}/historico")
    public ResponseEntity<Item> mudarStatusItem(@PathVariable String familiarId, @PathVariable int itemId, @RequestBody Map<String, String> body) {

        String novoStatus = body.get("status");
        if (novoStatus == null || novoStatus.trim().isEmpty()) {
            throw new RuntimeException("O campo 'status' é obrigatório");
        }

        Item itemAtualizado = familiarService.mudarStatusItem(familiarId, itemId, novoStatus);
        return ResponseEntity.ok(itemAtualizado);
    }
}
