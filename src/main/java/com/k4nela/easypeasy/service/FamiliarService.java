package com.k4nela.easypeasy.service;

import com.k4nela.easypeasy.entity.*;
import com.k4nela.easypeasy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FamiliarService {
    @Autowired
    private ListaDeDesejosRepository listaRepository;

    @Autowired
    private HistoricoService historicoService;

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MonitoraRepository monitoraRepository;

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

    private void validarAcesso(String familiarId, String idosoId) {
        boolean temAcesso = monitoraRepository.existsByFamiliarIdAndIdosoId(familiarId, idosoId);
        if (!temAcesso) {
            logService.registrar("Acesso negado ao idoso", "WARN", "FamiliarService",
                    "Familiar " + familiarId + " tentou acessar idoso " + idosoId);
            throw new RuntimeException("Você não monitora este idoso");
        }
    }

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

    public List<ListaDeDesejos> listarTodasAsListasPorFamiliar(String familiarId) {
        List<Idoso> idosos = listarIdososPorFamiliar(familiarId);
        List<ListaDeDesejos> todasAsListas = new ArrayList<>();

        for (Idoso idoso : idosos) {
            todasAsListas.addAll(listaRepository.findByIdoso(idoso));
        }

        logService.registrar(
                "Listagem de todas as listas do familiar",
                "INFO",
                "FamiliarService",
                "Familiar " + familiarId + " | Total listas: " + todasAsListas.size()
        );

        return todasAsListas;
    }

    public List<ListaDeDesejos> listarListasDeUmIdoso(String familiarId, String idosoId) {
        // Valida se o familiar tem acesso ao idoso
        validarAcesso(familiarId, idosoId);

        // Busca todas as listas do idoso
        List<ListaDeDesejos> listas = listaRepository.findByIdosoId(idosoId);

        logService.registrar(
                "Listagem de listas de um idoso específico",
                "INFO",
                "FamiliarService",
                "Familiar " + familiarId + " | Idoso " + idosoId + " | Total listas: " + listas.size()
        );

        return listas;
    }

    public ListaDeDesejos verListaDoIdoso(String familiarId, String idosoId, int listaId) {
        validarAcesso(familiarId, idosoId);

        return listaRepository.findByIdAndIdosoId(listaId, idosoId)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada ou sem permissão"));
    }

    public List<Item> verItens(String familiarId, String idosoId, int listaId) {
        ListaDeDesejos lista = verListaDoIdoso(familiarId, idosoId, listaId);
        return new ArrayList<>(lista.getItens());
    }

    public Item mudarStatusItem(String familiarId, int itemId, String novoStatus) {
        String statusUpper = novoStatus.toUpperCase();
        if (!List.of("PENDENTE", "EM_ANDAMENTO", "CONCLUIDO", "CANCELADO").contains(statusUpper)) {
            throw new RuntimeException("Status inválido. Use: PENDENTE, EM_ANDAMENTO, CONCLUIDO ou CANCELADO");
        }

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        String idosoId = item.getListaDeDesejos().getIdoso().getId();
        validarAcesso(familiarId, idosoId);

        Historico ultimoHistorico = historicoRepository.findTopByItemIdOrderByDataHistoricoDesc(itemId)
                .orElseThrow(() -> new RuntimeException("Histórico do item não encontrado"));

        Familiar familiar = buscarPorId(familiarId);

        historicoService.atualizarStatusHistorico(
                Integer.parseInt(String.valueOf(ultimoHistorico.getId())),
                statusUpper,
                familiar
        );
        return itemRepository.findById(itemId).get();
    }
}
