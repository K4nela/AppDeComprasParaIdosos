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
        return historicoRepository.findAll();
    }

    public Historico criar(Historico historico) {
        logService.registrar("Histórico criado", "INFO", "HistoricoService", "ID " + historico.getId());
        notificacaoService.enviar("Novo histórico registrado", "Histórico do Item " + historico.getItem().getNomeItem()  + " criado com sucesso");

        return historicoRepository.save(historico);
    }

    public Historico buscarPorId(String id) {
        Historico hist = historicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Histórico não encontrado: " + id));
        logService.registrar("Histórico consultado", "INFO", "HistoricoService", "ID " + id);
        return hist;
    }

    public Historico atualizar(String id, Historico atualizado) {
        atualizado.setId(Integer.parseInt(id));
        Historico historico = historicoRepository.save(atualizado);
        logService.registrar("Histórico atualizado", "INFO", "HistoricoService", "ID " + id);
        notificacaoService.enviar("Histórico atualizado", "Histórico do Item " + historico.getItem().getNomeItem()  + " atualizado");
        return historico;
    }

    public Historico atualizarParcial(int id, Map<String, Object> campos) {
        Historico hist = historicoRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Histórico não encontrado"));

        if (campos.containsKey("status")) {
            hist.setStatus((String) campos.get("status"));
        }

        Historico historico = historicoRepository.save(hist);
        logService.registrar("Histórico parcialmente atualizado", "INFO", "HistoricoService", "ID " + id);
        notificacaoService.enviar("Histórico atualizado", "Status do histórico ID " + id + " atualizado para " + hist.getStatus());
        return historico;
    }

    public void deletar(String id) {
        Historico historico = historicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Histórico não encontrado"));
        historicoRepository.delete(historico);
        logService.registrar("Histórico deletado", "WARN", "HistoricoService", "ID " + id);
        notificacaoService.enviar("Histórico removido", "Histórico do Item " + historico.getItem().getNomeItem()  + " removido do sistema");
    }

    public Historico atualizarStatusHistorico(int historicoId, String novoStatus, Familiar familiar) {
        // busca o histórico pelo ID
        Historico hist = historicoRepository.findById(String.valueOf(historicoId))
                .orElseThrow(() -> new RuntimeException("Histórico não encontrado"));

        // atualiza o status
        hist.setStatus(novoStatus);
        Historico salvo = historicoRepository.save(hist);

        // registra log
        logService.registrar(
                "Status do histórico atualizado",
                "INFO",
                "HistoricoService",
                "Histórico ID " + historicoId + " atualizado para " + novoStatus
        );

        // envia notificação
        notificacaoService.enviar(
                "Status atualizado",
                "O familiar " + familiar.getUsuario().getNome() + " atualizou o status do item '"
                        + hist.getItem().getNomeItem() + "' para " + novoStatus
        );

        return salvo;
    }

}
