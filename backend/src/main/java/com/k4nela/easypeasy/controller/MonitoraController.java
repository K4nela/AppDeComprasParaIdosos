package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.entity.Monitora;
import com.k4nela.easypeasy.service.MonitoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monitora")
public class MonitoraController {

    @Autowired
    private MonitoraService monitoraService;

    @GetMapping
    public List<Monitora> listar() {
        return monitoraService.listarMonitora();
    }

    @PostMapping
    public Monitora criar(@RequestBody Monitora monitora) {
        return monitoraService.criarMonitora(monitora.getIdoso(), monitora.getFamiliar());
    }

    @GetMapping("/{id}")
    public Monitora buscarPorId(@PathVariable String id) {
        return monitoraService.listarMonitoraPorId(id);
    }

//    @PutMapping("/{id}")
//    public Monitora atualizar(@PathVariable String id, @RequestBody Monitora atualizado) {
//        atualizado.setId(Integer.parseInt(id));
//        return monitoraRepository.save(atualizado);
//    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        monitoraService.deletarMonitora(id);
    }
}
