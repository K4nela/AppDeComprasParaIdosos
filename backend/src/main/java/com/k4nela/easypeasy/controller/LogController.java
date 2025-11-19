package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.entity.Log;
import com.k4nela.easypeasy.service.LogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {

    private final LogService service;

    public LogController(LogService service) {
        this.service = service;
    }

    @PostMapping
    public Log criarLog(@RequestBody Log req) {
        return service.registrar(
                req.getMensagem(),
                req.getNivel(),
                req.getOrigem(),
                req.getDetalhe()
        );
    }

    @GetMapping
    public List<Log> listarLogs() {
        return service.listarTodos();
    }
}
