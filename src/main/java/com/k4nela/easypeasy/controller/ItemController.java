package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.entity.Item;
import com.k4nela.easypeasy.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> listar() {
        return itemService.listar();
    }

    @PostMapping
    public Item criar(@RequestBody Item item) {
        return itemService.criar(item);
    }

    @GetMapping("/{id}")
    public Item buscarPorId(@PathVariable String id) {
        return itemService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Item atualizar(@PathVariable String id, @RequestBody Item itemAtualizado) {
        return itemService.atualizar(id, itemAtualizado);
    }

    @PatchMapping("/{id}")
    public Item atualizarParcial(@PathVariable int id, @RequestBody Map<String, Object> campos) {
        return itemService.atualizarParcial(id, campos);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        itemService.deletar(id);
    }
}

