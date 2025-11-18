package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.entity.*;
import com.k4nela.easypeasy.repository.ListaDeDesejosRepository;
import com.k4nela.easypeasy.service.AdministradorService;
import com.k4nela.easypeasy.service.IdosoService;
import com.k4nela.easypeasy.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AdministradorService administradorService;

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

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarTodosOsUsuarios(){
        return ResponseEntity.ok(administradorService.listarTodosUsuarios());
    }

    @GetMapping("/usuarios/{usuarioId}")
    public Usuario listarUsuarioPorId(@PathVariable String usuarioId){
        return administradorService.buscarUsuarioPorId(usuarioId);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable String id,@RequestBody Usuario dadosAtualizados) {
        Usuario atualizado = administradorService.atualizarUsuario(id, dadosAtualizados);
        return ResponseEntity.ok(atualizado);
    }

    @PatchMapping("/usuarios/{id}")
    public Usuario atualizarParcialUsuario(@PathVariable String id, @RequestBody Map<String, Object> campos) {
        return administradorService.atualizarParcial(id, campos);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String id) {
        administradorService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
