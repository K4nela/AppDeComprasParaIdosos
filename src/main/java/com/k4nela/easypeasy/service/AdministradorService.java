package com.k4nela.easypeasy.service;

import com.k4nela.easypeasy.entity.Administrador;
import com.k4nela.easypeasy.entity.Usuario;
import com.k4nela.easypeasy.repository.AdministradorRepository;
import com.k4nela.easypeasy.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private LogService logService;

    @Autowired
    private NotificacaoService notificacaoService;

    @Autowired
    private UsuarioService usuarioService;
    private UsuarioRepository usuarioRepository;

    // LISTAR
    public List<Administrador> listar() {
        List<Administrador> admins = administradorRepository.findAll();
        logService.registrar(
                "Listagem de administradores",
                "INFO",
                "AdministradorService",
                "Total retornado: " + admins.size()
        );
        return admins;
    }

    // CRIAR
    public Administrador criar(Administrador adm) {
        Administrador salvo = administradorRepository.save(adm);
        logService.registrar(
                "Administrador criado",
                "INFO",
                "AdministradorService",
                "ID " + salvo.getId()
        );
        notificacaoService.enviar(
                "Novo administrador cadastrado",
                "Administrador '" + salvo.getUsuario().getNome() + "' criado com sucesso"
        );
        return salvo;
    }

    // BUSCAR POR ID
    public Administrador buscarPorId(String id) {
        Administrador adm = administradorRepository.findById(id)
                .orElseThrow(() -> {
                    logService.registrar(
                            "Administrador não encontrado",
                            "WARN",
                            "AdministradorService",
                            "ID " + id
                    );
                    return new RuntimeException("Administrador não encontrado: " + id);
                });

        logService.registrar(
                "Consulta de administrador",
                "INFO",
                "AdministradorService",
                "ID " + id
        );
        return adm;
    }

    public Usuario buscarUsuarioPorId(String id){
        Usuario usuario = usuarioService.buscarPorId(id);
        logService.registrar(
                "Consulta de usuário pelo admin",
                "INFO",
                "AdministradorService",
                "ID usuário: " + id
        );
        return usuario;
    }

    // ATUALIZAR
    public Administrador atualizar(String id, Administrador admAtualizado) {
        admAtualizado.setId(id);
        Administrador salvo = administradorRepository.save(admAtualizado);
        logService.registrar(
                "Administrador atualizado",
                "INFO",
                "AdministradorService",
                "ID " + id
        );
        notificacaoService.enviar(
                "Administrador atualizado",
                "Administrador '" + salvo.getUsuario().getNome() + "' atualizado com sucesso"
        );
        return salvo;
    }

    // ATUALIZAR PARCIAL
    public Usuario atualizarParcial(String id, Map<String, Object> campos){
        Usuario usuarioAtualizado = usuarioService.atualizarInformacao(id, campos);

        logService.registrar(
                "Admin atualizou parcialmente usuário",
                "INFO",
                "AdministradorService",
                "ID usuário: " + id + " | Campos alterados: " + campos.keySet()
        );

        notificacaoService.enviar(
                "Atualização parcial de usuário",
                "Admin alterou alguns dados do usuário '" + usuarioAtualizado.getNome() + "'"
        );

        return usuarioAtualizado;
    }

    // DELETAR ADMIN
    public void deletar(String id) {
        Administrador adm = administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado: " + id));
        administradorRepository.delete(adm);

        logService.registrar(
                "Administrador deletado",
                "WARN",
                "AdministradorService",
                "ID " + id
        );
        notificacaoService.enviar(
                "Administrador removido",
                "Administrador '" + adm.getUsuario().getNome() + "' removido do sistema"
        );
    }

    // LISTAR TODOS USUÁRIOS
    public List<Usuario> listarTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        logService.registrar(
                "Admin listou todos os usuários",
                "INFO",
                "AdministradorService",
                "Total usuários retornados: " + usuarios.size()
        );
        return usuarios;
    }

    // ATUALIZAR USUÁRIO
    public Usuario atualizarUsuario(String id, Usuario dados) {
        Usuario atualizado = usuarioService.atualizarUsuario(id, dados);
        logService.registrar(
                "Admin atualizou usuário",
                "WARN",
                "AdministradorService",
                "ID usuário: " + id
        );
        notificacaoService.enviar(
                "Usuário atualizado pelo admin",
                "Os dados do usuário '" + atualizado.getNome() + "' foram atualizados pelo administrador"
        );
        return atualizado;
    }

    // DELETAR USUÁRIO
    public void deletarUsuario(String id) {
        Usuario usuario = buscarUsuarioPorId(id);
        usuarioService.deletarUsuario(id);

        logService.registrar(
                "Admin deletou usuário",
                "ERROR",
                "AdministradorService",
                "ID usuário: " + id
        );
        notificacaoService.enviar(
                "Conta removida",
                "O usuário '" + usuario.getNome() + "' foi removido do sistema pelo administrador"
        );
    }
}
