package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.entity.Idoso;
import com.k4nela.easypeasy.entity.Item;
import com.k4nela.easypeasy.entity.ListaDeDesejos;
import com.k4nela.easypeasy.repository.IdosoRepository;
import com.k4nela.easypeasy.service.IdosoService;
import com.k4nela.easypeasy.service.ListaDeDesejosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/idosos")
public class IdosoController {

    @Autowired
    private ListaDeDesejosService listaService;

    @Autowired
    private IdosoService idosoService;

    @GetMapping
    public List<Idoso> listar() {
        return idosoService.listar();
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

    @PostMapping("/{idosoId}/listas")
    public ListaDeDesejos criarLista(@PathVariable String idosoId, @RequestBody ListaDeDesejos novaLista) {
        Idoso idoso = idosoService.buscarPorId(idosoId);
        return idosoService.criarListaDeDesejos(idoso, novaLista);
    }

    @GetMapping("/{idosoId}/listas")
    public List<ListaDeDesejos> listarListas(@PathVariable String idosoId){
        return idosoService.listarListaDeDesejosById(idosoId);
    }

    @GetMapping("/{idosoId}/listas/{listaId}")
    public ResponseEntity<ListaDeDesejos> buscarListaEspecifica(@PathVariable String idosoId,@PathVariable int listaId) {

        ListaDeDesejos lista = idosoService.buscarListaDoIdosoPorId(idosoId, listaId);
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/{idosoId}/listas/{listaId}/itens")
    public ResponseEntity<Item> adicionarItemNaLista(@PathVariable String listaId, @RequestBody Item novoItem) {
        Item itemSalvo = idosoService.adicionarItemNaLista(listaId, novoItem);
        return ResponseEntity.status(201).body(itemSalvo);
    }

    @GetMapping("/{idosoId}/listas/{listaId}/itens")
    public ResponseEntity<List<Item>> listarItensDaListaDoIdoso(@PathVariable String idosoId, @PathVariable int listaId) {

        List<Item> itens = idosoService.listarItensDaListaDoIdoso(idosoId, listaId);
        return ResponseEntity.ok(itens);
    }
}
