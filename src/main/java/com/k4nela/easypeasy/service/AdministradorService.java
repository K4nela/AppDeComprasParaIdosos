package com.k4nela.easypeasy.service;

import com.k4nela.easypeasy.controller.UsuarioController;
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
        logService.registrar("Listagem de administradores", "INFO", "AdministradorService", "Total retornado: " + admins.size());
        return admins;
    }

    // CRIAR
    public Administrador criar(Administrador adm) {
        Administrador salvo = administradorRepository.save(adm);
        logService.registrar("Administrador criado", "INFO", "AdministradorService", "ID " + salvo.getId());
        notificacaoService.enviar("Novo administrador cadastrado", "Administrador '" + salvo.getUsuario().getNome() + "' criado com sucesso");
        return salvo;
    }

    // BUSCAR POR ID
    public Administrador buscarPorId(String id) {
        Administrador adm = administradorRepository.findById(id).orElse(null);
        logService.registrar("Consulta de administrador", "INFO", "AdministradorService", "ID " + id);
        return adm;
    }

    public Usuario buscarUsuarioPorId(String id){
        return usuarioService.buscarPorId(id);
    }

    // ATUALIZAR
    public Administrador atualizar(String id, Administrador admAtualizado) {
        admAtualizado.setId(id);
        Administrador salvo = administradorRepository.save(admAtualizado);
        logService.registrar("Administrador atualizado", "INFO", "AdministradorService", "ID " + id);
        notificacaoService.enviar("Administrador atualizado", "Administrador '" + salvo.getUsuario().getNome() + "' atualizado com sucesso");
        return salvo;
    }

    // DELETAR
    public void deletar(String id) {
        Administrador adm = administradorRepository.findById(id).orElse(null);
        if (adm != null) {
            administradorRepository.delete(adm);
            logService.registrar("Administrador deletado", "WARN", "AdministradorService", "ID " + id);
            notificacaoService.enviar("Administrador removido", "Administrador '" + adm.getUsuario().getNome() + "' removido do sistema");
        }
    }

    public List<Usuario> listarTodosUsuarios() {
        return usuarioService.listarUsuarios();
    }

    public Usuario atualizarUsuario(String id, Usuario dados) {
        logService.registrar("Admin atualizou usuário", "WARN", "AdministradorService", "ID usuário: " + id);
        return usuarioService.atualizarUsuario(id, dados);
    }

    public Usuario atualizarParcial(String id, Map<String, Object> campos){
        return usuarioService.atualizarInformacao(id, campos);
    }

    public void deletarUsuario(String id) {
        Usuario usuario = buscarUsuarioPorId(id);

        logService.registrar("Admin deletou usuário", "ERROR", "AdministradorService", "ID usuário: " + id);
        notificacaoService.enviar("Conta removida", "O usuário " + usuario.getNome() + " foi removido do sistema.");
        usuarioService.deletarUsuario(id);
    }
}
