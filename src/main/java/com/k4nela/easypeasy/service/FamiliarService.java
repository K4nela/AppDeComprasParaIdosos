package com.k4nela.easypeasy.service;

import com.k4nela.easypeasy.entity.Familiar;
import com.k4nela.easypeasy.entity.Idoso;
import com.k4nela.easypeasy.entity.Monitora;
import com.k4nela.easypeasy.repository.FamiliarRepository;
import com.k4nela.easypeasy.repository.MonitoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FamiliarService {

    @Autowired
    private FamiliarRepository familiarRepository;

    @Autowired
    private LogService logService;

    @Autowired
    private NotificacaoService notificacaoService;

    public List<Familiar> listar() {
        List<Familiar> lista = familiarRepository.findAll();

        logService.registrar(
                "Listagem de Familiares",
                "INFO",
                "FamiliarService",
                "Total retornado: " + lista.size()
        );

        return lista;
    }

    public Familiar criar(Familiar familiar) {
        Familiar criado = familiarRepository.save(familiar);

        logService.registrar(
                "Familiar Criado",
                "INFO",
                "FamiliarService",
                "ID " + criado.getId()
        );

        notificacaoService.enviar(
                "Novo familiar cadastrado!",
                "O familiar " + criado.getUsuario().getNome() + " foi adicionado ao sistema."
        );

        return criado;
    }

    public Familiar buscarPorId(String id) {
        Familiar f = familiarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Familiar não encontrado: " + id));

        logService.registrar(
                "Familiar Consultado",
                "INFO",
                "FamiliarService",
                "ID " + id
        );

        return f;
    }

    public Familiar atualizar(String id, Familiar atualizado) {
        Familiar existente = buscarPorId(id);

        atualizado.setId(existente.getId());
        Familiar atualizadoFinal = familiarRepository.save(atualizado);

        logService.registrar(
                "Familiar Atualizado",
                "INFO",
                "FamiliarService",
                "ID " + id
        );

        notificacaoService.enviar(
                "Dados atualizados!",
                "O familiar " + atualizadoFinal.getUsuario().getNome() + " teve seus dados atualizados."
        );

        return atualizadoFinal;
    }

    public void deletar(String id) {
        Familiar existente = buscarPorId(id);
        familiarRepository.delete(existente);

        logService.registrar(
                "Familiar Deletado",
                "WARN",
                "FamiliarService",
                "ID " + id
        );

        notificacaoService.enviar(
                "Conta removida",
                "O familiar " + existente.getUsuario().getNome() + " foi removido do sistema."
        );
    }

    @Autowired
    private MonitoraRepository monitoraRepository;

    public List<Idoso> listarIdososPorFamiliar(String familiarId) {
        List<Monitora> vinculos = monitoraRepository.findByFamiliarId(familiarId);
        List<Idoso> idosos = new ArrayList<>();

        for (Monitora v : vinculos) {
            idosos.add(v.getIdoso());
        }

        logService.registrar(
                "Listagem de Idosos por Familiar",
                "INFO",
                "FamiliarService",
                "Familiar ID " + familiarId + " retornou " + idosos.size() + " idosos"
        );

        return idosos;
    }


}
