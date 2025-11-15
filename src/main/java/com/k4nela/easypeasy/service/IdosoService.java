package com.k4nela.easypeasy.service;

import com.k4nela.easypeasy.entity.Idoso;
import com.k4nela.easypeasy.entity.Item;
import com.k4nela.easypeasy.entity.ListaDeDesejos;
import com.k4nela.easypeasy.repository.IdosoRepository;
import com.k4nela.easypeasy.repository.ItemRepository;
import com.k4nela.easypeasy.repository.ListaDeDesejosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdosoService {

    @Autowired
    private static IdosoRepository idosoRepository;

    @Autowired
    private static LogService logService;

    @Autowired
    private NotificacaoService notificacaoService;

    @Autowired
    private ListaDeDesejosRepository listaRepository;

    @Autowired
    private ItemRepository itemRepository;


    public static List<Idoso> listar() {
        List<Idoso> lista = idosoRepository.findAll();

        logService.registrar(
                "Listagem de Idosos",
                "INFO",
                "IdosoService",
                "Total retornado: " + lista.size()
        );

        return lista;
    }

    public Idoso criar(Idoso idoso) {
        Idoso criado = idosoRepository.save(idoso);

        logService.registrar(
                "Idoso Criado",
                "INFO",
                "IdosoService",
                "ID " + criado.getId()
        );

        notificacaoService.enviar(
                "Novo idoso cadastrado!",
                "O idoso " + criado.getUsuario().getNome() + " foi adicionado ao sistema."
        );
        return criado;
    }

    public Idoso buscarPorId(String id) {
        Idoso i = idosoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Idoso não encontrado: " + id));

        logService.registrar(
                "Idoso Consultado",
                "INFO",
                "IdosoService",
                "ID " + id
        );

        return i;
    }

    public Idoso atualizar(String id, Idoso atualizado) {
        Idoso existente = buscarPorId(id);

        atualizado.setId(existente.getId());
        Idoso atualizadoFinal = idosoRepository.save(atualizado);

        logService.registrar(
                "Idoso Atualizado",
                "INFO",
                "IdosoService",
                "ID " + id
        );

        notificacaoService.enviar(
                "Dados atualizados!",
                "O idoso " + atualizadoFinal.getUsuario().getNome() + " teve seus dados atualizados."
        );

        return atualizadoFinal;
    }

    public void deletar(String id) {
        Idoso i = buscarPorId(id);
        idosoRepository.delete(i);

        logService.registrar(
                "Idoso Deletado",
                "WARN",
                "IdosoService",
                "ID " + id
        );

        notificacaoService.enviar(
                "Registro removido",
                "O idoso " + i.getUsuario().getNome() + " foi removido do sistema."
        );
    }

    public ListaDeDesejos criarListaDeDesejos(Idoso idoso, ListaDeDesejos lista) {
        lista.setIdoso(idoso);
        ListaDeDesejos salva = listaRepository.save(lista);

        logService.registrar(
                "Lista de desejos criada",
                "INFO",
                "IdosoService",
                "Lista ID " + salva.getId() + " criada para o idoso " + idoso.getUsuario().getNome()
        );

        notificacaoService.enviar(
                "Nova lista de desejos",
                "O idoso " + idoso.getUsuario().getNome() + " criou uma nova lista de desejos."
        );

        return salva;
    }

    public Item criarItem(ListaDeDesejos lista, Item item) {
        item.setListaDeDesejos(lista);
        Item salvo = itemRepository.save(item);

        logService.registrar(
                "Item criado",
                "INFO",
                "IdosoService",
                "Item ID " + salvo.getId() + " adicionado à lista ID " + lista.getId()
        );

        notificacaoService.enviar(
                "Novo item adicionado",
                "Um novo item '" + salvo.getNomeItem() + "' foi adicionado à lista " + lista.getNomeLista()
        );

        return salvo;
    }

}

