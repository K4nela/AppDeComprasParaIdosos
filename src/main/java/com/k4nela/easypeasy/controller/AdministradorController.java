package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.entity.Administrador;
import com.k4nela.easypeasy.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @GetMapping
    public List<Administrador> listar() {
        return administradorRepository.findAll();
    }

    @PostMapping
    public Administrador criar(@RequestBody Administrador adm) {
        return administradorRepository.save(adm);
    }

    @GetMapping("/{id}")
    public Administrador buscarPorId(@PathVariable String id) {
        return administradorRepository.findById(id).orElse(null);
    }

//    @PutMapping("/{id}")
//    public Administrador atualizar(@PathVariable String id, @RequestBody Administrador admAtualizado) {
//        admAtualizado.setId(id);
//        return administradorRepository.save(admAtualizado);
//    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        administradorRepository.deleteById(id);
    }
}
