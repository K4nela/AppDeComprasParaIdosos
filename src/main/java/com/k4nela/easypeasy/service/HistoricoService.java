package com.k4nela.easypeasy.service;

import com.k4nela.easypeasy.entity.Familiar;
import com.k4nela.easypeasy.entity.Historico;
import com.k4nela.easypeasy.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private LogService logService;

    @Autowired
    private NotificacaoService notificacaoService;

    public List<Historico> listar() {
        List<Historico> lista = historicoRepository.findAll();

        logService.registrar(
                "Listagem de históricos",
                "INFO",
                "HistoricoService",
                "Total retornado: " + lista.size()
        );

        return lista;
    }

    public Historico criar(Historico historico) {
        Historico salvo = historicoRepository.save(historico);

        logService.registrar(
                "Histórico criado",
                "INFO",
                "HistoricoService",
                "ID " + salvo.getId()
        );

        notificacaoService.enviar(
                "Novo histórico registrado",
                "Histórico do item '" + salvo.getItem().getNomeItem() + "' criado com sucesso."
        );

        return salvo;
    }

    public Historico buscarPorId(String id) {
        Historico hist = historicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Histórico não encontrado: " + id));

        logService.registrar(
                "Histórico consultado",
                "INFO",
                "HistoricoService",
                "ID " + id
        );

        return hist;
    }

    public Historico atualizar(String id, Historico atualizado) {
        Historico existente = buscarPorId(id);

        atualizado.setId(existente.getId());
        Historico salvo = historicoRepository.save(atualizado);

        logService.registrar(
                "Histórico atualizado",
                "INFO",
                "HistoricoService",
                "ID " + id
        );

        notificacaoService.enviar(
                "Histórico atualizado",
                "Histórico do item '" + salvo.getItem().getNomeItem() + "' foi atualizado."
        );

        return salvo;
    }

    public Historico atualizarParcial(int id, Map<String, Object> campos) {
        Historico hist = historicoRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Histórico não encontrado"));

        if (campos.containsKey("status")) {
            hist.setStatus((String) campos.get("status"));
        }

        Historico salvo = historicoRepository.save(hist);

        logService.registrar(
                "Histórico parcialmente atualizado",
                "INFO",
                "HistoricoService",
                "ID " + id
        );

        notificacaoService.enviar(
                "Histórico atualizado",
                "O status do histórico ID " + id + " foi alterado para '" + hist.getStatus() + "'."
        );

        return salvo;
    }

    public void deletar(String id) {
        Historico historico = buscarPorId(id);

        historicoRepository.delete(historico);

        logService.registrar(
                "Histórico deletado",
                "WARN",
                "HistoricoService",
                "ID " + id
        );

        notificacaoService.enviar(
                "Histórico removido",
                "O histórico do item '" + historico.getItem().getNomeItem() + "' foi removido."
        );
    }

    public Historico atualizarStatusHistorico(int historicoId, String novoStatus, Familiar familiar) {
        Historico hist = historicoRepository.findById(String.valueOf(historicoId))
                .orElseThrow(() -> new RuntimeException("Histórico não encontrado"));

        hist.setStatus(novoStatus);
        Historico salvo = historicoRepository.save(hist);

        logService.registrar(
                "Status do histórico atualizado",
                "INFO",
                "HistoricoService",
                "Histórico ID " + historicoId + " atualizado para " + novoStatus
        );

        notificacaoService.enviar(
                "Status atualizado",
                "O familiar " + familiar.getUsuario().getNome()
                        + " atualizou o status do item '" + hist.getItem().getNomeItem()
                        + "' para '" + novoStatus + "'."
        );

        return salvo;
    }

}
