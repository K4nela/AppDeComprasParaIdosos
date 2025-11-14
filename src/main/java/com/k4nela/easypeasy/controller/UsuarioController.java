package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.entity.Usuario;
import com.k4nela.easypeasy.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Cadastra novo usuário (com verificação de e-mail duplicado)
    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.criarUsuario(usuario);
    }

    // Lista todos os usuários
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    // Busca usuário por ID
    @GetMapping("/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable String id) {
        return usuarioService.buscarPorId(id);
    }

    //  Atualiza usuário existente
    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable String id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioService.atualizarUsuario(id, usuarioAtualizado);
    }

    //  Excluir usuário
    @DeleteMapping("/{id}")
    public String deletarUsuario(@PathVariable String id) {
        usuarioService.deletarUsuario(id);
        return "Usuário removido com sucesso!";
    }
}
