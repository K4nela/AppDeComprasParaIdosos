package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.entity.Administrador;
import com.k4nela.easypeasy.entity.Idoso;
import com.k4nela.easypeasy.entity.Item;
import com.k4nela.easypeasy.entity.ListaDeDesejos;
import com.k4nela.easypeasy.repository.ListaDeDesejosRepository;
import com.k4nela.easypeasy.service.AdministradorService;
import com.k4nela.easypeasy.service.IdosoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;
    @Autowired
    private IdosoService idosoService;
    @Autowired
    private ListaDeDesejosRepository listaDeDesejosRepository;

    @GetMapping
    public List<Administrador> listar() {
        return administradorService.listar();
    }

    @PostMapping
    public Administrador criar(@RequestBody Administrador adm) {
        return administradorService.criar(adm);
    }

    @GetMapping("/{id}")
    public Administrador buscarPorId(@PathVariable String id) {
        return administradorService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Administrador atualizar(@PathVariable String id, @RequestBody Administrador admAtualizado) {
        return administradorService.atualizar(id, admAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        administradorService.deletar(id);
    }

    @PostMapping("/{id}/listas")
    public ListaDeDesejos criarListaDeDesejos(@PathVariable String id, @RequestBody ListaDeDesejos lista, @RequestBody List<Item> itens) {
        // busca o idoso
        Idoso idoso = idosoService.buscarPorId(id);

        // chama o service que cria a lista e salva os itens
        return idosoService.criarListaDeDesejos(idoso, lista);
    }

    @PostMapping("/listas/{listaId}/itens")
    public Item criarItem(@PathVariable int listaId, @RequestBody Item item) {
        ListaDeDesejos lista = listaDeDesejosRepository.findById(String.valueOf(listaId))
                .orElseThrow(() -> new RuntimeException("Lista não encontrada!"));
        return idosoService.criarItem(lista, item);
    }

}
