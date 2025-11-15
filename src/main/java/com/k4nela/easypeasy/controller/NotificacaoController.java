package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.entity.Notificacao;
import com.k4nela.easypeasy.service.NotificacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacao")
public class NotificacaoController {

    private final NotificacaoService service;

    public NotificacaoController(NotificacaoService service) {
        this.service = service;
    }

    @PostMapping
    public Notificacao enviar(@RequestBody Notificacao req) {
        return service.enviar(
                req.getTitulo(),
                req.getMensagem()
        );
    }

    @GetMapping
    public List<Notificacao> listar() {
        return service.listar();
    }

    @PatchMapping("/{id}/lida")
    public Notificacao marcarComoLida(@PathVariable String id) {
        return service.marcarLida(id);
    }
}
