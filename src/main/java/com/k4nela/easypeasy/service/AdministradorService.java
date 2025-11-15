package com.k4nela.easypeasy.service;

import com.k4nela.easypeasy.entity.Administrador;
import com.k4nela.easypeasy.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private LogService logService;

    @Autowired
    private NotificacaoService notificacaoService;

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
}
