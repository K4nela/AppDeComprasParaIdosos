package com.k4nela.easypeasy.service;

import com.k4nela.easypeasy.entity.Usuario;
import com.k4nela.easypeasy.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LogService logService;
    @Autowired
    private NotificacaoService notificacaoService;

    //  Cria um novo usuário com validação de e-mail único
    public Usuario criarUsuario(Usuario usuario) {
        // Verifica se já existe um usuário com o mesmo e-mail
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExistente.isPresent()) {
            throw new RuntimeException("Já existe um usuário cadastrado com este e-mail!");
        }

        logService.registrar(
                "Usuario criado!",
                "INFO",
                "UsuarioController",
                "ID " + usuario.getId());

        notificacaoService.enviar(
          "Bem Vindo!",
          "Usuário " + usuario.getNome() + " cadastrado com sucésso!"
        );

        return usuarioRepository.save(usuario);
    }

    //  Retorna todos os usuários cadastrados
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    //  Busca um usuário pelo ID
    public Optional<Usuario> buscarPorId(String id) {
        return usuarioRepository.findById(id);
    }

    //  Atualiza os dados de um usuário existente
    public Usuario atualizarUsuario(String id, Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        Usuario usuario = usuarioExistente.get();
        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setSenha(usuarioAtualizado.getSenha());
        usuario.setTelefone(usuarioAtualizado.getTelefone());
        usuario.setEndereco(usuarioAtualizado.getEndereco());
        usuario.setTipo(usuarioAtualizado.getTipo());
        usuario.setDataNasc(usuarioAtualizado.getDataNasc());

        logService.registrar(
                "Usuario atualizado!",
                "INFO",
                "UsuarioController",
                "ID" + id
        );

        notificacaoService.enviar(
                "Usuario Atualizado!",
                usuarioAtualizado.getNome() + " atualizado com sucésso!"
        );

        return usuarioRepository.save(usuario);
    }

    //  Remove um usuário pelo ID
    public void deletarUsuario(String id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado para exclusão!");
        }

        logService.registrar(
                "Usuario deletado!",
                "WARN",
                "UsuarioService",
                "ID deletado: " + id
        );

        notificacaoService.enviar(
          "Usuario Excluído!",
          "Usuário " + usuarioRepository.getById(id) + " excluído com sucésso!"
        );

        usuarioRepository.deleteById(id);
    }
}
