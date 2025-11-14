package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.entity.Item;
import com.k4nela.easypeasy.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public List<Item> listar() {
        return itemRepository.findAll();
    }

    @PostMapping
    public Item criar(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @GetMapping("/{id}")
    public Item buscarPorId(@PathVariable String id) {
        return itemRepository.findById(id).orElse(null);
    }

//    @PutMapping("/{id}")
//    public Item atualizar(@PathVariable String id, @RequestBody Item itemAtualizado) {
//        return itemRepository.save(itemAtualizado);
//    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        itemRepository.deleteById(id);
    }
}
