package com.k4nela.easypeasy.service;

import com.k4nela.easypeasy.entity.ListaDeDesejos;
import com.k4nela.easypeasy.repository.ListaDeDesejosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ListaDeDesejosService {

    @Autowired
    private ListaDeDesejosRepository listaRepository;

    @Autowired
    private LogService logService;

    @Autowired
    private NotificacaoService notificacaoService;

    public List<ListaDeDesejos> listar() {
        List<ListaDeDesejos> listas = listaRepository.findAll();

        logService.registrar(
                "Listagem de listas de desejos",
                "INFO",
                "ListaDeDesejosService",
                "Total retornado: " + listas.size()
        );

        return listas;
    }

    public ListaDeDesejos criar(ListaDeDesejos lista) {
        // Gera a data de criação se não vier no JSON
        if (lista.getDataCriacao() == null) {
            lista.setDataCriacao(LocalDate.now());
        }

        ListaDeDesejos salva = listaRepository.save(lista);

        logService.registrar(
                "Lista criada",
                "INFO",
                "ListaDeDesejosService",
                "ID " + salva.getId()
        );

        notificacaoService.enviar(
                "Nova lista criada",
                "A lista '" + salva.getNomeLista() + "' foi adicionada ao sistema."
        );

        return salva;
    }

    public ListaDeDesejos buscarPorId(String id) {
        ListaDeDesejos lista = listaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada: " + id));

        logService.registrar(
                "Lista consultada",
                "INFO",
                "ListaDeDesejosService",
                "ID " + id
        );

        return lista;
    }

    public ListaDeDesejos atualizar(String id, ListaDeDesejos atualizada) {
        ListaDeDesejos existente = buscarPorId(id);

        atualizada.setId(existente.getId());
        ListaDeDesejos salva = listaRepository.save(atualizada);

        logService.registrar(
                "Lista atualizada",
                "INFO",
                "ListaDeDesejosService",
                "ID " + id
        );

        notificacaoService.enviar(
                "Lista atualizada",
                "A lista '" + salva.getNomeLista() + "' teve seus dados modificados."
        );

        return salva;
    }

    public ListaDeDesejos atualizarParcial(int id, Map<String, Object> campos) {
        ListaDeDesejos lista = buscarPorId(String.valueOf(id));

        if (campos.containsKey("nomeLista")) {
            lista.setNomeLista((String) campos.get("nomeLista"));
        }
        if (campos.containsKey("descricao")) {
            lista.setDescricao((String) campos.get("descricao"));
        }
        if (campos.containsKey("dataCriacao")) {
            lista.setDataCriacao(LocalDate.parse((String) campos.get("dataCriacao")));
        }

        ListaDeDesejos salva = listaRepository.save(lista);

        logService.registrar(
                "Lista parcialmente atualizada",
                "INFO",
                "ListaDeDesejosService",
                "ID " + id
        );

        notificacaoService.enviar(
                "Lista atualizada",
                "Alguns campos da lista '" + salva.getNomeLista() + "' foram modificados."
        );

        return salva;
    }

    public void deletar(String id) {
        ListaDeDesejos lista = buscarPorId(id);
        listaRepository.delete(lista);

        logService.registrar(
                "Lista deletada",
                "WARN",
                "ListaDeDesejosService",
                "ID " + id
        );

        notificacaoService.enviar(
                "Lista removida",
                "A lista '" + lista.getNomeLista() + "' foi excluída."
        );
    }
}

