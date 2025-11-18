package com.k4nela.easypeasy.service;

import com.k4nela.easypeasy.entity.Item;
import com.k4nela.easypeasy.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private LogService logService;

    @Autowired
    private NotificacaoService notificacaoService;

    public List<Item> listar() {
        List<Item> itens = itemRepository.findAll();

        logService.registrar(
                "Listagem de itens",
                "INFO",
                "ItemService",
                "Total: " + itens.size()
        );

        return itens;
    }

    public Item criar(Item item) {
        Item salvo = itemRepository.save(item);

        logService.registrar(
                "Item criado",
                "INFO",
                "ItemService",
                "ID " + salvo.getId()
        );

        notificacaoService.enviar(
                "Novo item adicionado",
                "O item '" + salvo.getNomeItem() + "' foi criado com sucesso."
        );

        return salvo;
    }

    public Item buscarPorId(int id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        logService.registrar(
                "Consulta de item",
                "INFO",
                "ItemService",
                "ID " + id
        );

        return item;
    }

    public Item atualizar(int id, Item itemAtualizado) {
        Item existente = buscarPorId(id);

        itemAtualizado.setId(existente.getId());
        Item salvo = itemRepository.save(itemAtualizado);

        logService.registrar(
                "Item atualizado",
                "INFO",
                "ItemService",
                "ID " + id
        );

        notificacaoService.enviar(
                "Item atualizado",
                "O item '" + salvo.getNomeItem() + "' foi modificado."
        );

        return salvo;
    }

    public Item atualizarParcial(int id, Map<String, Object> campos) {
        Item item = buscarPorId(id);

        if (campos.containsKey("nomeItem")) {
            item.setNomeItem((String) campos.get("nomeItem"));
        }
        if (campos.containsKey("descricao")) {
            item.setDescricao((String) campos.get("descricao"));
        }
        if (campos.containsKey("quantidade")) {
            item.setQuantidade((Integer) campos.get("quantidade"));
        }
        if (campos.containsKey("nomeLoja")) {
            item.setNomeLoja((String) campos.get("nomeLoja"));
        }
        if (campos.containsKey("link")) {
            item.setLink((String) campos.get("link"));
        }

        Item salvo = itemRepository.save(item);

        logService.registrar(
                "Item parcialmente atualizado",
                "INFO",
                "ItemService",
                "ID " + id
        );

        notificacaoService.enviar(
                "Item atualizado",
                "Alguns campos do item '" + salvo.getNomeItem() + "' foram alterados."
        );

        return salvo;
    }

    public void deletar(int id) {
        Item item = buscarPorId(id);
        itemRepository.delete(item);

        logService.registrar(
                "Item deletado",
                "WARN",
                "ItemService",
                "ID " + id
        );

        notificacaoService.enviar(
                "Item removido",
                "O item '" + item.getNomeItem() + "' foi excluído."
        );
    }
}
