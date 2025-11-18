package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.entity.Item;
import com.k4nela.easypeasy.entity.ListaDeDesejos;
import com.k4nela.easypeasy.repository.ListaDeDesejosRepository;
import com.k4nela.easypeasy.service.FamiliarService;
import com.k4nela.easypeasy.service.ListaDeDesejosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/listas")
public class ListaDeDesejosController {

    @Autowired
    private FamiliarService familiarService;

    @Autowired
    private ListaDeDesejosService listaService;

    @Autowired
    private ListaDeDesejosRepository listaDeDesejosRepository;

    @Autowired
    private AdministradorController listaDeDesejosServices;

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

    @PostMapping("/{idLista}/itens")
    public Item criarItemNaLista(@PathVariable String idLista, @RequestBody Item item){
        return listaService.criarItemNaLista(idLista, item);
    }

    @GetMapping("/{idosoId}/listas/{listaId}/itens")
    public List<Item> listarItens(@PathVariable String listaId){
        return listaService.listarItens(listaId);
    }

    // Listar todas as listas de um idoso específico
    @GetMapping("/{familiarId}/idosos/{idosoId}/listas")
    public ResponseEntity<List<ListaDeDesejos>> listarListasDoIdoso(
            @PathVariable String familiarId,
            @PathVariable String idosoId) {

        List<ListaDeDesejos> listas = familiarService.listarListasDeUmIdoso(familiarId, idosoId);
        return ResponseEntity.ok(listas);
    }
}

