package com.k4nela.easypeasy.controller;

import com.k4nela.easypeasy.entity.Usuario;
import com.k4nela.easypeasy.repository.UsuarioRepository;
import com.k4nela.easypeasy.service.LogService;
import com.k4nela.easypeasy.service.NotificacaoService;
import com.k4nela.easypeasy.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LogService logService;
    @Autowired
    private NotificacaoService notificacaoService;

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

    @PatchMapping("/{id}")
    public Usuario atualizarInformacao(@PathVariable String id, @RequestBody Map<String, Object> campos){
        Usuario u = usuarioRepository.findById(id).orElseThrow( () ->
                new RuntimeException("Usuario não encontrado")
                );

        if(campos.containsKey("nome")){
            u.setNome((String) campos.get("nome"));
        }
        if(campos.containsKey("dataNasc")){
            u.setDataNasc((String) campos.get("dataNasc"));
        }
        if(campos.containsKey("email")){
            u.setEmail((String) campos.get("email"));
        }
        if(campos.containsKey("senha")){
            u.setSenha((String) campos.get("senha"));
        }
        if(campos.containsKey("endereco")){
            u.setEndereco((String) campos.get("endereco"));
        }
        if(campos.containsKey("telefone")){
            u.setTelefone((String) campos.get("telefone"));
        }

        return usuarioRepository.save(u);
    }

    //  Excluir usuário
    @DeleteMapping("/{id}")
    public String deletarUsuario(@PathVariable String id) {
        usuarioService.deletarUsuario(id);
        return "Usuário removido com sucesso!";
    }
}
