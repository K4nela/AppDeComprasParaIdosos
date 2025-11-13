package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.model.Monitora;
import com.k4nela.easypeasy.repository.MonitoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monitora")
public class MonitoraController {

    @Autowired
    private MonitoraRepository monitoraRepository;

    @GetMapping
    public List<Monitora> listar() {
        return monitoraRepository.findAll();
    }

    @PostMapping
    public Monitora criar(@RequestBody Monitora monitora) {
        return monitoraRepository.save(monitora);
    }

    @GetMapping("/{id}")
    public Monitora buscarPorId(@PathVariable String id) {
        return monitoraRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Monitora atualizar(@PathVariable String id, @RequestBody Monitora atualizado) {
        atualizado.setId(id);
        return monitoraRepository.save(atualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        monitoraRepository.deleteById(id);
    }
}
