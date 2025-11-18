package com.k4nela.easypeasy.service;

import com.k4nela.easypeasy.entity.Item;
import com.k4nela.easypeasy.entity.ListaDeDesejos;
import com.k4nela.easypeasy.repository.ItemRepository;
import com.k4nela.easypeasy.repository.ListaDeDesejosRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data

@Service
public class ListaDeDesejosService {

    @Autowired
    private ListaDeDesejosRepository listaRepository;

    @Autowired
    private LogService logService;

    @Autowired
    private NotificacaoService notificacaoService;

    private ItemRepository itemRepository;

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
            lista.setDataCriacao(LocalDateTime.now());
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
            lista.setDataCriacao(LocalDateTime.parse((String) campos.get("dataCriacao")));
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

    public Item criarItemNaLista(String id, Item item){
        ListaDeDesejos lista = listaRepository.findById(id).orElseThrow(() -> new RuntimeException("Lista de desejos não encontrada"));

        item.setListaDeDesejos(lista);

        Item salvo = itemRepository.save(item);

        lista.getItens().add(salvo);
        listaRepository.save(lista);


        // log
        logService.registrar(
                "Item criado na lista",
                "INFO",
                "ListaDeDesejosService",
                "Item " + salvo.getNomeItem() + " criado na lista ID " + lista.getId()
        );

        // notificação
        notificacaoService.enviar(
                "Novo item criado!",
                "O item '" + salvo.getNomeItem() + "' foi adicionado à lista '" + lista.getNomeLista() + "'."
        );

        return salvo;
    }

    public List<Item> listarItens(String listaId){
        ListaDeDesejos lista = buscarPorId(listaId);
        return lista.getItens();
    }
}

